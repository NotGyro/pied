package ws.zettabyte.gyro.pied;

public interface IType {
	boolean isEqual(IType other);
	boolean isSatisfiedBy(IType other);
}
