package Execise_2.busimpl;

import org.bson.Document;

import Execise_2.entity.Location;
import Execise_2.entity.Zip;

public class Mapper {

	public static Zip exportToZip(Document doc) {

		Document locationDoc = (Document) doc.get("loc");
		Location location = new Location(locationDoc.getDouble("x"), locationDoc.getDouble("y"));

		return new Zip(doc.getObjectId("_id").toHexString(), doc.getString("city"), doc.getString("state"),
				doc.getString("zips"), doc.getInteger("pop", 0), location);
	}

	public static Document transformToBson(Zip zip) {
		return new Document(new Document("city", zip.getCity())
				.append("zip", zip.getZips())
				.append("_id", zip.getId())
				.append("loc", new Document("y", zip.getLocation().getX()).append("x", zip.getLocation().getY()))
				.append("pop", zip.getPop()).append("state", zip.getState()));
	}

}
