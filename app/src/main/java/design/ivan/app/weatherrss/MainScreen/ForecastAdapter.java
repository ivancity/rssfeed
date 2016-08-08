package design.ivan.app.weatherrss.MainScreen;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import design.ivan.app.weatherrss.MainActivity;
import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.Model.ForecastDate;
import design.ivan.app.weatherrss.R;
import design.ivan.app.weatherrss.databinding.MainListItemBinding;
import design.ivan.app.weatherrss.databinding.MainListItemFirstBinding;

/**
 * Created by ivanm on 7/14/16.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{

    public interface ForecastAdapterOnClickHandler {
        void onClickItem(String date);
    }

    private static final int VIEWTYPE_CURRENT = 0;
    private static final int VIEWTYPE_GENERIC = 1;

    ForecastAdapterOnClickHandler clickHandler;
    private SparseArray<Forecast> forecastSparseArray;

    public ForecastAdapter(MainActivity mainActivity){
        this.clickHandler = mainActivity;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEWTYPE_CURRENT : VIEWTYPE_GENERIC;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if ( viewGroup instanceof RecyclerView ) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            if(viewType == VIEWTYPE_CURRENT){
                MainListItemFirstBinding bindingFirst = DataBindingUtil
                        .inflate(layoutInflater, R.layout.main_list_item_first, viewGroup, false);
                return new ForecastViewHolder(bindingFirst);
            }
            MainListItemBinding binding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.main_list_item, viewGroup, false);
            return new ForecastViewHolder(binding);
        } else {
            throw new RuntimeException("Something is wrong with the RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(final ForecastViewHolder holder, int position) {
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
            holder.firstBinding.itemDateTitle.setText(forecastSparseArray.valueAt(position).getFormattedDate());
            holder.firstBinding.itemNight.mainListItemMinWind.setText(night.getWindMin());
            holder.firstBinding.itemNight.mainListItemMaxWind.setText(night.getWindMax());
            holder.firstBinding.itemDay.mainListItemMinWind.setText(day.getWindMin());
            holder.firstBinding.itemDay.mainListItemMaxWind.setText(day.getWindMax());
            holder.firstBinding.itemNight.mainListItemWeatherDescription.setText(night.getDescription());
            holder.firstBinding.itemDay.mainListItemWeatherDescription.setText(day.getDescription());
            holder.firstBinding.itemDay.mainListItemTempText.setText(day.getTempPhrase());
            holder.firstBinding.itemNight.mainListItemTempText.setText(night.getTempPhrase());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    if(RecyclerView.NO_POSITION != position){
                        Forecast forecast = forecastSparseArray.valueAt(position);
                        clickHandler.onClickItem(forecast.getDate());
                    }

                }
            });
            return;
        }
        holder.binding.itemDateTitle.setText(forecastSparseArray.valueAt(position).getFormattedDate());
        holder.binding.itemNight.mainListItemMinTemp.setText(night.getTempMinFormatted());
        holder.binding.itemNight.mainListItemMaxTemp.setText(night.getTempMaxFormatted());
        holder.binding.itemDay.mainListItemMinTemp.setText(day.getTempMinFormatted());
        holder.binding.itemDay.mainListItemMaxTemp.setText(day.getTempMaxFormatted());
        holder.binding.itemDay.mainListItemTitle.setText(R.string.day);
        holder.binding.itemNight.mainListItemTitle.setText(R.string.night);
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

    public static class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MainListItemFirstBinding firstBinding;
        MainListItemBinding binding;

        //R.layout.main_list_item_first
        public ForecastViewHolder(MainListItemFirstBinding binding){
            this(binding.getRoot());
            firstBinding = binding;
        }

        //R.layout.main_list_item
        public ForecastViewHolder(MainListItemBinding binding){
            this(binding.getRoot());
            this.binding = binding;
        }

        public ForecastViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
