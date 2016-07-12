package design.ivan.app.weatherrss.Data;

/**
 * Created by ivanm on 7/12/16.
 */
public class Place {
    String name;
    String phenomenon;
    String tempMin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
