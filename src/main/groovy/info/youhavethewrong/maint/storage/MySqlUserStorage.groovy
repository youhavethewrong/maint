package info.youhavethewrong.maint.storage;

import groovy.sql.Sql
import info.youhavethewrong.maint.model.*

import java.sql.SQLException

import javax.sql.DataSource

import org.slf4j.*

public class MySqlUserStorage implements UserStorage {
	private static final Logger log = LoggerFactory.getLogger(MySqlUserStorage.class)
	DataSource ds

	public MySqlUserStorage(DataSource ds) {
		this.ds = ds
	}

	@Override
	public User getUser(BigInteger id) {
		try {
			Sql sql = new Sql( ds )
			def result = sql.firstRow("select id,name from user where id = ?", [id])
			return new User(id: result.id, name: result.name)
		}
		catch (SQLException ex) {
			log.error("Failed during retrieval of all maintenance", ex)
			return null
		}
	}

	@Override
	public User createUser(String username) {
		try {
			Sql sql = new Sql( ds )
			def result = sql.executeInsert("insert into user (name) values (?)", [username])
			return new User(id: result[0][0], name: username)
		}
		catch (SQLException ex) {
			log.error("Failed during retrieval of all maintenance", ex)
			return null
		}
	}

	@Override
	public void deleteUser(BigInteger id) {
		try {
			Sql sql = new Sql( ds )
			sql.execute("delete from user where id = ?", [id])
		}
		catch (SQLException ex) {
			log.error("Failed during retrieval of all maintenance", ex)
			return null
		}
	}
}
