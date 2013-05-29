package app.alarmmanager;

import java.util.Calendar;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import app.database.AlarmDbAdapter;
import app.database.AlarmDbUtilities;
import app.morningalarm.Alarm;
import app.morningalarm.R;
import app.tasks.AlarmTask;
import app.tasks.MathAlarmTask;

public class AlarmService extends WakeAlarmIntentService{
	public AlarmService(){
		super("AlarmService");
	}
	
	@Override
	void doAlarmWork(Intent intent){
		Toast.makeText(this,"alarm got", Toast.LENGTH_SHORT).show();
		Log.d("DEBUG_TAG", "alarm got in service");
		
		String alarmId = intent.getExtras().getString(AlarmDbAdapter.KEY_ID);
		Alarm alarm = AlarmDbUtilities.fetchAlarm(this, alarmId);
		if(alarm != null && alarm.isEnabled() == Alarm.ALARM_ENABLED){		
			
			MathAlarmTask.setAlarm(alarm);
			
			Intent task = new Intent(this, MathAlarmTask.class);
			task.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(task);
			
			Log.d("DEBUG_TAG", "activity must start");
		}else{
			Log.d("DEBUG_TAG", "alarm is null");
		}
		
		
	}

}
