package design.ivan.app.weatherrss;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import design.ivan.app.weatherrss.MainScreen.ForecastAdapter;
import design.ivan.app.weatherrss.MainScreen.IMainContract;
import design.ivan.app.weatherrss.MainScreen.MainPresenter;
import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Repo.Injection;

public class MainActivity extends AppCompatActivity implements IMainContract.MainView, ForecastAdapter.ForecastAdapterOnClickHandler{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static final String TAG = "MainActivity";
    private int scrollPosition;

    Snackbar snackbar;
    IMainContract.ActionListener actionListener;
    ForecastAdapter forecastAdapter;
    @BindView(R.id.layout_main_root)
    RelativeLayout root;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_button_refresh)
    ImageButton refreshButton;
    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_text_message)
    TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        forecastAdapter = new ForecastAdapter(this);
        recyclerView.setAdapter(forecastAdapter);
        actionListener = new MainPresenter(Injection.loadForecastRepository(), this);
        showMessage(R.string.no_data);
        actionListener.getRSSFeed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
            recyclerView.scrollToPosition(scrollPosition);
        }
    }

    //*** Main Presenter implementation ***//

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
        enableUI(true);
    }

    @Override
    public void showMessage(int resMessage) {
        enableUI(false);
        txtMessage.setText(resMessage);
    }

    @Override
    public void enableUI(boolean activate) {
        if(activate){
            txtMessage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            txtMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadData(SparseArray<Forecast> forecastSparseArray) {
        if(forecastAdapter == null){
            Log.d(TAG, "loadData: forecast adapter not found");
            return;
        }
        hideMessage();
        forecastAdapter.loadSparseArray(forecastSparseArray);

    }

    //+++ End MainPresenter implementation +++

    @OnClick(R.id.main_button_refresh)
    public void refreshClick(){
        Log.d(TAG, "refreshClick: ");
    }


    //ForecastAdapterOnClickHandler implementation from ForecastAdapter
    @Override
    public void onClickItem(String date) {

    }
}
