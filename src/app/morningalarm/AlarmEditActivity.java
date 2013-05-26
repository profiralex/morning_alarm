package app.morningalarm;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;

public class AlarmEditActivity extends Activity{
	
	@Override
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		this.setContentView(R.layout.activity_edit);
		
		ArrayList<Alarm> list=new ArrayList<Alarm>();
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
        
        ListView lv=(ListView)this.findViewById(R.id.alarm_edit_list);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        lv.setAdapter(ad);
		
	}
}
