package design.ivan.app.weatherrss.Repo;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import design.ivan.app.weatherrss.Model.Forecast;

/**
 * Created by ivanm on 7/12/16.
 */
public class InMemoryForecastRepo implements IForecastRepository {

    private final IForecastService forecastService;
    SparseArray<Forecast> cachedForecast;

    public InMemoryForecastRepo(@NonNull  IForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @Override
    public void getForecastList(@NonNull final LoadForecastCallback callback) {
        if(cachedForecast == null){
            forecastService.getAllForecasts(new IForecastService.ForecastServiceCallback<SparseArray<Forecast>>() {
                @Override
                public void onLoaded(SparseArray<Forecast> forecastSparseArray) {
                    cachedForecast = forecastSparseArray;
                    callback.onForecastLoaded(cachedForecast);
                }
            });
        } else {
            callback.onForecastLoaded(cachedForecast);
        }
    }

    @Override
    public void getForecast(@NonNull String forecastId, @NonNull final GetForecastCallback callback) {
        forecastService.getForecast(forecastId, new IForecastService.ForecastServiceCallback<Forecast>() {
            @Override
            public void onLoaded(Forecast forecast) {
                callback.onForecastLoaded(forecast);
            }
        });
    }

    @Override
    public void saveForecast(@NonNull Forecast forecast) {
        forecastService.saveForecast(forecast);
        refreshData();
    }

    @Override
    public void saveArrayForecast(@NonNull SparseArray<Forecast> forecastSparseArray, @NonNull final SaveForecastArrayCallback callback) {
        forecastService.saveForecastArray(forecastSparseArray, new IForecastService.SaveSparseArrayCallback() {
            @Override
            public void savedSparseArray(boolean saved) {
                if(saved){
                    refreshData();
                    callback.onSavedArray(saved);
                }
            }
        });
    }

    @Override
    public void refreshData() {
        cachedForecast = null;
    }
}
