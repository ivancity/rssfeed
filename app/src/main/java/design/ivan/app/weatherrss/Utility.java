package design.ivan.app.weatherrss;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import design.ivan.app.weatherrss.Data.Forecast;

/**
 * Created by ivanm on 7/12/16.
 */
public class Utility {
    public static SparseArray<Forecast> arrayMapToSparseArray(ArrayMap<String, Forecast> forecastData){
        SparseArray<Forecast> forecastSparseArray = new SparseArray<>();
        Forecast forecast;
        //faster for loop iteration due to use of ArrayMap
        for (int i = 0; i < forecastData.size(); i++) {
            forecast = forecastData.valueAt(i);
            forecastSparseArray.append(i, forecast);
        }
        return forecastSparseArray;
    }

}
