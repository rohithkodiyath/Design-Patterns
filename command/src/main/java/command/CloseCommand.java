package command;

import java.io.File;

public class CloseCommand implements FileCommands {

	private File file;
	
	public CloseCommand(File file) {
		this.file = file;
	}
	
	public void execute() {
		System.out.println("Closing file");

	}

	@Override
	public String toString() {
		return "CloseCommand [file=" + file + "]";
	}

}
