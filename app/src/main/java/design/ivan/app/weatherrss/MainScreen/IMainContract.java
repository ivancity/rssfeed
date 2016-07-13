package design.ivan.app.weatherrss.MainScreen;

import android.support.annotation.StringRes;

/**
 * Created by ivanm on 7/12/16.
 */
public interface IMainContract {
    interface MainView {
        void showSnackbar(@StringRes int resMessage);
        void showSnackbar(@StringRes int resMessage, boolean alwaysOn);
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
        void doWebRequest();
    }
}
