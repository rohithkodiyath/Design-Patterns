package callback;

import java.util.Optional;

public abstract class CallBackTask {
  
  public void executeTask(Callback callback) {
	  task();
	  Optional.ofNullable(callback).ifPresent(cb->cb.call());
  }
  
  public abstract void task();
}
