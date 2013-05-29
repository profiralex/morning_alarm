package app.tasks;

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import app.alarmmanager.AlarmSetter;
import app.database.AlarmDbUtilities;
import app.morningalarm.Alarm;
import app.morningalarm.R;

public abstract class AlarmTask extends Activity{

	protected static Alarm alarm;
	protected Ringtone ringtone;
	protected Vibrator vibrator;
	protected boolean finishAlarm;
	protected Dialog dialog;
	PowerManager pm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		setSignals();
		solveCondition();
		
		Thread thread = new Thread() {
			public void run() {
				Log.d("DEBUG_TAG", "thread running");
				Calendar whenToTurnOff = Calendar.getInstance();
				whenToTurnOff.add(Calendar.MINUTE, 2);
				pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
				
				while (!(finishAlarm == true
						|| whenToTurnOff.before(Calendar.getInstance()) || !pm.isScreenOn())) {

				}
				Log.d("DEBUG_TAG", "updating alarm");
				dialog.setCancelable(true);
				dialog.dismiss();
				ringtone.stop();
				vibrator.cancel();
				if (finishAlarm) {
					Log.d("DEBUG_TAG", "Set alarm on next day");
					Calendar when = Calendar.getInstance();
					when.setTimeInMillis(Long.parseLong(alarm.getTime()));
					when.add(Calendar.DAY_OF_YEAR, 1);
					alarm.setTime(when.getTimeInMillis() + "");
					AlarmDbUtilities.updateAlarm(AlarmTask.this, alarm);
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

	private void setSignals() {
		Uri sound = Uri.parse(alarm.getRingtone());
		ringtone = RingtoneManager.getRingtone(getApplicationContext(), sound);
		ringtone.play();
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		int dot = 200; // Length of a Morse Code "dot" in milliseconds
		int dash = 500; // Length of a Morse Code "dash" in milliseconds
		int short_gap = 200; // Length of Gap Between dots/dashes
		int medium_gap = 500; // Length of Gap Between Letters
		int long_gap = 1000; // Length of Gap Between Words
		long[] pattern = { 0, // Start immediately
				dot, short_gap, dot, short_gap, dot, // s
				medium_gap, dash, short_gap, dash, short_gap, dash, // o
				medium_gap, dot, short_gap, dot, short_gap, dot, // s
				long_gap };
		vibrator.vibrate(pattern, 17);
	}

	protected abstract void solveCondition();

	public static Alarm getAlarm() {
		return alarm;
	}

	public static void setAlarm(Alarm newAlarm) {
		alarm = newAlarm;
	}
	
	
	
}
