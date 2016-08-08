package design.ivan.app.weatherrss;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;

import design.ivan.app.weatherrss.MainScreen.BottomSheetAdapter;
import design.ivan.app.weatherrss.MainScreen.ForecastAdapter;
import design.ivan.app.weatherrss.MainScreen.IMainContract;
import design.ivan.app.weatherrss.MainScreen.MainPresenter;
import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Repo.Injection;
import design.ivan.app.weatherrss.databinding.ActivityMainBinding;

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
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        //use android data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
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
        if (binding.mainRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) binding.mainRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
            binding.mainRecyclerView.scrollToPosition(scrollPosition);
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
        //using binding auto generated object to initialize some views

        //toolbar
        setSupportActionBar(binding.mainToolbar);
        binding.mainToolbar.setTitleTextColor(Color.WHITE);

        //main recycler view
        binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.mainRecyclerView.setHasFixedSize(true);
        forecastAdapter = new ForecastAdapter(this);
        binding.mainRecyclerView.setAdapter(forecastAdapter);

        //bottom sheet recycler view. included <layout> tags to all necessary layouts
        binding.bottomSheetInclude.bottomRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.bottomSheetInclude.bottomRecycler.setHasFixedSize(true);
        bottomAdapter = new BottomSheetAdapter();
        binding.bottomSheetInclude.bottomRecycler.setAdapter(bottomAdapter);

        mBottomSheetBehavior = BottomSheetBehavior.from(binding.frameBottomSheet);
    }

    @Override
    public void showSnackbar(int resMessage) {
        showSnackbar(resMessage, false);
    }

    @Override
    public void showSnackbar(int resMessage, boolean alwaysOn) {
        if(alwaysOn){
            snackbar = Snackbar.make(binding.layoutMainRoot, resMessage, Snackbar.LENGTH_INDEFINITE);
        } else {
            snackbar = Snackbar.make(binding.layoutMainRoot, resMessage, Snackbar.LENGTH_LONG);
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
        binding.mainTextMessage.setText(resMessage);
    }

    @Override
    public void enableUI(boolean activate) {
        if(activate){
            binding.mainTextMessage.setVisibility(View.GONE);
            binding.mainRecyclerView.setVisibility(View.VISIBLE);
        } else {
            binding.mainTextMessage.setVisibility(View.VISIBLE);
            binding.mainRecyclerView.setVisibility(View.GONE);
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
        binding.bottomSheetInclude.bottomSheetTitle.setText(forecast.getFormattedDate());
        bottomAdapter.loadDataSet(forecast.getAdapterReadyList());
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void hideBottomSheet() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    //+++ End MainPresenter implementation +++

    //@OnClick(R.id.main_button_refresh)
    public void refreshClick(View view){
        actionListener.getRSSFeed(true);
    }


    //ForecastAdapterOnClickHandler implementation from ForecastAdapter
    @Override
    public void onClickItem(String date) {
        Log.d(TAG, "onClickItem: clicked on date" + date);
        actionListener.showPlaces(date);
    }
}
