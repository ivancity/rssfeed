package design.ivan.app.weatherrss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import design.ivan.app.weatherrss.MainScreen.IMainContract;
import design.ivan.app.weatherrss.MainScreen.MainPresenter;
import design.ivan.app.weatherrss.Repo.Injection;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements IMainContract.MainView{
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
