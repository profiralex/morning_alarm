package app.alarmmanager;

import java.util.Calendar;

import android.content.Intent;
import android.util.Log;
import app.database.AlarmDbAdapter;
import app.database.AlarmDbUtilities;
import app.morningalarm.Alarm;
import app.tasks.AlarmTask;
import app.tasks.LogicAlarmTask;
import app.tasks.MathAlarmTask;
import app.tasks.SimpleAlarmTask;

public class AlarmService extends WakeAlarmIntentService{
	private static long lastTime = 0;
	public AlarmService(){
		super("AlarmService");
	}
	
	/**
	 * este apelat cind a fost primit un semnal de alarma
	 * metoda primeste prin extras parametrul ID dupa care cauta alarma in baza de date 
	 * si afla daca trebuie sa fie apelata activitatea de afisare sau trebuie resetata alarma 
	 * pe ziua urmatoare 
	 */
	@Override
	synchronized void doAlarmWork(Intent intent){
		Log.d("DEBUG_TAG", "alarm got in service");
		String alarmId = intent.getExtras().getString(AlarmDbAdapter.KEY_ID);
		AlarmSetter aSetter = new AlarmSetter(this);
		Alarm alarm = AlarmDbUtilities.fetchAlarm(this, alarmId);
		if(alarm != null && alarm.isEnabled() == Alarm.ALARM_ENABLED){		
			
			Log.d("DEBUG_TAG", alarm.getDaysOfWeek());
			Log.d("DEBUG_TAG", alarm.getRingtone());
			String daysOfWeek = alarm.getDaysOfWeek();
			int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			if(!AlarmTask.isActive() && (daysOfWeek.contains("#ALL#") || daysOfWeek.contains(today+"")))
			{
				lastTime = alarm.getTime();
				Intent task= new Intent(this, SimpleAlarmTask.class);
				switch(Integer.parseInt(alarm.getWakeUpMode())){
					case 1: 
						task = new Intent(this, MathAlarmTask.class);
						break;
					case 3:
						task = new Intent(this, LogicAlarmTask.class);
						break;
				}
				AlarmTask.setAlarm(alarm);
				AlarmTask.setActive();
				task.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(task);
				Log.d("DEBUG_TAG", "activity must start");
			}else{
				aSetter.setAlarm(alarm);
			}
		}else{
			aSetter.removeAlarm(alarmId);
			Log.d("DEBUG_TAG", "alarm is null");
		}	
	}
}
