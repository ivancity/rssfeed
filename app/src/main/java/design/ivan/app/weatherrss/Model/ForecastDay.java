package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.Root;

@Root(name = "day")
public class ForecastDay {
    @Element(name = "phenomenon")
    String phenomenon;
    @Element(name = "tempmin")
    String tempMin;
    @Element(name = "tempmax")
    String tempMax;
    @Element(name = "text")
    String description;
    @ElementArray(name = "place")
    Place[] arrayPlaces;
    @ElementArray(name = "wind")
    Wind[] arrayWind;

    public ForecastDay(){}

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Place[] getArrayPlaces() {
        return arrayPlaces;
    }

    public void setArrayPlaces(Place[] arrayPlaces) {
        this.arrayPlaces = arrayPlaces;
    }

    public Wind[] getArrayWind() {
        return arrayWind;
    }

    public void setArrayWind(Wind[] arrayWind) {
        this.arrayWind = arrayWind;
    }
}
