package info.youhavethewrong.maint.resource;

import info.youhavethewrong.maint.model.User;
import info.youhavethewrong.maint.storage.UserStorage;

import java.math.BigInteger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	private UserStorage storage;

	public UserResource(UserStorage storage) {
		this.storage = storage;
	}

	@GET
	@Path("/{id}")
	public User getUser(@PathParam("id") BigInteger id) {
		return storage.getUser(id);
	}

	@POST
	@Path("/{username}")
	public User createUser(@PathParam("username") String username) {
		return storage.createUser(username);
	}

	@DELETE
	@Path("/{id}")
	public void deleteUser(@PathParam("id") BigInteger id) {
		storage.deleteUser(id);
	}
}
