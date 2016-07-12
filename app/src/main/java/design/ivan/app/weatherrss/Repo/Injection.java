package design.ivan.app.weatherrss.Repo;

/**
 * Created by ivanm on 7/12/16.
 */
public class Injection {
    public static IForecastRepository loadForecastRepository(){
        return ForecastRepositories.getInMemoryRepoInstance(new ForecastServiceImp());
    }
}
