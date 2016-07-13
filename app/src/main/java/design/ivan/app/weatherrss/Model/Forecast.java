package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "forecast")
public class Forecast {
    @Attribute(name = "date", required = false)
    String date;
    @Element(name = "day")
    ForecastDay day;
    @Element(name = "night")
    ForecastNight night;

    public Forecast(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ForecastDay getDay() {
        return day;
    }

    public void setDay(ForecastDay day) {
        this.day = day;
    }

    public ForecastNight getNight() {
        return night;
    }

    public void setNight(ForecastNight night) {
        this.night = night;
    }
}
