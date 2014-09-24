package ws.zettabyte.gyro.pied;

public interface INamedNode {
	boolean isContainer();
	
	INamedNode getParent();
	void setParent(INamedNode setTo);
	
	//null if anonymous
	String getName();
	void setName(String name);

	INamedNode getChild(String name);
	
	int getChildCount();
	INamedNode getChild(int idx);
	
	boolean addChild(INamedNode child, String name);
	boolean removeChild(String name);
	
	boolean isPrivate();
	void setPrivate(boolean setTo);
}
