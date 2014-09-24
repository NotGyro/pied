package ws.zettabyte.gyro.pied;

public interface IPipe extends INamedNode {
	void In(IPipe input);
	void ExecIn();
	IPipe Out();
	void ExecOut();
	
	IType getType();
}
