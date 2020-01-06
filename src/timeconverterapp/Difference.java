package timeconverterapp;

public class Difference {
    
    private String timeOneHour;
    private String timeOneMinute;
    private String timeTwoHour;
    private String timeTwoMinute;
    private int timeOneZone;
    private int timeTwoZone;
    private int timeDiffZone;
    private int timeDiffHour;
    private int timeDiffMinute;
    private int statusDayJump;
    private String[] timeZoneList;
    
    public Difference() {
        
        initItems();
        populateZoneList();
    }
    
    private void initItems() {
        this.timeOneHour = "0";
        this.timeOneMinute = "0";
        this.timeTwoHour = "0";
        this.timeTwoMinute = "0";
        this.timeOneZone = 0;
        this.timeTwoZone = 0;
        this.timeDiffZone = 0;
        this.timeDiffHour = 0;
        this.timeDiffMinute = 0;
        this.statusDayJump = 0;
    }
    
    public void setTimeValues(int[] array) {
        this.timeOneHour = Integer.toString(array[0]);
        this.timeOneMinute = Integer.toString(array[1]);
        this.timeTwoHour = Integer.toString(array[2]);
        this.timeTwoMinute = Integer.toString(array[3]);
    }
    
    public void setTimeZones(int zone1, int zone2) {
        this.timeOneZone = zone1;
        this.timeTwoZone = zone2;
    }
    
    public void calcZoneDiff() {
        int tempOne;
        int tempTwo;
        if (timeOneZone < 15) {
            tempOne = timeOneZone;
        } else {
            tempOne = -(timeOneZone - 14);
        }
        if (timeTwoZone < 15) {
            tempTwo = timeTwoZone;
        } else {
            tempTwo = -(timeTwoZone - 14);
        }
        timeDiffZone = tempTwo - tempOne;
    }
    
    public void calcTimeDiff() {
        int tempOneHour = Integer.valueOf(timeOneHour);
        int tempOneMinute = Integer.valueOf(timeOneMinute);
        int tempTwoHour = Integer.valueOf(timeTwoHour);
        int tempTwoMinute = Integer.valueOf(timeTwoMinute);
        statusDayJump = 0;
        if (tempOneHour < tempTwoHour) {
            timeDiffHour = tempTwoHour - tempOneHour;
            timeDiffHour += timeDiffZone;
        } else {
            timeDiffHour = tempOneHour - tempTwoHour;
            timeDiffHour += timeDiffZone;
        }
        if (timeDiffHour + tempOneHour < 0) {
            statusDayJump = 1;
        }
        if (timeDiffHour >= 0) {
            if (tempOneMinute <= tempTwoMinute) {
                timeDiffMinute = tempTwoMinute - tempOneMinute;
            } else {
                timeDiffMinute = (60 - tempOneMinute) + tempTwoMinute;
                if (timeDiffHour == 0) {
                    statusDayJump = 1;
                } else if (timeDiffHour + 1 > 23) {
                    timeDiffHour = 0;
                    statusDayJump = 1;
                } else {
                    timeDiffHour += 1;
                }
            }
        } else {
            if (tempOneMinute == tempTwoMinute) {
                timeDiffMinute = 0;
            } else if (tempOneMinute < tempTwoMinute) {
                timeDiffMinute = (60 - tempTwoMinute) + tempOneMinute;
                timeDiffHour += 1;
            } else {
                timeDiffMinute = tempOneMinute - tempTwoMinute;
            }
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
    
    public String[] getTimeZoneList() {
        return this.timeZoneList;
    }
    
    public int[] getTimeDiff() {
        return new int[] {timeDiffHour, timeDiffMinute};
    }
    
    public int getStatusDayJump() {
        return this.statusDayJump;
    }
    
}
