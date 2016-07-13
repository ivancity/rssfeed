package design.ivan.app.weatherrss;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import design.ivan.app.weatherrss.MainScreen.IMainContract;
import design.ivan.app.weatherrss.MainScreen.MainPresenter;
import design.ivan.app.weatherrss.Repo.Injection;

public class MainActivity extends AppCompatActivity implements IMainContract.MainView{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static final String TAG = "MainActivity";

    Snackbar snackbar;
    IMainContract.ActionListener actionListener;
    @BindView(R.id.layout_main_root)
    RelativeLayout root;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_button_refresh)
    ImageButton refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        actionListener = new MainPresenter(Injection.loadForecastRepository(), this);
        actionListener.getRSSFeed();
    }

    @Override
    public void showSnackbar(int resMessage) {
        showSnackbar(resMessage, false);
    }

    @Override
    public void showSnackbar(int resMessage, boolean alwaysOn) {
        if(alwaysOn){
            snackbar = Snackbar.make(root, resMessage, Snackbar.LENGTH_INDEFINITE);
        } else {
            snackbar = Snackbar.make(root, resMessage, Snackbar.LENGTH_LONG);
        }
        snackbar.show();
    }

    @Override
    public void hideSnackbar() {
        snackbar.dismiss();
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

    @OnClick(R.id.main_button_refresh)
    public void refreshClick(){
        Log.d(TAG, "refreshClick: ");
    }

}
