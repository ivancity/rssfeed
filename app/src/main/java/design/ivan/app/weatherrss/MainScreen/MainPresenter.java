package design.ivan.app.weatherrss.MainScreen;

import android.support.annotation.NonNull;
import android.util.Log;

import design.ivan.app.weatherrss.Model.Forecasts;
import design.ivan.app.weatherrss.Model.RssForecast;
import design.ivan.app.weatherrss.R;
import design.ivan.app.weatherrss.Repo.IForecastRepository;
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
    IForecastRepository forecastRepository;

    public MainPresenter(@NonNull IForecastRepository forecastRepository, IMainContract.MainView view){
        this.forecastRepository = forecastRepository;
        mainView = view;
    }

    //** ActionListener implementation **//

    @Override
    public void getRSSFeed() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        RssForecast rssService = retrofit.create(RssForecast.class);

        mainView.showSnackbar(R.string.syncing);
        Call<Forecasts> call = rssService.getForecasts();
        call.enqueue(this);
    }

    @Override
    public void loadFeed() {

    }

    @Override
    public void initConnection() {

    }

    //++ End ActionListener implementation ++//

    //** Retrofit 2 callback implementation **//

    @Override
    public void onResponse(Call<Forecasts> call, Response<Forecasts> response) {
        Log.d(TAG, "onResponse: response: " + response.body());
        int networkCode = response.raw().networkResponse().code();
        if(networkCode == 200){
            //TODO do something with parsed content
        }
    }

    @Override
    public void onFailure(Call<Forecasts> call, Throwable t) {
        Log.d(TAG, "onFailure: message: " + t.getMessage());
    }

    //++ End Retrofit 2 callback implementation ++//
}
