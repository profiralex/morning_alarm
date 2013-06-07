package app.tasks;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import app.morningalarm.Alarm;
import app.morningalarm.R;

public class SimpleAlarmTask extends AlarmTask {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
	}

	protected void solveCondition() {
		dialog.setContentView(R.layout.simple_test_layout);
		dialog.setTitle("Cancel Alarm or Snooze for 5 minutes");
		dialog.setCancelable(false);
		dialog.show();
		
		
		Button snoozeButton = (Button) dialog.findViewById(R.id.simple_test_snooze_button);
		Button cancelButton = (Button) dialog.findViewById(R.id.simple_test_cancel_button);
		snoozeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				snooze = true;
			}

		});
		cancelButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finishAlarm = true;
			}

		});
		Log.d("DEBUG_TAG", "exit solvecondition method");
	}

}
