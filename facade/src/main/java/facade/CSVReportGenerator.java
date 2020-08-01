package facade;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVReportGenerator implements ReportGenerator {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(CSVReportGenerator.class);
	
	@Override
	public Path generateReport() {
		LOGGER.info("# generating CSV report");
		return null;

	}

}
