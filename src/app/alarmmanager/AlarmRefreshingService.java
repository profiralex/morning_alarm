package app.alarmmanager;

import android.content.Intent;
import android.util.Log;
import app.database.AlarmDbUtilities;

public class AlarmRefreshingService extends WakeAlarmIntentService{
	public AlarmRefreshingService(){
		super("AlarmService");
	}
	/**
	 * seteaza din nou toate alarmele.
	 */
	@Override
	void doAlarmWork(Intent intent){
		Log.d("DEBUG_TAG", "alarm got in refreshing service "+ AlarmDbUtilities.fetchAllAlarms(this.getBaseContext()).size());
		AlarmSetter aSetter = new AlarmSetter(this.getBaseContext());
		aSetter.refreshAllAlarms();
	}

}
