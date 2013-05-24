package app.morningalarm;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AlarmListActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        ListView lv=(ListView)this.findViewById(R.id.listView1);
        lv.setAdapter(ad);
        
        lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
        	
        });
        
        Intent i=new Intent(this, AlarmEditActivity.class);
		startActivity(i);
    }
}
