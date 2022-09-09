package Execise_2.bus;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;

import Execise_2.entity.Zip;

public interface ZipService {

	public AggregateIterable<Document> getZipFromKToN(int n, int k);
	
	public String insertOneZip(Zip newZip);
	
	public Zip getZipByID(String id);
	
	public boolean updateZipByID(Zip zip, String id);
	
	public List<Document> getZipByCity(String city);
	
	public List<Document> getZipByPop(int pop);
	
	public int getPopOfZipByNameCity(String name);
	
	public List<Document> getCityByPop(int popF, int popT);
}
