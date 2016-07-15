package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "night", strict = false)
public class ForecastNight {
    @Element(name = "phenomenon")
    String phenomenon;
    @Element(name = "tempmin")
    String tempMin;
    @Element(name = "tempmax")
    String tempMax;
    @Element(name = "text")
    String description;
    @ElementList(entry = "place", inline = true, required = false)
    ArrayList<Place> arrayPlaces;
    @ElementList(entry = "wind", inline = true, required = false)
    ArrayList<Wind> arrayWind;
    String windMax, windMin;

    public ForecastNight(){}

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

    public ArrayList<Place> getArrayPlaces() {
        return arrayPlaces;
    }

    public void setArrayPlaces(ArrayList<Place> arrayPlaces) {
        this.arrayPlaces = arrayPlaces;
    }

    public ArrayList<Wind> getArrayWind() {
        return arrayWind;
    }

    public void setArrayWind(ArrayList<Wind> arrayWind) { this.arrayWind = arrayWind; }

    public String getWindMin() { return windMin; }

    public void setWindMin(String windMin) { this.windMin = windMin; }

    public String getWindMax() { return windMax; }

    public void setWindMax(String windMax) { this.windMax = windMax; }
}
