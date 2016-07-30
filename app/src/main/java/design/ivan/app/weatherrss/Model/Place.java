package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "place")
public class Place {
    @Element(name = "name", required = false)
    String name;
    @Element(name = "phenomenon", required = false)
    String phenomenon;
    @Element(name = "tempmin", required = false)
    String tempMin;
    @Element(name = "tempmax", required = false)
    String tempMax;
    String phenomenonExtra;

    public Place(){}

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

    public String getTempMax() { return tempMax; }

    public void setTempMax(String tempMax) { this.tempMax = tempMax; }

    public String getPhenomenonExtra() {
        return phenomenonExtra;
    }

    public void setPhenomenonExtra(String phenomenonExtra) {
        this.phenomenonExtra = phenomenonExtra;
    }
}
