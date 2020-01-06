package timeconverterapp;

public class PlannerEvent {
    
    private String title;
    private int month;
    private int day;
    private int year;
    private String desc;
    
    public PlannerEvent(String title, int month, int day, int year, String desc) {
        this.title = title;
        this.month = month;
        this.day = day;
        this.year = year;
        this.desc = desc;
    }
    
    public PlannerEvent() {
        
    }
    
    public String getTitle() {
        return this.title;
    }
    public int[] getDate() {
        int[] tempArray = {month, day, year};
        return tempArray;
    }
    public String getDesc() {
        return this.desc;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(int[] date) {
        this.month = date[0];
        this.day = date[1];
        this.year = date[2];
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
