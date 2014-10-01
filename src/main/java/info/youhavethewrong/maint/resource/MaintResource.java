package info.youhavethewrong.maint.resource;

import info.youhavethewrong.maint.model.Maintenance;
import info.youhavethewrong.maint.storage.MaintenanceStorage;

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

	@GET
	public List<Maintenance> getMaintenancePerformed() {
		return storage.getAllMaintenance();
	}

	@GET
	@Path("/{id: \\d+}")
	public Maintenance getMaintenanceById(@PathParam("id") Integer id) {
		return storage.getMaintenance(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Maintenance createMaintenance(Maintenance maint) {
		return storage.logMaintenance(maint);
	}
}
