package info.youhavethewrong.maint.storage

import info.youhavethewrong.maint.model.Maintenance

import java.text.*

public interface MaintenanceStorage {
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

	public List<Maintenance> getAllMaintenance()

	public Maintenance logMaintenance(Maintenance maint)

	public Maintenance getMaintenance(Integer id)
}
