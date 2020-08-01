package facade;

import java.nio.file.Path;

public class ReportMakerFacade {

	private ReportType reportType;

	public ReportMakerFacade(ReportType type) {
		this.reportType = type;
	}

	public Path generateReport() {
		Path reportPath;
		switch (reportType) {
		case CSV:
			reportPath = new CSVReportGenerator().generateReport();
			break;
		case HTML:
			reportPath = new HtmlReportGenerator().generateReport();
			break;
		case PDF:
			reportPath = new PdfReportGenerator().generateReport();
			break;
		default:
			throw new IllegalArgumentException("An implementation for the Report type is not found");
		}
		return reportPath;
	}

}
