package app.morningalarm;

import java.util.Calendar;

public class Alarm {
	private Calendar c;
	private String method;
	private String sound;
	private boolean dow[];
	private boolean enabled;

	public Alarm(Calendar c, String sound, String method, boolean dow[],boolean enabled) {
		this.c = c;
		this.sound = sound;
		this.method = method;
		this.dow=dow;
		this.enabled=enabled;
	}
	
	public Alarm(){
		this(Calendar.getInstance(),"calm","simple",null,true);
	}

	public Calendar getC() {
		return c;
	}

	public void setC(Calendar c) {
		this.c = c;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public boolean[] getDow() {
		return dow;
	}

	public void setDow(boolean[] dow) {
		this.dow = dow;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
}
