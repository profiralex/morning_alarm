package app.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import app.database.AlarmDbAdapter;

public class OnAlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String alarmId = intent.getExtras().getString(AlarmDbAdapter.KEY_ID);
		Log.d("DEBUG_TAG", "alarm got in receiver "+ alarmId);
		WakeAlarmIntentService.acquireStaticLock(context);
		
		Intent i = new Intent(context, AlarmService.class);
		i.putExtra(AlarmDbAdapter.KEY_ID, alarmId);
		context.startService(i);
		
	}
	

}
