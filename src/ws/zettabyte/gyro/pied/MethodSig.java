package ws.zettabyte.gyro.pied;

import java.util.ArrayList;
import java.util.List;

public class MethodSig {
	protected final ArrayList<IType> signature;
	
	public MethodSig(ArrayList<IType> in) {
		signature = in;
	}
	
	public List<IType> getSignature(){
		return signature;
	};
}
