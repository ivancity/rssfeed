package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "wind")
public class Wind {
    @Element(name = "name")
    String name;
    @Element(name = "speedmin")
    String speedMin;
    @Element(name = "speedmax")
    String speedMax;

    public Wind(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeedMin() {
        return speedMin;
    }

    public void setSpeedMin(String speedMin) {
        this.speedMin = speedMin;
    }

    public String getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(String speedMax) {
        this.speedMax = speedMax;
    }
}
