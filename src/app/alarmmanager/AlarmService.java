package app.alarmmanager;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import app.database.AlarmDbAdapter;

public class AlarmService extends WakeAlarmIntentService{
	public AlarmService(){
		super("AlarmService");
	}
	
	@Override
	void doAlarmWork(Intent intent){
		String alarmId = intent.getExtras().getString(AlarmDbAdapter.KEY_ID);
		Toast.makeText(this,"alarm got", Toast.LENGTH_SHORT).show();
		Log.d("DEBUG_TAG", "alarm got in service");
		Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarm);
		r.play();
	}

}
