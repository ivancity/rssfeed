package design.ivan.app.weatherrss.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RssForecast {
    @GET("/ilma_andmed/xml/forecast.php")
    Call<Forecasts> getForecasts();
}
