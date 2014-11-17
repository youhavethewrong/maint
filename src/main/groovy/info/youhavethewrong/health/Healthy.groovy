package info.youhavethewrong.health;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result

public class Healthy extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		return Result.healthy("Version 1.0");
	}
}
