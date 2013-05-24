package app.morningalarm;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class AlarmEditActivity extends Activity{
	
	@Override
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		this.setContentView(R.layout.activity_edit);
		
		ArrayList<Alarm> list=new ArrayList<Alarm>();
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,false));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,false));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,false));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        list.add(new Alarm(Calendar.getInstance(),"calm","simple",null,true));
        
        AlarmAdapter ad=new AlarmAdapter(this,R.layout.list_item_main,list);
        
        ListView lv=(ListView)this.findViewById(R.id.alarm_edit_list);
        
        lv.setAdapter(ad);
		
	}
}
