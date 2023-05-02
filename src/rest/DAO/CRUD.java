package rest.DAO;

import java.io.FileNotFoundException;
import java.util.List;

public interface CRUD<T> {
	
	// Methods that all DAOs use
	List<T> listAll();
	T search(String id);
	void read() throws FileNotFoundException;
	void create(T t);
	void update(T t);
	void delete(T t);
	String GenerateNewId();

}
