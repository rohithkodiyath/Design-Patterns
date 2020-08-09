package callback;

public class Client {
	public static void main(String[] args) {
		
		CallBackTask callbackTask = new CallBackTask() {
			@Override
			public void task() {
				System.out.println("This is th main task");
				
			}
		};
		
		callbackTask.executeTask(()->{
			System.out.println("This is the call back function");
		});
		
	}
}
