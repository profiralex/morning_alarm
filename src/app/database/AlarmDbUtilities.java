package app.database;

import java.util.ArrayList;

import android.database.Cursor;
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
	
	public static final void updateDb(AlarmDbAdapter mDbHelper, Alarm alarm){
		
	}
}
