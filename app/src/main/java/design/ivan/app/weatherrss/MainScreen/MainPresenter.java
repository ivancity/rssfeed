package design.ivan.app.weatherrss.MainScreen;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

import design.ivan.app.weatherrss.MainActivity;
import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Model.Forecasts;
import design.ivan.app.weatherrss.Model.RssForecast;
import design.ivan.app.weatherrss.R;
import design.ivan.app.weatherrss.Repo.IForecastRepository;
import design.ivan.app.weatherrss.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by ivanm on 7/12/16.
 */
public class MainPresenter implements IMainContract.ActionListener, Callback<Forecasts> {
    static final String BASE_URL = "http://www.ilmateenistus.ee/";
    private static final String TAG = "MainPresenter";

    IMainContract.MainView mainView;
    private IForecastRepository forecastRepository;
    private Retrofit retrofit;
    private RssForecast rssService;

    public MainPresenter(@NonNull IForecastRepository forecastRepository, IMainContract.MainView view){
        this.forecastRepository = forecastRepository;
        mainView = view;
    }

    //** ActionListener implementation **//

    @Override
    public void getRSSFeed() {
        initConnection();
        doWebRequest();
    }

    @Override
    public void loadFeed() {
        forecastRepository.getForecastList(new IForecastRepository.LoadForecastCallback() {
            @Override
            public void onForecastLoaded(SparseArray<Forecast> forecastSparseArray) {
                Log.d(TAG, "onForecastLoaded: " + forecastSparseArray);
            }
        });
    }

    @Override
    public void initConnection() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        rssService = retrofit.create(RssForecast.class);
    }

    @Override
    public void doWebRequest() {
        mainView.showSnackbar(R.string.syncing, true);
        Call<Forecasts> call = rssService.getForecasts();
        call.enqueue(this);
    }

    //++ End ActionListener implementation ++//

    //** Retrofit 2 callback implementation **//

    @Override
    public void onResponse(Call<Forecasts> call, Response<Forecasts> response) {
        ((MainActivity)mainView).runOnUiThread(new Runnable() {
            public void run() {
                mainView.hideSnackbar();
            }
        });
        //int networkCode = response.raw().networkResponse().code();
        if(response.isSuccessful()){
            ArrayList<Forecast> forecastList = (ArrayList<Forecast>) response.body().getForecasts();
            if(forecastList.size()<=0)
                return;
            SparseArray<Forecast> forecastSparseArray = Utility.listToSparseArray(forecastList);
            forecastRepository.saveArrayForecast(forecastSparseArray, new IForecastRepository.SaveForecastArrayCallback() {
                @Override
                public void onSavedArray(boolean saved) {
                    Log.d(TAG, "onSavedArray: Forecasts cached");
                    loadFeed();
                }
            });
        }
    }

    @Override
    public void onFailure(Call<Forecasts> call, Throwable t) {
        Log.d(TAG, "onFailure: message: " + t.getMessage());
        ((MainActivity)mainView).runOnUiThread(new Runnable() {
            public void run() {

            }
        });
    }

    //++ End Retrofit 2 callback implementation ++//

}
