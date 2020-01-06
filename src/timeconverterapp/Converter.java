package timeconverterapp;

import java.util.Calendar;
import java.util.Date;

public class Converter {
    
    private Date currentDate;
    private Calendar currentDateObject;
    private String currentHour;
    private String currentMinute;
    private String currentSecond;
    private String currentTime;
    private String currentZone;
    private int currentZoneOffset;
    private int targetHour;
    private String targetTime;
    private int targetZoneOffset;
    private String[] timeZoneList;
    
    
    public Converter() {
        this.currentDateObject = Calendar.getInstance();
        calcLocalZoneOffset();
        updateCurrentTime();
        populateZoneList();
        this.targetZoneOffset = this.currentZoneOffset;
    }
    
    private void updateCurrentTime() {
        this.currentDate = new Date();
        this.currentDateObject = Calendar.getInstance();
        this.currentHour = String.format("%02d", currentDateObject.get(Calendar.HOUR_OF_DAY));
        this.currentMinute = String.format("%02d", currentDateObject.get(Calendar.MINUTE));
        this.currentSecond = String.format("%02d", currentDateObject.get(Calendar.SECOND));
        this.currentTime = currentHour + ":" + currentMinute + ":" + currentSecond;
        if (currentDateObject.getTimeZone().inDaylightTime(currentDate)) {
            this.currentZone = "GMT " + currentZoneOffset + " (Currently DST for GMT " + (currentZoneOffset-1) + ")";
        } else {
            this.currentZone = "GMT " + currentZoneOffset;
        }
    }
    
    private String[] populateZoneList() {
        this.timeZoneList= new String[27];
        for (int i = 0; i < 27; i++) {
            if (i < 15) {
                timeZoneList[i] = "GMT+" + (i);
            } else {
                timeZoneList[i] = "GMT-" + (i-14);
            }
        }
        return this.timeZoneList;
    }
    
    private int calcLocalZoneOffset() {
        this.currentZoneOffset = currentDateObject.getTimeZone().getOffset(
        currentDateObject.get(Calendar.ERA),
        currentDateObject.get(Calendar.YEAR),
        currentDateObject.get(Calendar.MONTH),
        currentDateObject.get(Calendar.DAY_OF_MONTH),
        currentDateObject.get(Calendar.DAY_OF_WEEK),
        currentDateObject.get(Calendar.MILLISECOND)
        );
        this.currentZoneOffset = currentZoneOffset / (60*60*1000);
        return this.currentZoneOffset;
    }
    
    public void calcTargetOffset(int index) {
        if (index < 15) {
            this.targetZoneOffset = index;
        } else {
            this.targetZoneOffset = -(index - 14);
        }
        updateTargetTime();
    }
    
    private void updateTargetTime(){
        this.targetHour = Integer.valueOf(this.currentHour) - currentZoneOffset + targetZoneOffset;
        if (targetHour > 23) {
            this.targetHour -= 24;
        } else if (targetHour < 0) {
            this.targetHour += 24;
        }
        this.targetTime = String.format("%02d", targetHour) + ":" + currentMinute + ":" + currentSecond;
    }
    
    public String getCurrentTime() {
        updateCurrentTime();
        return this.currentTime;
    }
    public String getCurrentZone() {
        updateCurrentTime();
        return this.currentZone;
    }
    public String getTargetTime() {
        updateTargetTime();
        return this.targetTime;
    }
    public String[] getTimeZoneList() {
        return this.timeZoneList;
    }
    
}
