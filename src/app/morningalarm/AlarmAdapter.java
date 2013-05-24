package app.morningalarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AlarmAdapter extends ArrayAdapter<Alarm> {

	private ArrayList<Alarm> alarms;

	public AlarmAdapter(Context context, int textViewResourceId,
			ArrayList<Alarm> objects) {
		super(context, textViewResourceId, objects);
		this.alarms = objects;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item_main, null);
            }
            
            Alarm li = alarms.get(position);
            if (li != null) {
                    ImageView iv= (ImageView) v.findViewById(R.id.alarm_iv);
                    TextView tv = (TextView) v.findViewById(R.id.alarm_tv);
                    ToggleButton tb=(ToggleButton) v.findViewById(R.id.alarm_tb);
                    if (iv != null) {
                    	if(li.getMethod().equals("simple"))
                    		iv.setImageResource(R.drawable.ic_launcher);
                    	if(li.getMethod().equals("logic"))
                    		iv.setImageResource(R.drawable.ic_launcher);
                    	if(li.getMethod().equals("scanner"))
                    		iv.setImageResource(R.drawable.ic_launcher);
                    }
                    if(tv != null){
                    	  SimpleDateFormat df=new SimpleDateFormat("kk:mm");
                          tv.setText(df.format(li.getC().getTime()));
                    }
                    if(tb!=null){
                    	tb.setChecked(li.isEnabled());
                    }
            }
            
            return v;
    }

}
