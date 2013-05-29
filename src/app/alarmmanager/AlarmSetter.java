package app.alarmmanager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import app.database.AlarmDbAdapter;
import app.database.AlarmDbUtilities;
import app.morningalarm.Alarm;
import app.morningalarm.R;

public class AlarmSetter {

	private Context mContext;
	private AlarmManager mAlarmManager;
	private static final long FIVE_MINUTES = 300000L;
	public AlarmSetter(Context context){
		mContext = context;
		mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	}
	
	public void refreshAllAlarms(){
		ArrayList <Alarm> alarms = AlarmDbUtilities.fetchAllAlarms(mContext);
		for(Alarm alarm: alarms){
			setAlarm(alarm);
		}
	}
	
	/*public void setAlarm(Alarm alarm){
		Intent i = new Intent (mContext, OnAlarmReceiver.class);
		i.putExtra(AlarmDbAdapter.KEY_ID, alarm.getId());
		PendingIntent pi = PendingIntent.getBroadcast(mContext,Integer.parseInt(alarm.getId()), i, PendingIntent.FLAG_ONE_SHOT);
		mAlarmManager.set(AlarmManager.RTC_WAKEUP, Long.parseLong(alarm.getTime()), pi);
		//log
		Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(Long.parseLong(alarm.getTime()));
    	DateFormat df=DateFormat.getTimeInstance(DateFormat.SHORT);
		String time=df.format(c.getTime());
		
		Log.d("DEBUG_TAG", "alarm setted on "+ time);
	}*/
	
	public void setAlarm(Alarm alarm){
		Intent i = new Intent (mContext, OnAlarmReceiver.class);
		i.putExtra(AlarmDbAdapter.KEY_ID, alarm.getId());
		PendingIntent pi = PendingIntent.getBroadcast(mContext,Integer.parseInt(alarm.getId()), i, PendingIntent.FLAG_UPDATE_CURRENT);
		mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Long.parseLong(alarm.getTime()), FIVE_MINUTES, pi);
		//log
		Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(Long.parseLong(alarm.getTime()));
    	DateFormat df=DateFormat.getTimeInstance(DateFormat.SHORT);
		String time=df.format(c.getTime());
		
		Log.d("DEBUG_TAG", "alarm setted on "+ time);
	}
}
