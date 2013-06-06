package app.morningalarm;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import app.database.AlarmDbUtilities;

public class MorningAlarmWidgetProvider extends AppWidgetProvider {

  @Override
  public void onReceive(Context context, Intent intent){
	  if(intent.getAction()==null){
		  context.startService(new Intent(context, ToggleService.class));
	  }else{
		  super.onReceive(context, intent);
	  }
  }
  
  @Override 
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
	  context.startService(new Intent(context, ToggleService.class));
  }
  
  public static class ToggleService extends IntentService{

	public ToggleService() {
		super("MorningAlarmWidgetProvider$ToggleService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		ComponentName me = new ComponentName(this,MorningAlarmWidgetProvider.class);
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
		appWidgetManager.updateAppWidget(me, buildUpdate(this));
	}
	
	private RemoteViews buildUpdate(Context context){
		RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.home_screen_widget);;
		if(AlarmDbUtilities.fetchEnabledAlarms(this).size() == 0){
			updateViews.setImageViewResource(R.id.home_screen_iv, R.drawable.clock2);
		}else{
			updateViews.setImageViewResource(R.id.home_screen_iv, R.drawable.clock1);
		}
		Intent i = new Intent(this, MorningAlarmWidgetProvider.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
		updateViews.setOnClickPendingIntent(R.id.home_screen_iv, pi);
		
		return updateViews;
	}
	  
  }
}
