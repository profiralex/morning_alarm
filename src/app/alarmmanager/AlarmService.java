package app.alarmmanager;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import app.database.AlarmDbAdapter;
import app.database.AlarmDbUtilities;
import app.morningalarm.Alarm;
import app.tasks.LogicAlarmTask;
import app.tasks.MathAlarmTask;
import app.tasks.SimpleAlarmTask;

public class AlarmService extends WakeAlarmIntentService{
	public AlarmService(){
		super("AlarmService");
	}
	
	@Override
	void doAlarmWork(Intent intent){
		Log.d("DEBUG_TAG", "alarm got in service");
		String alarmId = intent.getExtras().getString(AlarmDbAdapter.KEY_ID);
		Alarm alarm = AlarmDbUtilities.fetchAlarm(this, alarmId);
		if(alarm != null && alarm.isEnabled() == Alarm.ALARM_ENABLED){		
			
			MathAlarmTask.setAlarm(alarm);
			Intent task = new Intent(this, SimpleAlarmTask.class);
			switch(Integer.parseInt(alarm.getWakeUpMode())){
				case 1: 
					task = new Intent(this, MathAlarmTask.class);
					break;
				case 3:
					task = new Intent(this, LogicAlarmTask.class);
					break;
			}
			task.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(task);
			Log.d("DEBUG_TAG", "activity must start");
		}else{
			AlarmSetter aSetter = new AlarmSetter(this);
			aSetter.removeAlarm(alarmId);
			Log.d("DEBUG_TAG", "alarm is null");
		}
		
		
	}

}
