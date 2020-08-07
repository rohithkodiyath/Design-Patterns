package command;

import java.io.File;

public class OpenCommand implements FileCommands {

private File file;
	
	public OpenCommand(File file) {
		this.file = file;
	}
	
	
	public void execute() {
		System.out.println("Open file");

	}


	@Override
	public String toString() {
		return "OpenCommand [file=" + file + "]";
	}
	
	

}
