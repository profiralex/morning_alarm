package app.morningalarm;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;

public class TimePreference extends DialogPreference {

	TimePicker tp;
	int xxx;
	public void initialize(){
		this.setPersistent(true);
	}
	
	public TimePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}
	
	public TimePreference(Context context, AttributeSet attrs, int def) {
		super(context, attrs, def);
		initialize();
	}
	
	@Override
	protected View onCreateDialogView() {
	    this.tp = new TimePicker(getContext());
	    this.tp.setIs24HourView(true);
	    final String storedValue = getPersistedString("07:00");
	    final String[] split = storedValue.split(":");
	    this.tp.setCurrentHour(Integer.parseInt(split[0]));
	    this.tp.setCurrentMinute(Integer.parseInt(split[1]));
	    return this.tp;
	}

	@Override
	public void onDialogClosed(boolean positiveResult) {
	    super.onDialogClosed(positiveResult);
	    if (positiveResult) {
	        final String result = this.tp.getCurrentHour() + ":" + this.tp.getCurrentMinute();
	        persistString(result);
	    }
	}
	
}