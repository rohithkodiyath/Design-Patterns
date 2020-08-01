package facade;

import java.nio.file.Path;

public class Client {
	public static void main(String[] args) {
		
		//for creating csv report
		ReportMakerFacade facade = new ReportMakerFacade(ReportType.CSV);
		Path reportPath = facade.generateReport();
		
		//for pdf report
		facade = new ReportMakerFacade(ReportType.PDF);
		reportPath = facade.generateReport();
		
		//for html report
		facade = new ReportMakerFacade(ReportType.HTML);
		reportPath = facade.generateReport();
		
		System.out.println(reportPath);
		
		
	}
}
