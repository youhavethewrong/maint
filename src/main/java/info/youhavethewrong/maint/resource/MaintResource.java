package info.youhavethewrong.maint.resource;

import info.youhavethewrong.maint.model.Maintenance;
import info.youhavethewrong.maint.storage.MaintenanceStorage;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Maintenance createMaintenance(Maintenance maint) {
		return storage.logMaintenance(maint);
	}
}
