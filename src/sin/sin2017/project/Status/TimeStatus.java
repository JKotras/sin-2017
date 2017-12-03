package sin.sin2017.project.Status;

import java.io.IOException;
import java.io.Serializable;

public class TimeStatus implements Serializable{
    public static String MORNING = "morning";
    public static String AFTERNOON = "afternoon";
    public static String EVENING = "evening";
    public static String NIGHT = "night";

    protected int numOfSeconds = 70000;
    public static int numbOfSecondsPerDay = 86400;

    public int getNumOfSeconds() {
        return numOfSeconds;
    }

    public void addSecond(){
        this.numOfSeconds++;
        if(numOfSeconds >= TimeStatus.numbOfSecondsPerDay){
            numOfSeconds = 0;
        }
    }

    public void setNumOfSeconds(int numOfSeconds) {
        this.numOfSeconds = numOfSeconds;
    }

    public int getActualTimeHours(){
        return numOfSeconds/3600;
    }

    public int getActualTimeMinutes(){
        int seconds = numOfSeconds%3600;
        return seconds/60;
    }

    public String getTimeLikeString(){
        int minutes = this.getActualTimeMinutes();
        String min;
        if(minutes < 10){
            min = "0" + Integer.toString(minutes);
        }else{
            min = Integer.toString(minutes);
        }

        int hours = this.getActualTimeHours();
        String h;
        if(hours < 10) {
            h = "0" + Integer.toString(hours);
        }else{
            h = Integer.toString(hours);
        }

        String time = h +":"+min;
        return time;
    }

    public String getPartOfDay() {
        if(this.numOfSeconds < 21600){
            return TimeStatus.NIGHT;
        }else if(this.numOfSeconds >= 21600 && this.numOfSeconds < 43200){
            return TimeStatus.MORNING;
        }else if(this.numOfSeconds >= 43200 && this.numOfSeconds < 64800){
            return TimeStatus.AFTERNOON;
        }else if(this.numOfSeconds >= 64800 && this.numOfSeconds < 75600) {
            return TimeStatus.EVENING;
        }else{
            return TimeStatus.NIGHT;
        }
    }

    public static TimeStatus deserialize(String s) throws IOException, ClassNotFoundException {
        int seconds = Integer.parseInt(s);
        TimeStatus timeStatus = new TimeStatus();
        timeStatus.numOfSeconds = seconds;
        return timeStatus;
    }

    public String serialize() throws IOException {
        return Integer.toString(this.numOfSeconds);
    }
}
