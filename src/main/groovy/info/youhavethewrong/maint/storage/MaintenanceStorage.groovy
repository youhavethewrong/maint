package info.youhavethewrong.maint.storage

import info.youhavethewrong.maint.model.*

import java.text.*

public interface MaintenanceStorage {
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

	@Deprecated
	public List<Maintenance> getAllMaintenance()
	
	public List<Maintenance> getAllMaintenanceByUser(User user)

	public Maintenance logMaintenance(Maintenance maint)

	public Maintenance getMaintenance(BigInteger id)
}
