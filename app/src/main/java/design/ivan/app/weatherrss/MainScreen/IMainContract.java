package design.ivan.app.weatherrss.MainScreen;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.util.SparseArray;

import design.ivan.app.weatherrss.Model.Forecast;

/**
 * Created by ivanm on 7/12/16.
 */
public interface IMainContract {
    interface MainView {
        void initUi();
        void showSnackbar(@StringRes int resMessage);
        void showSnackbar(@StringRes int resMessage, boolean alwaysOn);
        void hideSnackbar();
        void setProgressIndicator(boolean active);
        void hideMessage();
        void showMessage(@StringRes int message);
        void enableUI(boolean activate);
        void loadData(SparseArray<Forecast> forecastSparseArray);
        int adapterItemCount();
        void showBottomSheet(Forecast forecast);
        void hideBottomSheet();
    }
    interface ActionListener {
        void getRSSFeed(boolean forced);
        void loadFeed();
        void initConnection();
        void doWebRequest();
        void setupListeners(Activity main);
        void clearListeners(Activity main);
        void showPlaces(String id);
    }
}
