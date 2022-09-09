package Execise_2;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import Execise_2.busimpl.Mapper;
import Execise_2.busimpl.ZipServiceImpl;
import Execise_2.entity.Location;
import Execise_2.entity.Zip;
import Utils.MongoClientConnectApplication;

public class Test_Execise_2 {
	static ZipServiceImpl zipService = new ZipServiceImpl();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//					CONNECT DATABASE
		MongoClient mongoClient = MongoClientConnectApplication.getInstance().getMongoClient();

		mongoClient.listDatabaseNames().forEach(mongo -> System.out.println(mongo));

		MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_training");

		// get list zip fom k to n
		List<Zip> lstZip = new ArrayList<Zip>();

//		tranformDocumentToZip(lstZip);
//		lstZip.forEach(z -> System.out.println(z));
//		
//		insertOneZip();

//		updateOneZipByID();
//		getStringByCity();
//		getStringByPop();
//		getPopOfCityByName();
//		getCityByPopFromTo();
	}

	public static void tranformDocumentToZip(List<Zip> zips) {
		AggregateIterable<Document> lst = zipService.getZipFromKToN(2, 3);
		lst.forEach(zip -> zips.add(Mapper.exportToZip(zip)));
	}

	public static void insertOneZip() {

		System.out.println("=========== BEGIN INSERT =======");
		Zip zip = new Zip("TP HCM", "AL", "70000", 3000, new Location(86.621299, 34.268298));
		String id = zipService.insertOneZip(zip);
		if (id.isEmpty()) {
			System.out.println("Insert fail");
			return;
		}
		System.out.println(zipService.getZipByID(id));
		System.out.println("=============FINISH================");
	}

	public static void updateOneZipByID() {

		System.out.println("=========== BEGIN UPDATE =======");
		Zip zip = new Zip("TP HCM", "AL", "70000", 3000, new Location(86.621299, 34.268298));
		System.out.println(zip);
		zip.setCity("HCM");
		zip.setState("AZ");
		boolean update = zipService.updateZipByID(zip, "5c8eccc1caa187d17ca6ed16");
		if (!update) {
			System.out.println("update fail");
			return;
		}

		System.out.println("=============FINISH================");
	}

	public static void getStringByCity()
	{
		
		System.out.println("=========== GET ZIP BY CITY =======");
		List< Document> lsDocuments = zipService.getZipByCity("PALMER");
		lsDocuments.forEach(zip -> System.out.println(zip));
		System.out.println("=============FINISH================");
	}

	public static void getStringByPop()
	{
		System.out.println("=========== GET ZIP BY POP =======");
		List< Document> lsDocuments = zipService.getZipByPop(100000);
		lsDocuments.forEach(zip -> System.out.println(zip));
		System.out.println("=============FINISH================");
	}
	
	public static void getPopOfCityByName()
	{
		System.out.println("=========== GET Pop BY name =======");
		System.out.println(zipService.getPopOfZipByNameCity("FISHERS ISLAND"));
		System.out.println("=============FINISH================");
	}
	
	public static void getCityByPopFromTo() {
		System.out.println("=========== GET Pop BY POP =======");
		List< Document> lsDocuments = zipService.getCityByPop(10, 50);
		lsDocuments.forEach(zip -> System.out.println(zip));
		System.out.println("=============FINISH================");
	}
}
