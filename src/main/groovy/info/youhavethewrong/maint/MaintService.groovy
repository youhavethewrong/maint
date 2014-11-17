package info.youhavethewrong.maint

import info.youhavethewrong.health.Healthy
import info.youhavethewrong.maint.resource.*
import info.youhavethewrong.maint.storage.*
import io.dropwizard.Application
import io.dropwizard.setup.*
import javax.sql.DataSource

public class MaintService extends Application<MaintConfiguration> {

	public static void main( String[] args ) {
		new MaintService().run( args )
	}

	@Override
	public void initialize( Bootstrap<MaintConfiguration> boot ) {
	}

	@Override
	public void run( MaintConfiguration conf, Environment env ) throws Exception {
		DataSource maintDs = conf.getMaintDb().build( env.metrics(), "maintDb" )
		MaintenanceStorage maintStorage = new MySqlMaintenanceStorage( maintDs )
		UserStorage userStorage = new MySqlUserStorage( maintDs )
		env.jersey().register( new MaintResource( maintStorage ) )
		env.jersey().register( new UserResource( userStorage ) )
		env.healthChecks().register( "healthy", new Healthy() )
	}
}
