/**
 * 
 */
package cache;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rohith
 *
 */
public class AppManager {
	static Logger LOGGER = LoggerFactory.getLogger(AppManager.class);
    public static final int CACHE_SIZE = 3;
    public static CachePolicy cachePolicy ;
    private static DBManager dbManager;
	
	public static void initDb(boolean useVirtualDb) {
		dbManager = DBManager.getInstance();	
		dbManager.connectToDb();
		dbManager.useVirtualDB(useVirtualDb);
	}
	
	public static void initCache(int cacheSize) {
		CacheStore.initializeCache(cacheSize);
	}
	
	public static void setCachingPolicy(CachePolicy policy) {
		cachePolicy = policy;
		if(policy == CachePolicy.BEHIND) {
			Runtime.getRuntime().addShutdownHook(new Thread(CacheStore::flushCache));
		}
	}
	
	public static User getUserByUserId(int id) {
		if(cachePolicy == CachePolicy.THROUGH || cachePolicy == CachePolicy.AROUND) {
			return CacheStore.readThroughCache(id);
		}else if(cachePolicy == CachePolicy.BEHIND) {
			return CacheStore.readWithWriteBack(id);
		}else if(cachePolicy == CachePolicy.ASIDE) {
			return readWithAsidePolicy(id);
		}
		return null;
	}
	
	public static User saveUser(User user) {
		LOGGER.info("# Saving user by {} ,id {} ",cachePolicy,user.getId());
		if(cachePolicy == CachePolicy.THROUGH ) {
			return CacheStore.writeThroughCache(user);
		}else if(cachePolicy == CachePolicy.AROUND) {
			return CacheStore.writeAroundCache(user);
		}else if(cachePolicy == CachePolicy.BEHIND) {
			return CacheStore.writeBackCache(user);
		}else if(cachePolicy == CachePolicy.ASIDE) {
			return writeAsidePolicy(user);
		}
		return user;
	}
	
	
	
	private static User readWithAsidePolicy(final int id) {
		
		return Optional.ofNullable(CacheStore.get(id)).orElseGet(()->{
			Optional<User> user = Optional.of(dbManager.getUser(id));
			user.ifPresent((u)->{
				CacheStore.set(u);
			});
			return user.orElse(null);
		});
	}
	
	public static String getCachedElementIds() {
		return CacheStore.getCachedUsers().stream().map(User::getId).collect(Collectors.toList()).toString();
	}
	
	private static User writeAsidePolicy(User user) {
		LOGGER.info("# Saving user by write aside policy ");
		dbManager.upsert(user);
		CacheStore.invalidate(user.getId());
		return user;
	}
	
	
}
