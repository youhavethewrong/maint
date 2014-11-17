package info.youhavethewrong.maint.storage;

import static org.junit.Assert.*
import groovy.sql.Sql
import info.youhavethewrong.maint.model.Maintenance
import info.youhavethewrong.maint.model.User

import javax.sql.DataSource

import org.junit.*

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource

public class MySqlUserStorageTest {

	MySqlUserStorage storage

	@Before
	public void setUp() {
		storage = new MySqlUserStorage(getDataSource())
	}

	@After
	public void tearDown() {
		Sql sql = new Sql(getDataSource())
		sql.execute("""delete from user""")
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
	public void shouldCreateUser() {
		User bob = storage.createUser("Bob")
		assertTrue(bob.id == 1)
		assertEquals("Bob", bob.name)
	}

	@Test
	public void shouldGetUser() {
		User robert = storage.createUser("Robert")
		User storedRobert = storage.getUser(robert.id)
		assertEquals(robert.id, storedRobert.id)
		assertEquals(robert.name, storedRobert.name)
	}

	private DataSource getDataSource() {
		MysqlDataSource ds = new MysqlDataSource()
		ds.url = "jdbc:mysql://bastion:3306/maint_test"
		ds.user = "maint"
		return ds
	}
}
