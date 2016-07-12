package design.ivan.app.weatherrss.Repo;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import design.ivan.app.weatherrss.Data.Forecast;

/**
 * Created by ivanm on 7/12/16.
 */
public interface IForecastRepository {
    interface LoadForecastCallback {
        void onForecastLoaded(SparseArray<Forecast> forecastSparseArray);
    }

    /**
     * Used to return back one Forecast from the ForecastService api object.
     */
    interface GetForecastCallback {
        void onForecastLoaded(Forecast forecast);
    }

    /**
     * Callback that returns a boolean that confirms if data was saved or not.
     */
    interface SaveForecastArrayCallback{
        void onSavedArray(boolean saved);
    }

    void getForecastList(@NonNull LoadForecastCallback callback);
    void getForecast(@NonNull String forecastId, @NonNull GetForecastCallback callback);
    void saveForecast(@NonNull Forecast forecast);
    void saveArrayForecast(@NonNull SparseArray<Forecast> forecastSparseArray, @NonNull SaveForecastArrayCallback callback);
    void refreshData();
}
