package app.alarmmanager;

import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;
import app.database.AlarmDbAdapter;
import app.database.AlarmDbUtilities;
import app.morningalarm.Alarm;

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
			
			Calendar when = Calendar.getInstance();
			when.setTimeInMillis(Long.parseLong(alarm.getTime()));
			when.add(Calendar.DAY_OF_YEAR, 1);
			alarm.setTime(when.getTimeInMillis()+"");
			
			AlarmDbUtilities.updateAlarm(this, alarm);
			AlarmSetter aSetter = new AlarmSetter(this);
			aSetter.setAlarm(alarm);
			
			Uri alarmSound = Uri.parse(alarm.getRingtone());
			//Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
			//r.play();
			Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(5000);
			
			Log.d("DEBUG_TAG", "alarm is not null");
		}
		
		Log.d("DEBUG_TAG", "alarm is null");
	}

}
