package app.alarmmanager;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import app.database.AlarmDbAdapter;
import app.morningalarm.Alarm;

public class AlarmSetter {

	private Context mContext;
	private AlarmManager mAlarmManager;
	
	public AlarmSetter(Context context){
		mContext = context;
		mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	}
	
	public void setAlarm(Alarm alarm){
		Intent i = new Intent (mContext, OnAlarmReceiver.class);
		i.putExtra(AlarmDbAdapter.KEY_ID, alarm.getId());
		PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, i, PendingIntent.FLAG_ONE_SHOT);
		mAlarmManager.set(AlarmManager.RTC_WAKEUP, Long.parseLong(alarm.getTime()), pi);
		
		//log
		Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(Long.parseLong(alarm.getTime()));
    	DateFormat df=DateFormat.getTimeInstance(DateFormat.SHORT);
		String time=df.format(c.getTime());
		Log.d("DEBUG_TAG", "alarm setted on "+ time);
	}
}
