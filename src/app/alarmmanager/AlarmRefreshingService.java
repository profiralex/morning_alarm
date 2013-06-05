package app.alarmmanager;

import android.content.Intent;
import android.util.Log;

public class AlarmRefreshingService extends WakeAlarmIntentService{
	public AlarmRefreshingService(){
		super("AlarmService");
	}
	
	/**
	 * seteaza din nou toate alarmele
	 */
	@Override
	void doAlarmWork(Intent intent){
		Log.d("DEBUG_TAG", "alarm got in boot service ");
		AlarmSetter aSetter = new AlarmSetter(this);
		aSetter.refreshAllAlarms();
	}

}
