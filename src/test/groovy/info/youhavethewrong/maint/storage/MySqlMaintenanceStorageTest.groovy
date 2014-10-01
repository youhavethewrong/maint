package info.youhavethewrong.maint.storage;

import static org.junit.Assert.*
import groovy.sql.Sql
import info.youhavethewrong.maint.model.Maintenance

import javax.sql.DataSource

import org.junit.*

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource

public class MySqlMaintenanceStorageTest {

	MySqlMaintenanceStorage storage

	@Before
	public void setUp() {
		storage = new MySqlMaintenanceStorage(getDataSource())
	}

	@After
	public void tearDown() {
		Sql sql = new Sql(getDataSource())
		sql.execute("""delete from log""")
		sql.close()
	}

	@Test
	public void shouldGetConnection() {
		Sql sql = new Sql(storage.ds)
		def result = sql.rows("select 42 as answer")
		assertEquals(42L, result.answer[0])
		sql.close()
	}

	@Test
	public void shouldAddMaintenanceLogEntry() {
		Maintenance maint = getOil()
		Maintenance stored = storage.logMaintenance(maint)
		assertTrue(stored.id > 0)
		assertEquals(maint.durableGood, stored.durableGood)
		assertEquals(maint.date, stored.date)
		assertEquals(maint.notes, stored.notes)
	}

	@Test
	public void shouldGetMaintenanceList() {
		storeMaintenanceEntries()
		List<Maintenance> allEntries = storage.getAllMaintenance()
		assertEquals(2, allEntries.size())
		assertEquals(getAirFilter().durableGood, allEntries.get(0).durableGood)
		assertEquals(getOil().durableGood, allEntries.get(1).durableGood)
	}

	private void storeMaintenanceEntries() {
		storage.logMaintenance(getAirFilter())
		storage.logMaintenance(getOil())
	}

	private Maintenance getOil() {
		Maintenance maint = new Maintenance()
		maint.durableGood = "Motorcycle"
		maint.date = Calendar.instance.getTime()
		maint.notes = "34510 miles: Changed oil."
		maint
	}

	private Maintenance getAirFilter() {
		Maintenance maint = new Maintenance()
		maint.durableGood = "House"
		maint.date = Calendar.instance.getTime()
		maint.notes = "Changed HVAC air filter.  Also noted this date on the filter."
		maint
	}

	private DataSource getDataSource() {
		MysqlDataSource ds = new MysqlDataSource()
		ds.url = "jdbc:mysql://bastion:3306/maint_test"
		ds.user = "maint"
		return ds
	}
}
