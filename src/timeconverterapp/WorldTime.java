package timeconverterapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class WorldTime {
    
    private Calendar currentDateObject;
    private int currentHour;
    private int currentMinute;
    
    private HashMap<String, Integer> cityMap;
    private ArrayList<String> cityList;
    
    public WorldTime() {
        fillCityMap();
        this.currentDateObject = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        updateCurrentTime();
    }
    
    private void updateCurrentTime() {
        this.currentDateObject = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        this.currentHour = currentDateObject.get(Calendar.HOUR_OF_DAY);
        this.currentMinute = currentDateObject.get(Calendar.MINUTE);
    }
    
    private void fillCityMap() {
        cityMap = new HashMap<>();
        cityMap.put("State College", -5);
        cityMap.put("London", 0);
        cityMap.put("Paris", 1);
        cityMap.put("Berlin", 1);
        cityMap.put("Cairo", 2);
        cityMap.put("Helsinki", 2);
        cityMap.put("Baghdad", 3);
        cityMap.put("Moscow", 3);
        cityMap.put("Dubai", 4);
        cityMap.put("Bangkok", 7);
        cityMap.put("Beijing", 8);
        cityMap.put("Singapore", 8);
        cityMap.put("Tokyo", 9);
        cityMap.put("Seoul", 9);
        cityMap.put("Brisbane", 10);
        cityMap.put("Sydney", 11);
        cityMap.put("Honolulu", -10);
        cityMap.put("Anchorage", -9);
        cityMap.put("Vancouver", -8);
        cityMap.put("Los Angeles", -8);
        cityMap.put("Seattle", -8);
        cityMap.put("Edmonton", -7);
        cityMap.put("Denver", -7);
        cityMap.put("Winnipeg", -6);
        cityMap.put("Mexico City", -6);
        cityMap.put("Chicago", -6);
        cityMap.put("Houston", -6);
        cityMap.put("Toronto", -5);
        cityMap.put("Montreal", -5);
        cityMap.put("New York", -5);
        cityMap.put("Washington DC", -5);
        cityMap.put("Miami", -5);
        cityMap.put("Halifax", -4);
        cityMap.put("San Juan", -4);
        cityMap.put("Buenos Aires", -3);
        cityMap.put("Rio de Janeiro", -3);
        cityList = new ArrayList<>();
        cityMap.entrySet().forEach(entry -> {
            cityList.add(entry.getKey());
        });
    }
    
    public int[] getCurrentTime() {
        updateCurrentTime();
        int[] tempArray = new int[]{currentHour, currentMinute};
        return tempArray;
    }
    
    public HashMap<String, Integer> getCityMap() {
        return this.cityMap;
    }
    
    public ArrayList<String> getCityList() {
        return this.cityList;
    }
    
}
