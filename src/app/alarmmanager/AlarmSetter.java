package app.alarmmanager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import app.database.AlarmDbAdapter;
import app.database.AlarmDbUtilities;
import app.morningalarm.Alarm;

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
	
	public void setAlarm(Alarm alarm){
		alarm = getAlarmUpToDate(alarm);
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
	
	private Alarm getAlarmUpToDate(Alarm alarm){
		Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(Long.parseLong(alarm.getTime()));
    	Calendar temp = Calendar.getInstance();
    	if(!temp.before(c)){
    		Log.d("DEBUG_TAG", "actualizeaza alarma");
    		temp.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
    		temp.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
    		if(!Calendar.getInstance().before(temp)){
    			temp.add(Calendar.DAY_OF_YEAR, 1);
    		}
    		temp.set(Calendar.SECOND, 0);
    		alarm.setTime(temp.getTimeInMillis() + "");
    	}
    	c.setTimeInMillis(Long.parseLong(alarm.getTime()));
    	DateFormat df=DateFormat.getDateTimeInstance();
		String time=df.format(c.getTime());
		Log.d("DEBUG_TAG","alarm updated to "+time );
    	AlarmDbUtilities.updateAlarm(mContext, alarm);
    	return alarm;
	}
	
	public void removeAlarm(String alarmId){
		Intent i = new Intent (mContext, OnAlarmReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(mContext,Integer.parseInt(alarmId), i, PendingIntent.FLAG_CANCEL_CURRENT);
		mAlarmManager.cancel(pi);
		Log.d("DEBUG_TAG", "alarm setted on "+ alarmId + "canceled");
	}
}
