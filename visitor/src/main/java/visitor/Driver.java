package visitor;

public class Driver {
	public static void main(String[] args) {
		User user = new User("Tony");
		Order order1 = new Order(1);
		Order order2 = new Order(2);
		Item item1 = new Item("Sugar");
		Item item2 = new Item("Glass");
		Item item3 = new Item("Computer");
		Item item4 = new Item("Mouse");
		
		order1.addItem(item1);
		order1.addItem(item2);
		
		order2.addItem(item3);
		order2.addItem(item4);
		
		user.addOrder(order1);
		user.addOrder(order2);
		
		UserReportVisitor reportVisitor = new UserReportVisitor();
		user.accept(reportVisitor);
		System.out.println(reportVisitor.getReport());
		
	}
}
