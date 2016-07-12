package design.ivan.app.weatherrss.Repo;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import design.ivan.app.weatherrss.Data.Forecast;

/**
 * Created by ivanm on 7/12/16.
 */
public interface IForecastService {
    interface ForecastServiceCallback<T>{
        void onLoaded(T forecast);
    }

    interface SaveSparseArrayCallback{
        void savedSparseArray(boolean saved);
    }

    void getAllForecasts(ForecastServiceCallback<SparseArray<Forecast>> callback);

    void getForecast(String forecastId, ForecastServiceCallback<Forecast> callback);

    void saveForecast(Forecast forecast);

    void saveForecastArray(@NonNull SparseArray<Forecast> forecastSparseArray,
                           @NonNull SaveSparseArrayCallback callback);

}
