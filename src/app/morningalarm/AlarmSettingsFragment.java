package app.morningalarm;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;

@SuppressLint("NewApi")
public class AlarmSettingsFragment extends PreferenceFragment {

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String id = (String) this.getArguments().get("id");
		if (id==null){
			id="-1";
		}
		this.getPreferenceManager().setSharedPreferencesName(id);
		addPreferencesFromResource(R.xml.alarm_preferences_screen);
	}

}