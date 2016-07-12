package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.Root;

@Root(name = "forecasts")
public class Forecasts {
    @ElementArray(name = "forecast")
    Forecast[] forecasts;

    public Forecasts(){}
}
