package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "forecast")
public class Forecast {
    @Attribute(name = "date", required = false)
    String date;
    @Element(name = "day")
    ForecastDate day;
    @Element(name = "night")
    ForecastDate night;
    String formattedDate;

    public Forecast(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ForecastDate getDay() {
        return day;
    }

    public void setDay(ForecastDate day) {
        this.day = day;
    }

    public ForecastDate getNight() {
        return night;
    }

    public void setNight(ForecastDate night) {
        this.night = night;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
