package app.alarmmanager;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import app.database.AlarmDbUtilities;
import app.morningalarm.Alarm;

public class AlarmRefreshingService extends WakeAlarmIntentService{
	public AlarmRefreshingService(){
		super("AlarmService");
	}
	
	@Override
	void doAlarmWork(Intent intent){
		
		ArrayList<Alarm> alarms = AlarmDbUtilities.fetchAllAlarms(this);
		Toast.makeText(this,"alarm got in boot service " + alarms.size(), Toast.LENGTH_LONG).show();
		Log.d("DEBUG", "alarm got in boot service " + alarms.size());
		Calendar now = Calendar.getInstance();
		Calendar when = Calendar.getInstance();
		for(Alarm alarm:alarms){
			when.setTimeInMillis(Long.parseLong(alarm.getTime()));
			if(when.before(now)){
				when.add(Calendar.DAY_OF_YEAR, 1);
				alarm.setTime(when.getTimeInMillis()+"");
				AlarmDbUtilities.updateAlarm(this, alarm);
			}
		}
		
		AlarmSetter aSetter = new AlarmSetter(this);
		aSetter.refreshAllAlarms();
	}

}
