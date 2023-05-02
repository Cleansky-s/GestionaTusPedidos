package rest.factories;

import org.json.JSONObject;

import rest.misc.User;
import rest.model.Cliente;

public class UserBuilder extends Builder<User> {

	public UserBuilder() {
		super("User");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected User createTheInstance(JSONObject data) {
		if (data == null)
			throw new IllegalArgumentException("Invalid value for createInstance: null");

		User user = new User(data.getString("username"),data.getString("password"));
		return user;
	}
}
