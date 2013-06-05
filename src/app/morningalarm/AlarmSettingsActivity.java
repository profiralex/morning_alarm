package app.morningalarm;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class AlarmSettingsActivity extends PreferenceActivity {

  /**
   * clasa ce creaza ecranul cu preferinte pentru versiuni <=3
   */
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    String id = this.getIntent().getExtras().getString("id");
    if(id==null){
    	id="-1";
    }
    
    this.getPreferenceManager().setSharedPreferencesName(id);
    addPreferencesFromResource(R.xml.alarm_preferences_screen);
  }

}