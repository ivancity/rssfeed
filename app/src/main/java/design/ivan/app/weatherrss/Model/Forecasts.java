package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "forecasts")
public class Forecasts {
    @ElementList(inline = true, entry = "forecast")
    ArrayList<Forecast> forecasts;

    public Forecasts(){}

    public ArrayList<Forecast> getForecasts() { return forecasts; }

    public void setForecasts(ArrayList<Forecast> forecasts) { this.forecasts = forecasts; }
}
