package aynchronousinvocation;

public class Client {
	public static void main(String[] args) throws Exception {
		
		AsynchronousExecuter executer = new AsynchonousExecuterImpl();
		AsyncResult<String> task1 = executer.startProcess(() -> {
			System.out.println("Before sleep");
			System.out.println("task -->"+ Thread.currentThread().getId());
			Thread.sleep(200);
			System.out.println("After sleep");
			return "Hi";
		},(result)->{
			System.out.println("Got result in callback "+result);
		});
		
		AsyncResult<String> task2 = executer.startProcess(() -> {
			Thread.sleep(3000);
			return "Bye";
		},(result)->{
			System.out.println("Got result in callback "+result);
		});
		
		executer.endProcess(task2);
		executer.endProcess(task1);
		
		
		
	}
}
