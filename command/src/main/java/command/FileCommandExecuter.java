package command;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCommandExecuter {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(FileCommandExecuter.class);
	
  private List<FileCommands> previousCommands = new ArrayList<FileCommands>();
  
  public void perform(FileCommands fileCommand) {
	  LOGGER.info("# file opeation "+fileCommand);
	  this.previousCommands.add(fileCommand);
	  fileCommand.execute();
  }
  
  
}
