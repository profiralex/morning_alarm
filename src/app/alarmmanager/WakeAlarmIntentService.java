package app.alarmmanager;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public abstract class WakeAlarmIntentService extends IntentService{
	abstract void doAlarmWork(Intent i);
	
	public static final String LOCK_NAME_STATIC = "app.android.morningalarm.Static";
	public static PowerManager.WakeLock lockStatic = null;
	
	public static void acquireStaticLock(Context context){
		getLock(context).acquire();
	}
	
	synchronized private static PowerManager.WakeLock getLock(Context context){
		if(lockStatic == null){
			PowerManager mgr = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
			lockStatic = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, LOCK_NAME_STATIC);
			lockStatic.setReferenceCounted(true);
		}
		return lockStatic;
	}
	
	public WakeAlarmIntentService(String name){
		super(name);
	}
	
	@Override
	final protected void onHandleIntent(Intent intent){
		try{
			doAlarmWork(intent);
		}finally{
			getLock(this).release();
		}
	}
}
