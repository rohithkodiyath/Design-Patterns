package cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
	
	static Logger LOGGER = LoggerFactory.getLogger(Driver.class);
	public static void main(String[] args) {
		AppManager.initDb(true);
		AppManager.initCache(3);
		//readAndWriteThroughStrategy();
		//readAndWriteThroughBehind();
		readAndWriteAround();
		
		
	}
	
	public static void readAndWriteAround() {
		AppManager.setCachingPolicy(CachePolicy.AROUND);
		User user1 = new User(1,"A");
		User user2 = new User(2,"B");
		User user3 = new User(3,"C");
		User user4 = new User(4,"D");
		AppManager.saveUser(user1);
		AppManager.saveUser(user2);
		AppManager.saveUser(user3);
		AppManager.saveUser(user4);
		AppManager.getUserByUserId(1);
		AppManager.getUserByUserId(2);
		AppManager.getUserByUserId(3);
		AppManager.getUserByUserId(4);
		//AppManager.getUserByUserId(1);
		LOGGER.info("Cached Elemets "+AppManager.getCachedElementIds());
	}
	
	public static void readAndWriteThroughBehind() {
		AppManager.setCachingPolicy(CachePolicy.BEHIND);
		User user1 = new User(1,"A");
		User user2 = new User(2,"B");
		User user3 = new User(3,"C");
		User user4 = new User(4,"D");
		AppManager.saveUser(user1);
		AppManager.saveUser(user2);
		AppManager.saveUser(user3);
		AppManager.saveUser(user4);
		AppManager.getUserByUserId(1);
		LOGGER.info("Cached Elemets "+AppManager.getCachedElementIds());
	}
	
	public static void readAndWriteThroughAside() {
		AppManager.setCachingPolicy(CachePolicy.ASIDE);
		User user1 = new User(1,"A");
		User user2 = new User(2,"B");
		User user3 = new User(3,"C");
		User user4 = new User(4,"D");
		AppManager.saveUser(user1);
		AppManager.saveUser(user2);
		AppManager.saveUser(user3);
		AppManager.saveUser(user4);
		AppManager.getUserByUserId(1);
		AppManager.getUserByUserId(2);
		AppManager.getUserByUserId(3);
		AppManager.getUserByUserId(4);
		LOGGER.info("Cached Elemets "+AppManager.getCachedElementIds());
		User user = AppManager.getUserByUserId(4);
		user = AppManager.getUserByUserId(2);
		user = AppManager.getUserByUserId(1);
		System.out.println(user);
		LOGGER.info("Cached Elemets "+AppManager.getCachedElementIds());
	}
	
	public static void readAndWriteThroughStrategy() {
		AppManager.setCachingPolicy(CachePolicy.THROUGH);
		User user1 = new User(1,"A");
		User user2 = new User(2,"B");
		User user3 = new User(3,"C");
		User user4 = new User(4,"D");
		AppManager.saveUser(user1);
		AppManager.saveUser(user2);
		AppManager.saveUser(user3);
		AppManager.saveUser(user4);
		AppManager.getUserByUserId(1);
		LOGGER.info("Cached Elemets "+AppManager.getCachedElementIds());
		
	}
	
 
	

}
