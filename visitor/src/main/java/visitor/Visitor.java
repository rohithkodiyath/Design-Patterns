package visitor;

public interface Visitor {
	
	void visit(User user);
	
	void visit(Order order);
	
	void visit(Item item);

}
