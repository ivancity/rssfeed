package design.ivan.app.weatherrss.Model;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface RssForecast {
    @GET("/ilma_andmed/xml/forecast.php")
    void getForecast(Callback<Forecasts> callback);
}
