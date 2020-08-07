package command;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		
		File file  = new File("x.txt");
		FileCommands openCommand = new OpenCommand(file);
		FileCommands saveCommand = new SaveCommand(file);
		FileCommands closeCommand = new CloseCommand(file);
		
		FileCommandExecuter fileCommandExecuter =  new FileCommandExecuter();
		fileCommandExecuter.perform(openCommand);
		fileCommandExecuter.perform(saveCommand);
		fileCommandExecuter.perform(closeCommand);
		
		//same can be achieved with the help of functional programming as shown below
		FileCommandExecuter fileCommandExecuterWithFp =  new FileCommandExecuter();
		fileCommandExecuterWithFp.perform(()->{
			System.out.println("Open");
		});
		fileCommandExecuterWithFp.perform(()->{
			System.out.println("Save");
		});
		fileCommandExecuterWithFp.perform(()->{
			System.out.println("Close");
		});
	}
}
