package aynchronousinvocation;

import java.util.concurrent.Callable;

public interface AsynchronousExecuter {
	
	public <T> AsyncResult<T> startProcess(Callable<T> task,AsyncCallback<T> callback);
	
	public <T> AsyncResult<T> startProcess(Callable<T> task);
	
	public <T> T endProcess(AsyncResult<T> asyncResult) throws Exception;
	
	
	
}
