package visitor;

import java.util.ArrayList;
import java.util.List;

public class Order implements Visitable {

	private int orderNumber;
	private List<Item> items = new ArrayList<Item>();

	public Order(int orderNumber) {
		super();
		this.orderNumber = orderNumber;
	}
	
	

	public int getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}



	public void addItem(Item item) {
		items.add(item);
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		for (Item item : items) {
			item.accept(visitor);
		}
	}

}
