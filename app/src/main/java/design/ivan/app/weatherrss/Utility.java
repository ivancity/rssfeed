package design.ivan.app.weatherrss;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import java.util.ArrayList;

import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Model.Wind;

/**
 * Created by ivanm on 7/12/16.
 */
public class Utility {
    private static final String TAG = "Utility";

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

    public static SparseArray<Forecast> prepareSparseArray(ArrayList<Forecast> forecastList){
        Forecast selectedForecast;
        SparseArray<Forecast> sparseArray = new SparseArray<>();
        for (int i = 0; i < forecastList.size(); i++) {
            selectedForecast = forecastList.get(i);
            initWindReadings(selectedForecast);
            sparseArray.put(i, selectedForecast);
        }
        return sparseArray;
    }

    public static void initWindReadings(Forecast forecast){
        //TODO iterate in wind list of night and day and get min and max winds
        Wind wind;
        int speedMax, speedMin;
        ArrayList<Wind>windList = forecast.getDay().getArrayWind();
        if(windList == null)
            return;

        for (int i = 0; i < windList.size(); i++) {
            wind = windList.get(i);
            speedMax = wind.getSpeedMax();
        }

    }

}
