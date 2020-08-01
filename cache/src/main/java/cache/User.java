/**
 * 
 */
package cache;

import java.security.acl.LastOwnerException;
import java.util.Objects;

import org.bson.Document;

/**
 * @author rohith
 *
 */
public class User {
	
	public static final String ID = "id";
	public static final String FNAME = "firstName";
	public static final String LNAME = "lastName";
	public static final String AGE = "age";
	
	
	
	
	
	private int id ;
	
	private String firstName;
	
	private String lastName;
	
	private int age;
	
	

	public User(int id, String firstName) {
		super();
		this.id = id;
		this.firstName = firstName;
	}
	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/** 
	 * get object from document
	 * @param {@link {@link Document}}
	 * @return {@link User}
	 */
	public static User getEntityFromDocument(Document doc) {
		Objects.nonNull(doc);
		User ret = new User();
		ret.setId(doc.getInteger(ID));
		ret.setAge(doc.getInteger(AGE));
		ret.setLastName(doc.getString(LNAME));
		ret.setFirstName(doc.getString(FNAME));
		return ret;
	}
	
	
	/** 
	 * get object from document
	 * @param {@link {@link Document}}
	 * @return {@link User}
	 */
	public static Document getDocumentFromEntity(User user) {
		Objects.nonNull(user);
		Document doc = new Document();
		doc.append(ID, user.getId());
		doc.append(AGE, user.getAge());
		doc.append(FNAME, user.getFirstName());
		doc.append(LNAME, user.getLastName());
		return doc;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}
	
	
	
	
	
}
