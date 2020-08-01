/**
 * 
 */
package cache;

import java.util.Hashtable;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import static cache.User.*;

/**
 * @author rohith
 *
 */
/**
 * @author rohith
 *
 */
public class DBManager {
	
	static Logger LOGGER = LoggerFactory.getLogger(DBManager.class);
	private boolean useMongoDb = false;
	private static final String CONNECTION_URL = "mongodb+srv://<password>@<cluster>/<db>";
	private static final String COLLECTION_NAME = "<collection name>";
	private MongoDatabase db = null;
	private Hashtable<Integer, User> usersInoDb = new Hashtable<Integer, User>(); 
	
	private static DBManager instance;
	
	/**
	 * prevent object creation
	 */
	private  DBManager() {
		
	}
	
	/**
	 * Static factory for single ton creation
	 * @return DBManager
	 */
	public static DBManager getInstance() {
		if( instance == null ) instance = new DBManager();
		return instance;
	}
	
	
	
	/**
	 * Connect to DB
	 */
	public  void connectToDb() {
		LOGGER.info("# connecting to database");
		ConnectionString connString = new ConnectionString(CONNECTION_URL);
		MongoClientSettings settings = MongoClientSettings.builder()
			    .applyConnectionString(connString)
			    .retryWrites(true)
			    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		db = mongoClient.getDatabase("test");
		LOGGER.info("# Connection established to DB");
	}
	
	
	/**
	 * use virtual db for saving data
	 */
	public void useVirtualDB(boolean useVirtualDb) {
		useMongoDb = ! useVirtualDb;
		LOGGER.info("# Data base types is set to ", (useMongoDb ? "MONGODB":"VIRTUALDB"));
	}
	
	
	/** 
	 * Retrieve user with id
	 * @param id
	 * @return {@link User}
	 */
	public  User getUser(int id) {
		LOGGER.info("# getting info from the db for the id {}",id);
		if( useMongoDb ) { 
			return usersInoDb.get(id);
		}
		if( db ==  null ) connectToDb();
		MongoCollection<Document> col = db.getCollection(COLLECTION_NAME);
		FindIterable<Document> iterable = col.find(eq(ID,id));
		Document document = iterable.first();
		if(document == null) return null;
		return User.getEntityFromDocument(document);
		
		
	}
	
	/** 
	 * Retrieve user with id
	 * @param id
	 * @return {@link User}
	 */
	public  User upsert (User user) {
		LOGGER.info("# upserting info {}",user);
		if( useMongoDb ) { 
			return usersInoDb.put(user.getId(), user);
		}
		if( db ==  null ) connectToDb();
		MongoCollection<Document> col = db.getCollection(COLLECTION_NAME);
		UpdateResult result = col.updateOne(eq(ID,user.getId()),new Document("$set",User.getDocumentFromEntity(user)), new UpdateOptions().upsert(true));
		return user;
	}

	/**
	 * @param args
	 */ 
	public static void main(String[] args) {
		DBManager dbManager = DBManager.getInstance();
		User user = new User();
		user.setAge(31);
		user.setFirstName("Rohith");
		user.setLastName("KP");
		user.setId(1);
		dbManager.upsert(user);
		user = null;
		user  = dbManager.getUser(1);
		System.out.println(user);

	}

}
