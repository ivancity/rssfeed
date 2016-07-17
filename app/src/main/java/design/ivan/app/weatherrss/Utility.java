package design.ivan.app.weatherrss;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Model.ForecastDate;
import design.ivan.app.weatherrss.Model.Wind;


public class Utility {
    private static final String TAG = "Utility";
    public static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    public static final String[] tens = {
            "",        // 0
            "",        // 1
            "twenty",  // 2
            "thirty",  // 3
            "forty",   // 4
            "fifty",   // 5
            "sixty",   // 6
            "seventy", // 7
            "eighty",  // 8
            "ninety"   // 9
    };

    public static SparseArray<Forecast> arrayMapToSparseArray(ArrayMap<String, Forecast> forecastData){
        SparseArray<Forecast> forecastSparseArray = new SparseArray<>();
        Forecast forecast;
        //faster for loop iteration if we stick to regular for loop
        for (int i = 0; i < forecastData.size(); i++) {
            forecast = forecastData.valueAt(i);
            forecastSparseArray.append(i, forecast);
        }
        return forecastSparseArray;
    }

    public static SparseArray<Forecast> prepareSparseArray(Context context, ArrayList<Forecast> forecastList){
        Forecast currentForecast;
        ForecastDate day, night;
        SparseArray<Forecast> sparseArray = new SparseArray<>();
        for (int i = 0; i < forecastList.size(); i++) {
            currentForecast = forecastList.get(i);
            formatDate(context, currentForecast, i);
            day = currentForecast.getDay();
            night = currentForecast.getNight();
            initTempFormat(context, day);
            initTempFormat(context, night);
            checkTextEncoding(currentForecast);
            initWindReadings(context, day);
            initWindReadings(context, night);
            day.setTempMaxWord(numberToWord(Integer.valueOf(day.getTempMax())));
            day.setTempMinWord(numberToWord(Integer.valueOf(day.getTempMin())));
            night.setTempMaxWord(numberToWord(Integer.valueOf(night.getTempMax())));
            night.setTempMinWord(numberToWord(Integer.valueOf(night.getTempMin())));
            initTempPhrase(context, day);
            initTempPhrase(context, night);
            sparseArray.put(i, currentForecast);
        }
        return sparseArray;
    }

    public static void formatDate(Context context, Forecast forecast, int index){
        String dateFormatted = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd", Locale.US);
        Calendar forecastCal = Calendar.getInstance();
        Calendar todayCal = Calendar.getInstance(Locale.US);
        try {
            forecastCal.setTime(sdf.parse(forecast.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //get year, day, month
        int year = forecastCal.get(Calendar.YEAR);
        int day = forecastCal.get(Calendar.DAY_OF_MONTH);
        String monthName = new SimpleDateFormat("MMM", Locale.US).format(forecastCal.getTime());
        if(index == 0){
            int calDay = forecastCal.get(Calendar.DAY_OF_YEAR);
            int todayDay = todayCal.get(Calendar.DAY_OF_YEAR);
            if(calDay == todayDay){
                dateFormatted = context.getString(R.string.format_today, monthName, day);
            }else if(todayDay < calDay && (todayDay + 2 == calDay + 1)){
                dateFormatted = context.getString(R.string.format_tomorrow, monthName, day);
            } else{
                dateFormatted = context.getString(R.string.format_date, monthName, day, year);
            }
            Log.d(TAG, "formatDate: index 0 " + dateFormatted);
            forecast.setFormattedDate(dateFormatted);
            return;
        }

        dateFormatted = context.getString(R.string.format_date, monthName, day, year);
        forecast.setFormattedDate(dateFormatted);

        Log.d(TAG, "formatDate: dateformatted = " + dateFormatted);

        //Log.d(TAG, "test");


    }

    public static void initTempPhrase(Context context, ForecastDate forecastDate){
        int formatTempWords = R.string.format_temperature_words;
        forecastDate.setTempPhrase(
                context.getString(
                        formatTempWords,
                        forecastDate.getTempMaxWord(),
                        forecastDate.getTempMinWord()
                )
        );
    }

    public static void initTempFormat(Context context, ForecastDate forecastDate){
        int formatTemperature = R.string.format_temperature;
        forecastDate.setTempMaxFormatted(
                context.getString(
                        formatTemperature,
                        forecastDate.getTempMax()
                )
        );
        forecastDate.setTempMinFormatted(
                context.getString(
                        formatTemperature,
                        forecastDate.getTempMin()
                )
        );
    }

    public static String numberToWord(final int n){
        if (n < 0) {
            return "minus " + numberToWord(-n);
        }
        if (n < 20) {
            return units[n];
        }
        return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
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

    public static void initWindReadings(Context context, ForecastDate forecastDate){
        int speedMax, speedMin, temp;
        int formatWind = R.string.format_wind;
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
        forecastDate.setWindMax(String.valueOf(
                context.getString(
                        formatWind,
                        speedMax)
        ));
        forecastDate.setWindMin(String.valueOf(
                context.getString(
                        formatWind,
                        speedMin))
        );
    }

    public static boolean isAppOnline(Context context){
        //get network info
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        //send boolean if it is connected or not
        return  activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
