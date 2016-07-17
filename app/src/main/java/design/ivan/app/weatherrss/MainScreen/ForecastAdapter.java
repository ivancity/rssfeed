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
import design.ivan.app.weatherrss.MainActivity;
import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Model.ForecastDate;
import design.ivan.app.weatherrss.R;

/**
 * Created by ivanm on 7/14/16.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{

    public static interface ForecastAdapterOnClickHandler {
        void onClickItem(String date);
    }

    private static final int VIEWTYPE_CURRENT = 0;
    private static final int VIEWTYPE_GENERIC = 1;

    Context context;
    ForecastAdapterOnClickHandler clickHandler;
    private SparseArray<Forecast> forecastSparseArray;

    public ForecastAdapter(MainActivity mainActivity){
        this.context = mainActivity;
        this.clickHandler = mainActivity;
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
                    layoutId = R.layout.main_list_item;
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
        ForecastDate night = forecastSparseArray.valueAt(position).getNight();
        ForecastDate day = forecastSparseArray.valueAt(position).getDay();
        switch (getItemViewType(position)){
            case VIEWTYPE_CURRENT:
                isCurrentDay = true;
                break;
            default:
                isCurrentDay = false;
        }

        if(isCurrentDay){
            holder.windMinNight.setText(night.getWindMin());
            holder.windMaxNight.setText(night.getWindMax());
            holder.windMinDay.setText(day.getWindMin());
            holder.windMaxDay.setText(day.getWindMax());
            holder.nightWeatherDesc.setText(night.getDescription());
            holder.dayWeatherDesc.setText(day.getDescription());
            holder.dayTempText.setText(day.getTempPhrase());
            holder.nightTempText.setText(night.getTempPhrase());
        }
        holder.tempMinNight.setText(night.getTempMinFormatted());
        holder.tempMaxNight.setText(night.getTempMaxFormatted());
        holder.tempMinDay.setText(day.getTempMinFormatted());
        holder.tempMaxDay.setText(day.getTempMaxFormatted());
        holder.dayTitle.setText(R.string.day);
        holder.nightTitle.setText(R.string.night);
        holder.dateTitle.setText(forecastSparseArray.valueAt(position).getFormattedDate());
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
        TextView dayTitle, nightTitle;
        TextView dayTempText, nightTempText;
        TextView dayWeatherDesc, nightWeatherDesc;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if(itemDay != null){
                tempMaxDay = ButterKnife.findById(itemDay, R.id.main_list_item_max_temp);
                tempMinDay = ButterKnife.findById(itemDay, R.id.main_list_item_min_temp);
                windMaxDay = ButterKnife.findById(itemDay, R.id.main_list_item_max_wind);
                windMinDay = ButterKnife.findById(itemDay, R.id.main_list_item_min_wind);
                dayTitle = ButterKnife.findById(itemDay, R.id.main_list_item_title);
                dayTempText = ButterKnife.findById(itemDay, R.id.main_list_item_temp_text);
                dayWeatherDesc = ButterKnife.findById(itemDay, R.id.main_list_item_weather_description);
            }
            if (itemNight != null) {
                tempMaxNight = ButterKnife.findById(itemNight, R.id.main_list_item_max_temp);
                tempMinNight = ButterKnife.findById(itemNight, R.id.main_list_item_min_temp);
                windMaxNight = ButterKnife.findById(itemNight, R.id.main_list_item_max_wind);
                windMinNight = ButterKnife.findById(itemNight, R.id.main_list_item_min_wind);
                nightTitle = ButterKnife.findById(itemNight, R.id.main_list_item_title);
                nightTempText = ButterKnife.findById(itemNight, R.id.main_list_item_temp_text);
                nightWeatherDesc = ButterKnife.findById(itemNight, R.id.main_list_item_weather_description);
            }
        }

        @Override
        public void onClick(View view) {

        }
    }

}
