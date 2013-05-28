package app.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OnBootReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context,"alarm boot got in receiver", Toast.LENGTH_SHORT).show();
		Log.d("DEBUG_TAG", "alarm boot got in receiver");
		
		WakeAlarmIntentService.acquireStaticLock(context);
		
		Intent i = new Intent(context, AlarmRefreshingService.class);
		context.startService(i);
	}

}
