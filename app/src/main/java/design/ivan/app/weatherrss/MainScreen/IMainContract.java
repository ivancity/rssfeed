package design.ivan.app.weatherrss.MainScreen;

/**
 * Created by ivanm on 7/12/16.
 */
public interface IMainContract {
    interface MainView {
        void showSnackbar(int resMessage);
        void hideSnackbar();
        void setProgressIndicator(boolean active);
        void hideMessage();
        void showMessage(String message);
        void enableUI(boolean activate);
    }
    interface ActionListener {
        void getRSSFeed();
        void loadFeed();
        void initConnection();
    }
}
