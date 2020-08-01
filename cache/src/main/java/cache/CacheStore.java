/**
 * 
 */
package cache;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rohith
 *
 */
public class CacheStore  {
	static final Logger LOGGER = LoggerFactory.getLogger(AppManager.class);
	private static LruCache<Integer, User> cache;
	private static DBManager dbManager;
	
	public static void initializeCache(int capacity) {
		if(cache == null) {
			cache = new LruCache<Integer, User>();
			cache.setCapacity(capacity);
			dbManager = DBManager.getInstance();
		}
	} 
	
	public static User readThroughCache(Integer userId ) {
		if( cache.containsKey(userId) ) {
			return cache.get(userId);
		}
		User userFromDb = dbManager.getUser(userId);
		if(userFromDb != null)
			cache.set(userFromDb.getId(), userFromDb);
		return userFromDb;
	}
	
	public static void invalidate(int userId) {
		if(cache.containsKey(userId)) {
			cache.invalidate(userId);
		}
	} 
	
	public static User writeThroughCache(User user) {
		if( cache.containsKey(user.getId()) ) {
			user = cache.set(user.getId(), user);
		}else {
			dbManager.upsert(user);
			cache.set(user.getId(), user);
		}
		return user;
	}
	
	public static void flushCache() {
		LOGGER.info("# Flushing data to db");
		Optional.ofNullable(cache).ifPresent((cache)->{
			cache.getAsList().stream().map(User::getId).forEach((id)->{
				User user = CacheStore.get(id);
				dbManager.upsert(user);
			});
		});
		cache.clearCache();
	}
	
	public static User get(int id) {
		return cache.get(id);
	}
	
	public static User set(User user) {
		return cache.set(user.getId(), user);
	}
	
	public static User writeAroundCache(User user) {
		if( cache.containsKey(user.getId()) ) {
			cache.invalidate(user.getId());
		}else {
			dbManager.upsert(user);
		}
		return user;
	}
	
	public static User writeBackCache(User user) {
		if( cache.containsKey(user.getId()) ) {
			cache.set(user.getId(),user);
		}else {
			if(cache.isFull()) {
				User lruUser = cache.getLru();
				if (lruUser != null)
					dbManager.upsert(lruUser);
			}
			cache.set(user.getId(), user);
		}
		return user;
	}
	
	public static List<User> getCachedUsers() {
		return cache.getAsList();	
	}
	
	public static User readWithWriteBack(int id) {
		if( cache.containsKey(id) ) {
			return cache.get(id);
		}
		User user = dbManager.getUser(id);
		if(user == null) return null;
		User lruUser;
		if(cache.isFull() && (lruUser = cache.getLru()) != null) {
			dbManager.upsert(lruUser);
		}
		cache.set(user.getId(), user);
		return user;
	}
}
