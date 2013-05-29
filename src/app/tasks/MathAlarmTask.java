package app.tasks;

import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import app.morningalarm.Alarm;
import app.morningalarm.R;

public class MathAlarmTask extends AlarmTask {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	protected void solveCondition() {
		finishAlarm = false;
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.math_test_layout);
		dialog.setTitle("Math Test");
		dialog.setCancelable(false);
		dialog.show();
		dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		
		TextView textView = (TextView) dialog.findViewById(R.id.math_test_task);
		final EditText editText = (EditText) dialog.findViewById(R.id.math_test_answer);
		Button solveButton = (Button) dialog.findViewById(R.id.math_test_button);
		Random rand = new Random();
		int	nums[] = new int[5];
		for (int i = 0; i < 5; i++) {
			nums[i] = rand.nextInt(5) + 1;
		}
		textView.setText(nums[0] + " + " + nums[1] + " * " + nums[2] + " + "
				+ nums[3] + " * " + nums[4]);
		final int result = nums[0] + nums[1] * nums[2] + nums[3] * nums[4];
		solveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (editText.getText().toString().equals(result + "")) {
					finishAlarm = true;
					Log.d("DEBUG_TAG", "condition solved");
				} else
					Toast.makeText(dialog.getContext(), "Not Corect!",
							Toast.LENGTH_SHORT).show();

			}

		});
		Log.d("DEBUG_TAG", "exit solvecondition method");
	}

	public static Alarm getAlarm() {
		return alarm;
	}

	public static void setAlarm(Alarm newAlarm) {
		alarm = newAlarm;
	}
}
