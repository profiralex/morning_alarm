package app.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import app.alarmmanager.AlarmSetter;
import app.morningalarm.Alarm;

public class AlarmDbUtilities {
	
	public static final ArrayList<Alarm> fetchCursor(Cursor c){
		ArrayList<Alarm> arr = new ArrayList<Alarm>();
		if (c.moveToFirst()){
			do{
				String id = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_ID));
				Integer enabled = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_ENABLED)));
				String time = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_TIME));
				String daysOfWeek = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DAYS_OF_WEEK));
				String duration = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DURATION));
				String wakeUpMode = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_WAKE_UP_MODE));
				String ringtone = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_RINGTONE));
				String description = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DESCRIPTION));
				arr.add(new Alarm(id, enabled, description, time, daysOfWeek, duration, wakeUpMode, ringtone));
			}while(c.moveToNext());
		}
		c.close();
		return arr;
	}
	
	public static final ArrayList<Alarm> fetchAllAlarms(Context context){
		ArrayList<Alarm> arr = new ArrayList<Alarm>();
		AlarmDbAdapter mDbHelper = new AlarmDbAdapter(context);
        mDbHelper.open();
        Cursor c = mDbHelper.fetchAllAlarms();
		if (c.moveToFirst()){
			do{
				String id = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_ID));
				Integer enabled = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_ENABLED)));
				String time = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_TIME));
				String daysOfWeek = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DAYS_OF_WEEK));
				String duration = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DURATION));
				String wakeUpMode = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_WAKE_UP_MODE));
				String ringtone = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_RINGTONE));
				String description = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DESCRIPTION));
				arr.add(new Alarm(id, enabled, description, time, daysOfWeek, duration, wakeUpMode, ringtone));
			}while(c.moveToNext());
		}
		c.close();
		mDbHelper.close();
		return arr;
	}

	public static final Alarm fetchAlarm(Context context, String alarmId){
		Alarm alarm = null;
		AlarmDbAdapter mDbHelper = new AlarmDbAdapter(context);
        mDbHelper.open();
        Cursor c = mDbHelper.fetchAlarm(alarmId);
		if (c.moveToFirst()){
				String id = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_ID));
				Integer enabled = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_ENABLED)));
				String time = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_TIME));
				String daysOfWeek = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DAYS_OF_WEEK));
				String duration = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DURATION));
				String wakeUpMode = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_WAKE_UP_MODE));
				String ringtone = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_RINGTONE));
				String description = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DESCRIPTION));
				alarm = new Alarm(id, enabled, description, time, daysOfWeek, duration, wakeUpMode, ringtone);
		}
		c.close();
		mDbHelper.close();
		return alarm;
	}

	public static final Alarm fetchNewAlarm(Context context){
		Alarm alarm = null;
		AlarmDbAdapter mDbHelper = new AlarmDbAdapter(context);
        mDbHelper.open();
        mDbHelper.createAlarm();
        Cursor c = mDbHelper.fetchNewAlarm();
		if (c.moveToFirst()){
				String id = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_ID));
				Integer enabled = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_ENABLED)));
				String time = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_TIME));
				String daysOfWeek = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DAYS_OF_WEEK));
				String duration = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DURATION));
				String wakeUpMode = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_WAKE_UP_MODE));
				String ringtone = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_RINGTONE));
				String description = c.getString(c.getColumnIndexOrThrow(AlarmDbAdapter.KEY_DESCRIPTION));
				alarm = new Alarm(id, enabled, description, time, daysOfWeek, duration, wakeUpMode, ringtone);
		}
		c.close();
		mDbHelper.close();
		return alarm;
	}
	
	public static final void deleteAlarm(Context context, Alarm alarm){
		AlarmDbAdapter mDbHelper = new AlarmDbAdapter(context);
        mDbHelper.open();
        mDbHelper.deleteAlarm(alarm);
        mDbHelper.close();
        AlarmSetter aSetter = new AlarmSetter(context);
        aSetter.removeAlarm(alarm.getId());
	}
	
	public static final void deleteAll(Context context){
		ArrayList<Alarm> alarms = fetchAllAlarms(context);
		AlarmSetter aSetter = new AlarmSetter(context);
		for(Alarm a: alarms ){
	        aSetter.removeAlarm(a.getId());
		}
		AlarmDbAdapter mDbHelper = new AlarmDbAdapter(context);
        mDbHelper.open();
        mDbHelper.deletAll();
        mDbHelper.close();
        
	}
	
	public static final void updateAlarm(Context context, Alarm alarm){
		AlarmDbAdapter mDbHelper = new AlarmDbAdapter(context);
        mDbHelper.open();
        mDbHelper.updateAlarm(alarm);
        mDbHelper.close();
	}
}
