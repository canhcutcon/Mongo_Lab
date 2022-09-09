package Utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientConnectApplication {
	public static MongoClientConnectApplication instance = null;

	private MongoClient mongoClient;

	private MongoClientConnectApplication() {
		super();
		// TODO Auto-generated constructor stub
		try {
			ConnectionString connectionString = new ConnectionString(
					"mongodb+srv://VoThiTraGiang_19510771:giangvo19510771@cluster0.1odnozv.mongodb.net/test");
			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
					.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build()).build();
			mongoClient = MongoClients.create(settings);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static MongoClientConnectApplication getInstance() {
		if (instance == null)
			instance = new MongoClientConnectApplication();
		return instance;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

}
