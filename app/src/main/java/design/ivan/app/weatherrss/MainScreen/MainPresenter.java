package design.ivan.app.weatherrss.MainScreen;

import android.support.annotation.NonNull;

import design.ivan.app.weatherrss.Repo.IForecastRepository;

/**
 * Created by ivanm on 7/12/16.
 */
public class MainPresenter implements IMainContract.ActionListener {

    IMainContract.MainView mainView;
    IForecastRepository forecastRepository;

    public MainPresenter(@NonNull IForecastRepository forecastRepository, IMainContract.MainView view){
        this.forecastRepository = forecastRepository;
        mainView = view;
    }

    @Override
    public void getRSSFeed() {

    }

    @Override
    public void loadFeed() {

    }
}
