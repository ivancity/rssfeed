package design.ivan.app.weatherrss.MainScreen;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.R;

/**
 * Created by ivanm on 7/14/16.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{

    public static interface ForecastAdapterOnClickHandler {
        void onClick(String date);
    }

    private static final int VIEWTYPE_CURRENT = 0;
    private static final int VIEWTYPE_GENERIC = 1;

    Context context;
    ForecastAdapterOnClickHandler clickHandler;
    private SparseArray<Forecast> forecastSparseArray;

    public ForecastAdapter(Context context, ForecastAdapterOnClickHandler clickHandler){
        this.context = context;
        this.clickHandler = clickHandler;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEWTYPE_CURRENT : VIEWTYPE_GENERIC;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if ( viewGroup instanceof RecyclerView ) {
            int layoutId = -1;
            switch (viewType) {
                case VIEWTYPE_CURRENT: {
                    layoutId = R.layout.main_list_item_first;
                    break;
                }
                case VIEWTYPE_GENERIC: {
                    layoutId = R.layout.main_list_item_generic;
                    break;
                }
            }
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
            view.setFocusable(true);
            return new ForecastViewHolder(view);
        } else {
            throw new RuntimeException("Something is wrong with the RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        boolean isCurrentDay;
        Forecast forecast = forecastSparseArray.valueAt(position);
        switch (getItemViewType(position)){
            case VIEWTYPE_CURRENT:
                isCurrentDay = true;
                break;
            default:
                isCurrentDay = false;
        }

        if(isCurrentDay){
            //TODO handle wind stuff

        }

        //TODO handle temperature which both view types have


    }

    @Override
    public int getItemCount() {
        if(forecastSparseArray == null) return 0;
        return forecastSparseArray.size();
    }

    public void loadSparseArray(SparseArray<Forecast> forecastSparseArray){
        this.forecastSparseArray = forecastSparseArray;
        notifyDataSetChanged();
        //TODO consider modifying UI if sparse array is empty at this point.
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Nullable @BindView(R.id.item_date_title)
        TextView dateTitle;
        @Nullable @BindView(R.id.item_day)
        GridLayout itemDay;
        @Nullable @BindView(R.id.item_night)
        GridLayout itemNight;
        TextView tempMaxDay, tempMinDay;
        TextView windMaxDay, windMinDay;
        TextView tempMaxNight, tempMinNight;
        TextView windMaxNight, windMinNight;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if(itemDay != null){
                tempMaxDay = ButterKnife.findById(itemDay, R.id.main_list_item_max_temp);
                tempMinDay = ButterKnife.findById(itemDay, R.id.main_list_item_min_temp);
                windMaxDay = ButterKnife.findById(itemDay, R.id.main_list_item_max_wind);
                windMinDay = ButterKnife.findById(itemDay, R.id.main_list_item_min_wind);
            }
            if (itemNight != null) {
                tempMaxNight = ButterKnife.findById(itemNight, R.id.main_list_item_max_temp);
                tempMinNight = ButterKnife.findById(itemNight, R.id.main_list_item_min_temp);
                windMaxNight = ButterKnife.findById(itemNight, R.id.main_list_item_max_wind);
                windMinNight = ButterKnife.findById(itemNight, R.id.main_list_item_min_wind);
            }


        }

        @Override
        public void onClick(View view) {

        }
    }

}
