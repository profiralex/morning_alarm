package app.morningalarm;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import app.alarmmanager.AlarmSetter;
import app.database.AlarmDbAdapter;
import app.database.AlarmDbUtilities;

public class AlarmListActivity extends Activity {
	
	private ArrayList<Alarm> alarmList;
	private String lastId;
	private int lastIndex;
	private AlarmListAdapter ad;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        alarmList = AlarmDbUtilities.fetchAllAlarms(this);
        Button b = (Button)this.findViewById(R.id.add_btn);
        b.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				Alarm newAlarm = AlarmDbUtilities.fetchNewAlarm(AlarmListActivity.this);
				lastIndex = alarmList.size();
				alarmList.add(newAlarm);
				Intent i;
				if (Build.VERSION.SDK_INT < 11) {
				    i = new Intent(AlarmListActivity.this, AlarmSettingsActivity.class);
				} else {
					i= new Intent(AlarmListActivity.this, AlarmFragmentsSettingsActivity.class);
				}
				i.putExtra(AlarmDbAdapter.KEY_ID, newAlarm.getId());
				lastId = newAlarm.getId();
				AlarmListActivity.this.startActivityForResult(i,0);
				
			}
        	
        });
        
        ad=new AlarmListAdapter(this,R.layout.list_item_main,alarmList);
        ListView lv=(ListView)this.findViewById(R.id.listView1);
        lv.setAdapter(ad);
        
        
        lv.setOnItemClickListener(new OnItemClickListener(){
        
			@SuppressLint("NewApi")
			public void onItemClick(AdapterView<?> adapter, View view, int arg,
					long position) {
				int pos = (int) position;
				Intent i;
				if (Build.VERSION.SDK_INT < 11) {
				    i = new Intent(AlarmListActivity.this, AlarmSettingsActivity.class);
				} else {
					i= new Intent(AlarmListActivity.this, AlarmFragmentsSettingsActivity.class);
				}
				i.putExtra("id", alarmList.get(pos).getId());
				lastId = alarmList.get(pos).getId();
				lastIndex = pos;
				AlarmListActivity.this.startActivityForResult(i,0);
			}
        });
        
        emptyTextViewVisibility();
        this.registerForContextMenu(lv);
    }
    
    private void emptyTextViewVisibility(){
    	if(alarmList.size()>0)
        	findViewById(R.id.id_empty_list_text_view).setVisibility(View.GONE);
    	else
    		findViewById(R.id.id_empty_list_text_view).setVisibility(View.VISIBLE);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	super.onContextItemSelected(item);
    	switch(item.getItemId()){
    		case R.id.delete_option:
    			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    			Alarm alarm = alarmList.get((int)info.id);
    			AlarmDbUtilities.deleteAlarm(this, alarm);
    			alarmList.remove(alarm);
    			emptyTextViewVisibility();
    			ad.notifyDataSetChanged();
    			break;
    	}
    	return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	SharedPreferences sp= this.getSharedPreferences(lastId, Context.MODE_PRIVATE);
    	
		String description = sp.getString("description", null);
		String time = sp.getString("time", null);
		String duration = sp.getString("duration", null);
		String daysOfWeek = sp.getString("days_of_week", null);
		String wakeUpMode = sp.getString("wake_up_mode", null);
		String ringtone = sp.getString("ringtone", null);
		Alarm alarm = alarmList.get(lastIndex);
		Calendar now = Calendar.getInstance();
		Calendar when = Calendar.getInstance();
		when.set(Calendar.SECOND,0);
		now.set(Calendar.SECOND,1);
		
		if(time != null){
			String timeArgs[] =time.split(":");
			when.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArgs[0]));
			when.set(Calendar.MINUTE, Integer.parseInt(timeArgs[1]));
		}
		
		Long setDayAfter = 0L;
		if(when.before(now)){
			setDayAfter = 86400000L;
		}
		
		alarm.setDescription(description);
		alarm.setTime((when.getTimeInMillis()+ setDayAfter) + "");
		alarm.setDuration(duration);
		alarm.setWakeUpMode(wakeUpMode);
		alarm.setDaysOfWeek(daysOfWeek);
		alarm.setRingtone(ringtone);
		AlarmDbUtilities.updateAlarm(this, alarm);
		emptyTextViewVisibility();
		ad.notifyDataSetChanged();
		AlarmSetter aSetter= new AlarmSetter(this);
		aSetter.setAlarm(alarm);
		
    }
}
