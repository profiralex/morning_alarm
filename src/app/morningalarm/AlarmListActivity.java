package app.morningalarm;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AlarmListActivity extends Activity {
	ArrayList<Alarm> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list =new ArrayList<Alarm>();
        list.add(new Alarm(0,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(1,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(2,Calendar.getInstance(),"calm","simple",null,false));
        list.add(new Alarm(3,Calendar.getInstance(),"calm","simple",null,false));
        list.add(new Alarm(4,Calendar.getInstance(),"calm","simple",null,false));
        list.add(new Alarm(5,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(6,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(7,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(8,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(9,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(10,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(11,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(12,Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(13,Calendar.getInstance(),"calm","simple",null,true));
        AlarmAdapter ad=new AlarmAdapter(this,R.layout.list_item_main,list);
        ListView lv=(ListView)this.findViewById(R.id.listView1);
        lv.setAdapter(ad);
        
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this); 
        
        lv.setOnItemClickListener(new OnItemClickListener(){
        
			@SuppressLint("NewApi")
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i;
				if (Build.VERSION.SDK_INT < 11) {
				    i = new Intent(AlarmListActivity.this, AlarmSettingsActivity.class);
				} else {
					i= new Intent(AlarmListActivity.this, AlarmFragmentsSettingsActivity.class);
				}
				i.putExtra("id", list.get((int)arg3).getId());
				AlarmListActivity.this.startActivity(i);
			}
        });
        
        
    }
}
