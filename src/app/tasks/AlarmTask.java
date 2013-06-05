package app.tasks;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.WindowManager;
import app.alarmmanager.AlarmSetter;
import app.morningalarm.Alarm;

public abstract class AlarmTask extends Activity{

	protected static Alarm alarm;
	protected Ringtone ringtone;
	protected Vibrator vibrator;
	protected boolean finishAlarm;
	protected boolean snooze;
	protected Dialog dialog;
	PowerManager pm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setSignals();
		
		dialog = new Dialog(this);
		solveCondition();
		
		Thread thread = new Thread() {
			public synchronized void run() {
				Log.d("DEBUG_TAG", "thread running");
				Calendar whenToTurnOff = Calendar.getInstance();
				whenToTurnOff.add(Calendar.MINUTE, 2);
				pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
				finishAlarm = false;
				snooze = false;
				
				try {
					wait(1000);
				} catch (InterruptedException e) {
				}
			
				
				while (!(finishAlarm == true || snooze == true 
						|| whenToTurnOff.before(Calendar.getInstance()) || pm.isScreenOn() == false)) {
					try {
						wait(200);
						vibrator.vibrate(100);
					} catch (InterruptedException e) {
					}
				}
				Log.d("DEBUG_TAG", "updating alarm");
				dialog.setCancelable(true);
				dialog.dismiss();
				ringtone.stop();
				vibrator.cancel();
				if (finishAlarm) {
					Log.d("DEBUG_TAG", "Set alarm on next day");
					AlarmSetter aSetter = new AlarmSetter(AlarmTask.this);
					aSetter.setAlarm(alarm);
				}else{
					Log.d("DEBUG_TAG", "Snooze");
				}
				AlarmTask.this.finish();
				Log.d("DEBUG_TAG", "thread over");
			}
		};
		thread.start();

	}

	protected void setSignals() {
		Uri sound = Uri.parse(alarm.getRingtone());
		ringtone = RingtoneManager.getRingtone(AlarmTask.this, sound);
		ringtone.play();
		vibrator = (Vibrator) AlarmTask.this.getSystemService(Context.VIBRATOR_SERVICE);
	}

	protected abstract void solveCondition();

	public static Alarm getAlarm() {
		return alarm;
	}

	public static void setAlarm(Alarm newAlarm) {
		alarm = newAlarm;
	}
	
	
	
}
