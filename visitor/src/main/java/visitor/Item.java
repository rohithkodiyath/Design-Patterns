package visitor;

public class Item implements Visitable{

	private String name;
	
	public Item(String name) {
		super();
		this.name = name;
	}

	public void accept(Visitor visitable) {
		System.out.println();
		visitable.visit(this);
	}

	public String getName() {
		return name;
	}

	
	
	

}
