package design.ivan.app.weatherrss.Model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "forecasts")
public class Forecasts {
    @ElementList(inline = true, entry = "forecast")
    List<Forecast> forecasts;

    public Forecasts(){}

    public List<Forecast> getForecasts() { return forecasts; }

    public void setForecasts(List<Forecast> forecasts) { this.forecasts = forecasts; }
}
