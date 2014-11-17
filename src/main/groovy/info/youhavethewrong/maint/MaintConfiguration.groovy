package info.youhavethewrong.maint;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MaintConfiguration extends Configuration {
	
	@JsonProperty("maintDb")
	private DataSourceFactory maintDb;
	
	public DataSourceFactory getMaintDb() {
		return maintDb;
	}
}
