package design.ivan.app.weatherrss.Repo;

import android.support.annotation.NonNull;

/**
 * Created by ivanm on 7/12/16.
 */
public class ForecastRepositories {

    private static IForecastRepository repository = null;

    public synchronized static IForecastRepository getInMemoryRepoInstance(@NonNull IForecastService forecastService) {
        //checkNotNull(forecastService);
        if (null == repository) {
            //initialize a new repository using the PeopleServiceApi that this method receives.
            repository = new InMemoryForecastRepo(forecastService);
        }
        return repository;
    }

}
