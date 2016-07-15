package design.ivan.app.weatherrss;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import java.io.UnsupportedEncodingException;
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
            checkTextEncoding(selectedForecast);
            initWindReadings(selectedForecast.getDay());
            initWindReadings(selectedForecast.getNight());
            sparseArray.put(i, selectedForecast);
        }
        return sparseArray;
    }

    public static void checkTextEncoding(Forecast forecast){
        String dayDesc = forecast.getDay().getDescription();
        String nightDesc = forecast.getNight().getDescription();
        try {
            String nightUTF8 = new String(nightDesc.getBytes("ISO-8859-1"), "UTF-8");
            forecast.getNight().setDescription(nightUTF8);
            String dayUTF8 = new String(dayDesc.getBytes("ISO-8859-1"), "UTF-8");
            forecast.getDay().setDescription(dayUTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
