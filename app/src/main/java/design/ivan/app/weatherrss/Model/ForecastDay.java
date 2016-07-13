package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "day", strict = false)
public class ForecastDay {
    @Element(name = "phenomenon")
    String phenomenon;
    @Element(name = "tempmin")
    String tempMin;
    @Element(name = "tempmax")
    String tempMax;
    @Element(name = "text")
    String description;
    @ElementList(entry = "place", inline = true, required = false)
    List<Place> arrayPlaces;
    @ElementList(entry = "wind", inline = true, required = false)
    List<Wind> arrayWind;

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

    public List<Place> getArrayPlaces() {
        return arrayPlaces;
    }

    public void setArrayPlaces(List<Place> arrayPlaces) {
        this.arrayPlaces = arrayPlaces;
    }

    public List<Wind> getArrayWind() {
        return arrayWind;
    }

    public void setArrayWind(List<Wind> arrayWind) { this.arrayWind = arrayWind; }
}
