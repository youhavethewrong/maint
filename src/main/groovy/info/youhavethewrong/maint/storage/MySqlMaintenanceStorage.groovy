package info.youhavethewrong.maint.storage;

import groovy.sql.Sql
import info.youhavethewrong.maint.model.*

import java.sql.SQLException

import javax.sql.DataSource

import org.slf4j.*

public class MySqlMaintenanceStorage implements MaintenanceStorage {
	private static final Logger log = LoggerFactory.getLogger(MySqlMaintenanceStorage.class)
	DataSource ds

	public MySqlMaintenanceStorage(DataSource ds) {
		this.ds = ds
	}

	/**
	 * Use getAllMaintenanceByUser instead
	 */
	@Deprecated
	@Override
	public List<Maintenance> getAllMaintenance() {
		try {
			List<Maintenance> allWork = []
			Sql sql = new Sql(ds)
			sql.rows("select id,durableGood,date,notes from log").each { result ->
				allWork.add(new Maintenance(id: result.id, durableGood: result.durableGood, date: result.date, notes: result.notes))
			}

			return allWork
		}
		catch (SQLException ex) {
			log.error("Failed during retrieval of all maintenance", ex)
			return null
		}
	}

	@Override
	public List<Maintenance> getAllMaintenanceByUser(User user) {
		try {
			List<Maintenance> allWork = []
			Sql sql = new Sql(ds)
			sql.rows("select id,userid,durableGood,date,notes from log where userid = ?", [user.id]).each { result ->
				allWork.add(new Maintenance(id: result.id, userid: result.userid, durableGood: result.durableGood, 
											date: result.date, notes: result.notes))
			}

			return allWork
		}
		catch (SQLException ex) {
			log.error("Failed during retrieval of all maintenance", ex)
			return null
		}
	}

	@Override
	public Maintenance logMaintenance(Maintenance maint) {
		try {
			Sql sql = new Sql(ds)
			def result = sql.executeInsert("insert into log (userid,durableGood,date,notes) values (?,?,?,?)", [
				maint.userid,
				maint.durableGood,
				maint.date,
				maint.notes
			])

			Maintenance stored = new Maintenance()
			stored.id = result[0][0]
			stored.userid = maint.userid
			stored.durableGood = maint.durableGood
			stored.date = maint.date
			stored.notes = maint.notes

			return stored
		}
		catch (SQLException ex) {
			log.error("Failed during log of maintenance $maint", ex)
			return null
		}
	}

	@Override
	public Maintenance getMaintenance(BigInteger id) {
		try {
			Sql sql = new Sql(ds)
			def result = sql.firstRow("select id,userid,durableGood,date,notes from log where id=?", [id])

			if(result != null ) {
				Maintenance stored = new Maintenance()
				stored.id = result.id
				stored.userid = maint.userid
				stored.durableGood = result.durableGood
				stored.date = result.date
				stored.notes = result.notes

				return stored
			}
			return null
		}
		catch(SQLException ex) {
			log.error("Failed during retrieval of maint with id $id", ex)
			return null
		}
	}
}
