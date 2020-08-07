package command;

import java.io.File;

public class SaveCommand implements FileCommands {
    
	private File file;
	
	public SaveCommand(File file) {
		this.file = file;
	}
	
	public void execute() {
		System.out.println("Save the file");

	}

	@Override
	public String toString() {
		return "SaveCommand [file=" + file + "]";
	}
	

}
