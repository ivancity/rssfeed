package design.ivan.app.weatherrss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import design.ivan.app.weatherrss.MainScreen.IMainContract;
import design.ivan.app.weatherrss.MainScreen.MainPresenter;
import design.ivan.app.weatherrss.Repo.Injection;

public class MainActivity extends AppCompatActivity implements IMainContract.MainView{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    IMainContract.ActionListener actionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionListener = new MainPresenter(Injection.loadForecastRepository(), this);
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
