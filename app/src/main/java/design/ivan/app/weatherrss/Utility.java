package design.ivan.app.weatherrss;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import java.util.ArrayList;

import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Model.ForecastDate;
import design.ivan.app.weatherrss.Model.Wind;


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
            initWindReadings(selectedForecast.getDay());
            initWindReadings(selectedForecast.getNight());
            sparseArray.put(i, selectedForecast);
        }
        return sparseArray;
    }

    public static void initWindReadings(ForecastDate forecastDate){
        int speedMax, speedMin, temp;
        ArrayList<Wind>windList = forecastDate.getArrayWind();
        if(windList == null)
            return;

        speedMax = windList.get(0).getSpeedMax();
        speedMin = windList.get(0).getSpeedMin();
        for (int i = 1; i < windList.size(); i++) {
            temp = windList.get(i).getSpeedMax();
            if(temp > speedMax)
                speedMax = temp;

            temp = windList.get(i).getSpeedMin();
            if(temp < speedMin)
                speedMin = temp;
        }
        forecastDate.setWindMax(String.valueOf(speedMax));
        forecastDate.setWindMin(String.valueOf(speedMin));
    }


}
