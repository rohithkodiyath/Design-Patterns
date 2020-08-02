package visitor;

import java.util.ArrayList;
import java.util.List;

public class User implements Visitable{

	private String userName;
	private List<Order> orders = new ArrayList<Order>();

	public User(String userName) {
		this.userName = userName;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		for (Order order : orders) {
			order.accept(visitor);
		}
	}

	
	
	

}
