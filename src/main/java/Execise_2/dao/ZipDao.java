package Execise_2.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.Doc;

import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;

import Execise_2.busimpl.Mapper;
import Execise_2.entity.Zip;
import Utils.MongoClientConnectApplication;

public class ZipDao {
	private MongoCollection<Document> zipCol;

	public ZipDao() {
		// TODO Auto-generated constructor stub
		zipCol = MongoClientConnectApplication.getInstance().getMongoClient().getDatabase("sample_training")
				.getCollection("zips");
	}

	// Hiển thị n documents từ document thứ k
	public AggregateIterable<Document> showDocumentFromIndex(int n, int k) {
		return zipCol.aggregate(Arrays.asList(new Document("$skip", k - 1), new Document("$limit", n + 1)));
	}

	public Zip getDocumentByID(String id) {
		Document document = zipCol.find(Filters.eq("_id", new ObjectId(id))).first();
		return Mapper.exportToZip(document);
	}

	public String insertNewZip(Zip zip) {
		Document item = new Document(new Document("city", zip.getCity()).append("zip", zip.getZips())
				.append("loc", new Document("y", zip.getLocation().getX()).append("x", zip.getLocation().getY()))
				.append("pop", zip.getPop()).append("state", zip.getState()));
		InsertOneResult result = zipCol.insertOne(item);
		if (result.getInsertedId().isNull())
			return null;
		return item.getObjectId("_id").toHexString();
	}

//	Cập nhật thông tin của một document khi biết id
	public boolean updateDocumentByID(Zip zip, String id) {
		try {
			Document loc = new Document().append("x", zip.getLocation().getX()).append("y", zip.getLocation().getY());
			Bson updates = Updates.combine(Updates.set("city", zip.getCity()), Updates.set("state", zip.getState()),
					Updates.set("zips", zip.getState()), Updates.set("pop", zip.getState()), Updates.set("loc", loc),
					Updates.currentTimestamp("lastUpdated"));
			zipCol.updateOne(Filters.eq("_id", new ObjectId(id)), updates);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

//	Tìm các document có city là PALMER
	public List<Document> getZipByCity(String city) {

		return zipCol.find(eq("city", city)).into(new ArrayList<>());
	}
	
//	Tìm các document có dân số >100000
//	db.zips.find({pop:{$gt:100000}})
	public List<Document> getZipByPop(int pop) {
		
		Document filter = Document.parse("{pop:{$gt:"+ pop +"}}");		
		return zipCol.find(filter).into(new ArrayList<>());
	}

	public int getPopOfZipByNameCity(String name)
	{
		return (int)zipCol.find(Filters.eq("city", name)).first().get("pop");
	}
//	Tìm các thành phố có dân số từ 10 – 50
	public List<Document> getCityByPop(int popF, int popT){
		Document filter = Document.parse("{pop:{$gt:"+ popF +",$lt:"+ popT +"}}");
		return zipCol.find(filter).into(new ArrayList<>());
	}
	
//	Tìm tất cả các thành phố của bang MA có dân số trên 500
	public List<Document> getAllCityOgMAHavePop(){
		Document filter = Document.parse("{{state: \"MA\",pop: {$gt:500}}}");
		return zipCol.find(filter).into(new ArrayList<>());
	}
	
}
