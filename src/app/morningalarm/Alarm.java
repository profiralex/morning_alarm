package app.morningalarm;

public class Alarm {
	
	public static final int 	ALARM_ENABLED = 1;
	public static final int 	ALARM_DISABLED = 0;
	
	private String id;
	private int    enabled;
	private String description;
	private String time;
	private String daysOfWeek;
	private String duration;
	private String wakeUpMode;
	private String ringtone;

	public Alarm(String id, int enabled, String description, String time,String daysOfWeek, String duration, String wakeUpMode, String ringtone) {
		this.id = id;
		this.enabled=enabled;
		this.description = description;
		this.time = time;
		this.daysOfWeek=daysOfWeek;
		this.duration = duration;
		this.wakeUpMode = wakeUpMode;
		this.ringtone = ringtone;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWakeUpMode() {
		return wakeUpMode;
	}

	public void setWakeUpMode(String wakeUpMode) {
		this.wakeUpMode = wakeUpMode;
	}

	public String getRingtone() {
		return ringtone;
	}

	public void setRingtone(String ringtone) {
		this.ringtone = ringtone;
	}

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public int isEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public static String convertArrayToString(String[] array){
	    String str = "";
	    for (int i = 0;i<array.length; i++) {
	        str = str+array[i];
	        // Do not append comma at the end of last element
	        if(i<array.length-1){
	            str = str+",";
	        }
	    }
	    return str;
	}
	public static String[] convertStringToArray(String str){
	    String[] arr = str.split(",");
	    return arr;
	}

	
}
