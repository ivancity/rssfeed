package design.ivan.app.weatherrss.Data;

/**
 * Created by ivanm on 7/12/16.
 */
public class Forecast {
    String date;
    ForecastInfo day;
    ForecastInfo night;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ForecastInfo getDay() {
        return day;
    }

    public void setDay(ForecastInfo day) {
        this.day = day;
    }

    public ForecastInfo getNight() {
        return night;
    }

    public void setNight(ForecastInfo night) {
        this.night = night;
    }
}
