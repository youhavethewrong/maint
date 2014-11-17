package info.youhavethewrong.maint.resource;

import info.youhavethewrong.maint.model.*;
import info.youhavethewrong.maint.storage.MaintenanceStorage;

import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/maint")
@Produces(MediaType.APPLICATION_JSON)
public class MaintResource {

	private MaintenanceStorage storage;

	public MaintResource(MaintenanceStorage storage) {
		this.storage = storage;
	}

	@Deprecated
	@GET
	public List<Maintenance> getMaintenancePerformed() {
		return storage.getAllMaintenance();
	}

	@GET
	@Path("/{id: \\d+}")
	public Maintenance getMaintenanceById(@PathParam("id") BigInteger id) {
		return storage.getMaintenance(id);
	}

	@GET
	@Path("/user/{userid}")
	public List<Maintenance> getMaintenancePerformedByUser(@PathParam("userid") BigInteger userid) {
		User user = new User();
		user.setId(userid);
		return storage.getAllMaintenanceByUser(user);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Maintenance createMaintenance(Maintenance maint) {
		return storage.logMaintenance(maint);
	}
}
