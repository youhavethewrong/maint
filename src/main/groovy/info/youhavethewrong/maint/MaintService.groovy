package info.youhavethewrong.maint

import info.youhavethewrong.maint.resource.MaintResource
import info.youhavethewrong.maint.storage.*
import io.dropwizard.Application
import io.dropwizard.setup.*

public class MaintService extends Application<MaintConfiguration> {

	public static void main( String[] args ) {
		new MaintService().run( args )
	}

	@Override
	public void initialize( Bootstrap<MaintConfiguration> boot ) {
	}

	@Override
	public void run( MaintConfiguration conf, Environment env ) throws Exception {
		MaintenanceStorage storage = new MySqlMaintenanceStorage( conf.getMaintDb().build( env.metrics(), "maintDb" ) )
		env.jersey().register( new MaintResource( storage ) )
	}
}
