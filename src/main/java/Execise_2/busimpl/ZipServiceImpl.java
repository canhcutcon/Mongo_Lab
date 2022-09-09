package Execise_2.busimpl;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;

import Execise_2.bus.ZipService;
import Execise_2.dao.ZipDao;
import Execise_2.entity.Zip;

public class ZipServiceImpl implements ZipService{
	ZipDao dao;
	public ZipServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new ZipDao();
	}

	@Override
	public AggregateIterable<Document> getZipFromKToN(int n, int k) {
		// TODO Auto-generated method stub
		return dao.showDocumentFromIndex(n, k);
	}

	@Override
	public String insertOneZip(Zip newZip) {
		// TODO Auto-generated method stub
		return dao.insertNewZip(newZip);
	}

	@Override
	public Zip getZipByID(String id) {
		// TODO Auto-generated method stub
		return dao.getDocumentByID(id);
	}

	@Override
	public boolean updateZipByID(Zip zip,String id) {
		// TODO Auto-generated method stub
		return dao.updateDocumentByID(zip,id);
	}

	@Override
	public List<Document> getZipByCity(String city) {
		// TODO Auto-generated method stub
		return dao.getZipByCity(city);
	}

	@Override
	public List<Document> getZipByPop(int pop) {
		// TODO Auto-generated method stub
		return dao.getZipByPop(pop);
	}

	@Override
	public int getPopOfZipByNameCity(String name) {
		// TODO Auto-generated method stub
		return dao.getPopOfZipByNameCity(name);
	}

	@Override
	public List<Document> getCityByPop(int popF, int popT) {
		// TODO Auto-generated method stub
		return dao.getCityByPop(popF, popT);
	}
	
	

}
