package rest.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import rest.factories.BuilderBasedFactory;
import rest.misc.User;
import rest.model.Cliente;
import rest.model.GestionaTusPedidos;
import rest.model.Persona;

public class UserDAOImpl implements UserDAO {

	List<User> lis = new Vector<>();
	BuilderBasedFactory<User> fac;
	private static GestionaTusPedidos gtp;
	
	public UserDAOImpl(BuilderBasedFactory<User> fac) {
		this.fac = fac;
	}
	
	@Override
	public List<User> listAll() {
		return lis;
	}

	@Override
	public User search(String id) {
		User user = null;
		for(int i = 0; i<lis.size()&&user==null;i++){
			if(id.equals(lis.get(i).getUsername())){
				user = lis.get(i);
			}
		}
		return user;
	}

	@Override
	public void read() throws FileNotFoundException {
		JSONArray  userArray;
		InputStream in = new FileInputStream(new File("resources/User.json"));
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		try {
			//Read and create Cliente
			User user;
			userArray = jsonInput.getJSONArray("User");
			for(int i = 0; i < userArray.length(); i++) {
				user = fac.createInstance(userArray.getJSONObject(i));
				lis.add(user);
			}

		}
		catch(JSONException e) {
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	public void create(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GenerateNewId() {
		// TODO Auto-generated method stub
		return null;
	}

}
