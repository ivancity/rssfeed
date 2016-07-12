package design.ivan.app.weatherrss.Data;

/**
 * Created by ivanm on 7/12/16.
 */
public class ForecastInfo {
    String phenomenon;
    String tempMin;
    String tempMax;
    String description;
    Place[] arrayPlaces;
    Wind[] arrayWind;

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
