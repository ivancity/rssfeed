package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(strict = false)
public class ForecastDate {
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
    String windMin, windMax;
    String tempMinWord, tempMaxWord;
    String tempMinFormatted, tempMaxFormatted;
    String tempPhrase;

    public ForecastDate(){}

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

    public String getTempMinWord() {
        return tempMinWord;
    }

    public void setTempMinWord(String tempMinWord) {
        this.tempMinWord = tempMinWord;
    }

    public String getTempMaxWord() {
        return tempMaxWord;
    }

    public void setTempMaxWord(String tempMaxWord) {
        this.tempMaxWord = tempMaxWord;
    }

    public String getTempMinFormatted() {
        return tempMinFormatted;
    }

    public void setTempMinFormatted(String tempMinFormatted) {
        this.tempMinFormatted = tempMinFormatted;
    }

    public String getTempMaxFormatted() {
        return tempMaxFormatted;
    }

    public void setTempMaxFormatted(String tempMaxFormatted) {
        this.tempMaxFormatted = tempMaxFormatted;
    }

    public String getTempPhrase() {
        return tempPhrase;
    }

    public void setTempPhrase(String tempPhrase) {
        this.tempPhrase = tempPhrase;
    }
}
