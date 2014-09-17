package info.youhavethewrong.maint.storage;

import groovy.sql.Sql
import info.youhavethewrong.maint.model.Maintenance

import javax.sql.DataSource

public class MySqlMaintenanceStorage implements MaintenanceStorage {

	DataSource ds
	String table = "maintenance"

	public List<Maintenance> getAllMaintenance() {
		List<Maintenance> allWork = []
		Sql sql = new Sql(ds)
		sql.rows("select id,durableGood,date,notes from $table").each { result ->
			allWork.add(new Maintenance(id: result.id, durableGood: result.durableGood, date: result.date, notes: result.notes))
		}

		return allWork
	}

	public Maintenance logMaintenance(Maintenance maint) {
		Sql sql = new Sql(ds)
		sql.executeInsert("insert into $table (durableGood,date,notes) values (?,?,?)", [
			maint.durableGood,
			maint.date,
			maint.notes
		])
		return null;
	}

	public Maintenance getMaintenance(String name) {
		return null;
	}
}
