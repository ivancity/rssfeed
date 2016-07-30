package design.ivan.app.weatherrss;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import design.ivan.app.weatherrss.MainScreen.BottomSheetAdapter;
import design.ivan.app.weatherrss.MainScreen.ForecastAdapter;
import design.ivan.app.weatherrss.MainScreen.IMainContract;
import design.ivan.app.weatherrss.MainScreen.MainPresenter;
import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Repo.Injection;

public class MainActivity extends AppCompatActivity implements IMainContract.MainView,
        ForecastAdapter.ForecastAdapterOnClickHandler
{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static final String TAG = "MainActivity";
    private int scrollPosition;


    private Snackbar snackbar;
    IMainContract.ActionListener actionListener;
    private ForecastAdapter forecastAdapter;
    private BottomSheetAdapter bottomAdapter;
    private BottomSheetBehavior<FrameLayout> mBottomSheetBehavior;
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
    @BindView(R.id.bottom_recycler)
    RecyclerView bottomRecycler;
    @BindView(R.id.bottom_sheet_title)
    TextView txtBottomSheetTitle;
    @BindView(R.id.frame_bottom_sheet)
    FrameLayout frameBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUi();
        actionListener = new MainPresenter(Injection.loadForecastRepository(), this);
        showMessage(R.string.no_data);
        actionListener.getRSSFeed(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        //if it was previously scrolled find the correct position to display
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
            recyclerView.scrollToPosition(scrollPosition);
        }
        actionListener.setupListeners(this);
        if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
            hideBottomSheet();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        actionListener.clearListeners(this);
    }

    //*** Main Presenter implementation ***//

    @Override
    public void initUi() {
        //toolbar
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        //main recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        forecastAdapter = new ForecastAdapter(this);
        recyclerView.setAdapter(forecastAdapter);

        //bottom sheet recycler view
        bottomRecycler.setLayoutManager(new LinearLayoutManager(this));
        bottomRecycler.setHasFixedSize(true);
        bottomAdapter = new BottomSheetAdapter();
        bottomRecycler.setAdapter(bottomAdapter);

        mBottomSheetBehavior = BottomSheetBehavior.from(frameBottomSheet);
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

    @Override
    public int adapterItemCount() {
        if(forecastAdapter != null){
            return forecastAdapter.getItemCount();
        }
        return 0;
    }

    @Override
    public void showBottomSheet(Forecast forecast) {
        if(forecast == null){
            Log.d(TAG, "showBottomSheet: Bottom sheet not loaded null forecast object");
            return;
        }
        txtBottomSheetTitle.setText(forecast.getFormattedDate());
        bottomAdapter.loadDataSet(forecast.getAdapterReadyList());
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void hideBottomSheet() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    //+++ End MainPresenter implementation +++

    @OnClick(R.id.main_button_refresh)
    public void refreshClick(){
        actionListener.getRSSFeed(true);
    }


    //ForecastAdapterOnClickHandler implementation from ForecastAdapter
    @Override
    public void onClickItem(String date) {
        Log.d(TAG, "onClickItem: clicked on date" + date);
        actionListener.showPlaces(date);
    }
}
