package visitor;

public class UserReportVisitor implements Visitor {

	private StringBuffer report = new StringBuffer();
	
	public void visit(User user) {
		report.append("User name "+user.getUserName() +System.lineSeparator());

	}

	public void visit(Order order) {
		report.append("Order  "+order.getOrderNumber() +System.lineSeparator());

	}

	public void visit(Item item) {
		report.append("Item  "+item.getName() +System.lineSeparator());

	}
	
	public String getReport() {
		return report.toString();
	}

}
