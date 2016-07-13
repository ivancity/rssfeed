package design.ivan.app.weatherrss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import design.ivan.app.weatherrss.MainScreen.IMainContract;
import design.ivan.app.weatherrss.MainScreen.MainPresenter;
import design.ivan.app.weatherrss.Model.Forecasts;
import design.ivan.app.weatherrss.Model.RssForecast;
import design.ivan.app.weatherrss.Repo.Injection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements IMainContract.MainView{
    private static final String TAG = "MainActivity";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    IMainContract.ActionListener actionListener;

    static final String BASE_URL = "http://www.ilmateenistus.ee/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionListener = new MainPresenter(Injection.loadForecastRepository(), this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        RssForecast rssService = retrofit.create(RssForecast.class);

        Call<Forecasts> call = rssService.getForecasts();
        call.enqueue(new Callback<Forecasts>() {
            @Override
            public void onResponse(Call<Forecasts> call, Response<Forecasts> response) {
                //Log.d(TAG, "onResponse: response: " + response.body());
                int networkCode = response.raw().networkResponse().code();
                if(networkCode == 200){
                    //TODO do something with parsed content
                }
            }

            @Override
            public void onFailure(Call<Forecasts> call, Throwable t) {
                Log.d(TAG, "onFailure: message: " + t.getMessage());
            }
        });
    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void hideMessage() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void enableUI(boolean activate) {

    }
}
