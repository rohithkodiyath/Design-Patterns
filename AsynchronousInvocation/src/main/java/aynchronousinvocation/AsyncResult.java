package aynchronousinvocation;

public interface AsyncResult <T> {
	
	void setValue(T value);
	
	T getValue() throws Exception;
	
	void await() throws InterruptedException;

	void setError(Exception e);
	
	boolean isCompleted();
	
}
