package info.youhavethewrong.maint.storage

import info.youhavethewrong.maint.model.*

import java.text.*

public interface UserStorage {
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

	public User getUser(BigInteger id)

	public User createUser(String username)

	public void deleteUser(BigInteger id)
}
