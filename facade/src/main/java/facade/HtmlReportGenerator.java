package facade;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlReportGenerator implements ReportGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlReportGenerator.class);

	@Override
	public Path generateReport() {
		LOGGER.info("#Generating HTML Report");
		return null;
		// TODO Auto-generated method stub

	}

}
