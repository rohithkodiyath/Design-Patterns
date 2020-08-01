package facade;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfReportGenerator implements ReportGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PdfReportGenerator.class);

	
	@Override
	public Path generateReport() {
		LOGGER.info("Generating PDF report");
		return null;
		// TODO Auto-generated method stub

	}

}
