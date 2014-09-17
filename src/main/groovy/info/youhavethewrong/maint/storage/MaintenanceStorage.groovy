package info.youhavethewrong.maint.storage

import info.youhavethewrong.maint.model.Maintenance

public interface MaintenanceStorage {
	public List<Maintenance> getAllMaintenance()

	public Maintenance logMaintenance(Maintenance maint)

	public Maintenance getMaintenance(String name)
}
