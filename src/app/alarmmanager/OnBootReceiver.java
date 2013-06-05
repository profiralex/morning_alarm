package app.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OnBootReceiver extends BroadcastReceiver{

	
	/**
	 * metoda este apelata atunc cind telefonul a fost resetat
	 * la primirea semnalului apeleaza service-ul alarmRefreshingService 
	 * pentru a seta si actualiza toate alarmele
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("DEBUG_TAG", "alarm boot got in receiver");
		
		WakeAlarmIntentService.acquireStaticLock(context);
		
		Intent i = new Intent(context, AlarmRefreshingService.class);
		context.startService(i);
	}

}
