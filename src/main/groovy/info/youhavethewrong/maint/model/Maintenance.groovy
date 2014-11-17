package info.youhavethewrong.maint.model

import groovy.transform.Canonical

@Canonical
public class Maintenance {
	BigInteger id
	BigInteger userid
	String durableGood
	Date date
	String notes
}
