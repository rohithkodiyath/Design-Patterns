package aynchronousinvocation;

import java.util.Optional;
import java.util.concurrent.Callable;

public class AsynchonousExecuterImpl implements AsynchronousExecuter {

	public <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback) {
		AsyncResult<T> asyncResult = new AsynchronousResultImpl<T>(callback);
		new Thread(() -> {
			try {
				T result = task.call();
				asyncResult.setValue(result);
			} catch (Exception e) {
				asyncResult.setError(e);
			}
		}).start();
		return asyncResult;
	}

	public <T> AsyncResult<T> startProcess(Callable<T> task) {
		return startProcess(task, null);
	}

	public <T> T endProcess(AsyncResult<T> asyncResult) throws Exception {
		if (! asyncResult.isCompleted()) {
			asyncResult.await();
		}
		return asyncResult.getValue();
	}

	private static class AsynchronousResultImpl<T> implements AsyncResult<T> {

		private static final int RUNNING = 1;
		private static final int ERROR = 2;
		private static final int COMPLETED = 3;
		private volatile int status = RUNNING;
		private Object lock = new Object();

		private Optional<AsyncCallback<T>> callback;
		private Exception error;
		private T value;

		public AsynchronousResultImpl(AsyncCallback<T> callback) {
			this.callback = Optional.ofNullable(callback);
		}

		public void setValue(T value) {
			this.value = value;
			this.callback.ifPresent(cb->cb.callBack(value));
			this.status = COMPLETED;
			synchronized (lock) {
				lock.notifyAll();
			}
		}

		public T getValue() throws Exception {
			switch (status) {
			case COMPLETED:
				return this.value;
			case RUNNING:
				throw new IllegalStateException("Not yet finished");
			case ERROR:
				throw error;
			}
			return null;
		}

		public void await() throws InterruptedException {
			while (!isCompleted()) {
				synchronized (lock) {
					System.out.println("WAITING");
					lock.wait();
					System.out.println("WAITING RELEASED");
				}
			}
		}

		@Override
		public void setError(Exception e) {
			this.error = e;
			this.status = ERROR;
			synchronized (lock) {
				lock.notifyAll();
			}
		}

		@Override
		public boolean isCompleted() {
			return status > RUNNING;
		}

	}

}
