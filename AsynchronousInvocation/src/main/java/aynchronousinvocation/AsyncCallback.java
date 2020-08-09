package aynchronousinvocation;

@FunctionalInterface
public interface AsyncCallback <T> {
	 public void callBack(T value);
}
