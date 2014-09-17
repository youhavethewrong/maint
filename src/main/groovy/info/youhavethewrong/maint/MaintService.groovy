package info.youhavethewrong.maint

import info.youhavethewrong.maint.resource.MaintResource
import info.youhavethewrong.maint.storage.MaintenanceStorage
import info.youhavethewrong.maint.storage.MySqlMaintenanceStorage
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

public class MaintService extends Application<MaintConfiguration> {

	@Override
	public void initialize(Bootstrap<MaintConfiguration> boot) {
	}

	@Override
	public void run(MaintConfiguration conf, Environment env) throws Exception {
		MaintenanceStorage storage = new MySqlMaintenanceStorage(ds: conf
		.getMaintDb().build(env.metrics(), "maintDb"))
		env.jersey().register(new MaintResource(storage))
	}
}
