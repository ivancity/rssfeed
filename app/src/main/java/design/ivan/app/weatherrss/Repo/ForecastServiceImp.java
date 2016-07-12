package design.ivan.app.weatherrss.Repo;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Utility;

/**
 * Created by ivanm on 7/12/16.
 */
public class ForecastServiceImp implements IForecastService {

    //private static final ArrayMap<String, Forecast> PERSON_SERVICE_DATA;
    private static ArrayMap<String, Forecast> FORECAST_SERVICE_DATA = new ArrayMap<>();
    @Override
    public void getAllForecasts(ForecastServiceCallback<SparseArray<Forecast>> callback) {
        callback.onLoaded(Utility.arrayMapToSparseArray(FORECAST_SERVICE_DATA));
    }

    @Override
    public void getForecast(String forecastId, ForecastServiceCallback<Forecast> callback) {
        callback.onLoaded(FORECAST_SERVICE_DATA.get(forecastId));
    }

    @Override
    public void saveForecast(Forecast forecast) {
        FORECAST_SERVICE_DATA.put(forecast.getDate(), forecast);
    }

    @Override
    public void saveForecastArray(@NonNull SparseArray<Forecast> forecastSparseArray, @NonNull SaveSparseArrayCallback callback) {
        Forecast forecast;
        int counter = 0;
        for (int i = 0; i < forecastSparseArray.size(); i++) {
            forecast = forecastSparseArray.get(i);
            FORECAST_SERVICE_DATA.put(forecast.getDate(), forecast);
            counter++;
        }
        if(counter == forecastSparseArray.size()){
            callback.savedSparseArray(true);
        } else{
            callback.savedSparseArray(false);
        }
    }
}
