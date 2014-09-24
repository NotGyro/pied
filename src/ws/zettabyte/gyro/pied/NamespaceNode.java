package ws.zettabyte.gyro.pied;

import java.util.HashMap;
import java.util.Iterator;

/*
 * Used to compose things like code blocks and pipes that have a scope.
 */
public class NamespaceNode implements INamedNode
{
	protected HashMap<String, INamedNode> namespace = new HashMap<String, INamedNode>(64);
	protected NamespaceNode parent = null;
	
	//Null if anonymous.
	protected String name = null;
	
	/*
	 * Gets a variable with an unqualified name, like temp or i or setTeeth().
	 * 
	 * Recurses *up* the hierarchy only.
	 */
	public INamedNode ResolveUnqualified(String pipename)
	{
		INamedNode retval = namespace.get(pipename);
		if(retval == null)
		{
			//Recursion ho!
			if(parent != null)
			{
				retval = parent.ResolveUnqualified(pipename);
			}
		}
		return retval;
	}
	
	/*
	 * Gets a variable with a qualified name, like namespace.var or
	 * doctor.mask.fabric or killbot.processor.machine.ghost.getFavoriteColor()
	 * 
	 * Recurses *up* and then *down* the hierarchy.
	 */

	public INamedNode ResolveQualified(String pipename)
	{
		String currentLeft = null;
		String currentRight = pipename;
		String[] currentArry = null;
		

		currentArry = currentRight.split("\\.", 2);
		currentLeft = currentArry[0];
		currentRight = currentArry[1];
		
		//Use ResolveUnqualified's recursive nature to get the root of this variable name
		INamedNode currentTop = this.ResolveUnqualified(currentLeft);
		if(currentTop == null)
		{
			return null;
		}
		//Start crunching away at our qualified name.
		//Iteratively walks through the name, leftmost (highest in tree) first.
		while(currentRight.contains(".")) {
			currentArry = currentRight.split("\\.", 2);
			currentLeft = currentArry[0];
			currentRight = currentArry[1];
			
			currentTop = currentTop.getChild(currentLeft);
		}
		//The final node being named the rightmost element.
		return currentTop.getChild(currentRight);
	}
	
	/*
	 * Calls ResolveQualified or ResolveUnqualified internally.
	 */
	
	public INamedNode Resolve(String pipename)
	{
		if(pipename.contains(".")) {
			return ResolveQualified(pipename);
		}
		else {
			return ResolveUnqualified(pipename);
		}
	}

	@Override
	public boolean isContainer() {
		return true;
	}

	@Override
	public INamedNode getParent() {
		return parent;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String n) {
		name = n;
	}

	@Override
	public INamedNode getChild(String n) {
		return namespace.get(n);
	}

	@Override
	public int getChildCount() {
		return namespace.size();
	}

	@Override
	public INamedNode getChild(int idx) {
		if(idx < namespace.size()) {
			Iterator<INamedNode> iter = namespace.values().iterator();
			int i = 0;
			INamedNode current = null;
			while (i < namespace.size()) {
				current = iter.next();
				if(i == idx) {
					break; //We've found our element.
				}
				i++;
			}
			return current; 
		}
		else {
			return null;
		}
	}

	@Override
	public boolean addChild(INamedNode child, String cname) {
		if(namespace.containsKey(cname)) {
			return false;
		}
		else {
			namespace.put(cname, child);
			return true;
		}
	}
	@Override
	public boolean removeChild(String cname) {
		if(namespace.containsKey(cname)) {
			return false;
		}
		else {
			namespace.remove(cname);
			return true;
		}
	}

	@Override
	public void setParent(INamedNode setTo) {
		if(setTo instanceof NamespaceNode) {
			parent = (NamespaceNode) setTo;
		}
		else {
			//Throw error? Not sure.
		}
	}

	protected boolean privateStatus = false;
	
	@Override
	public boolean isPrivate() {
		return privateStatus;
	}

	@Override
	public void setPrivate(boolean setTo) {
		privateStatus = setTo;
	}
};
