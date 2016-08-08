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
            //we are binding a Model to the layout with its respective values to the variable defined on the layout xml
            holder.firstBinding.itemDay.setDate(day);
            holder.firstBinding.itemNight.setDate(night);
            //bind titles and anything that goes directly to a View with no Model related to it.
            holder.firstBinding.itemDateTitle.setText(forecastSparseArray.valueAt(position).getFormattedDate());
            holder.firstBinding.itemDay.mainListItemTitle.setText(R.string.day);
            holder.firstBinding.itemNight.mainListItemTitle.setText(R.string.night);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    if(RecyclerView.NO_POSITION != position){ //always check we might get a NO_POSITION because user clicked too fast
                        Forecast forecast = forecastSparseArray.valueAt(position);
                        clickHandler.onClickItem(forecast.getDate());
                    }

                }
            });
            holder.firstBinding.executePendingBindings();//important to add this line otherwise recyclerView might have to measure twice before getting it right
            return;
        }
        //Binding to the layout using a Model
        holder.binding.itemNight.setDate(night);
        holder.binding.itemDay.setDate(day);
        //Binding to the layout directly by setting all TextViews from code
        holder.binding.itemDateTitle.setText(forecastSparseArray.valueAt(position).getFormattedDate());
        holder.binding.itemDay.mainListItemTitle.setText(R.string.day);
        holder.binding.itemNight.mainListItemTitle.setText(R.string.night);
        holder.binding.executePendingBindings();//always execute this method for performance reasons
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
