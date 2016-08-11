package design.ivan.app.weatherrss.MainScreen;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import design.ivan.app.weatherrss.Model.Forecast;
import design.ivan.app.weatherrss.R;
import design.ivan.app.weatherrss.databinding.MainListItemBinding;
import design.ivan.app.weatherrss.databinding.MainListItemFirstBinding;

/**
 * Created by ivanm on 7/14/16.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{

    private static final String TAG = "ForecastAdapter";

    public interface ForecastAdapterOnClickHandler {
        void onClickItem(String date);
    }

    private static final int VIEWTYPE_CURRENT = 0;
    private static final int VIEWTYPE_GENERIC = 1;

    private SparseArray<Forecast> forecastSparseArray;
    IMainContract.ActionListener actionListener;

    public ForecastAdapter(IMainContract.ActionListener actionListener){ this.actionListener = actionListener;}

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
        //Getting our variable Forecast that is going to be used in the layout binding
        Forecast forecast = forecastSparseArray.valueAt(position);
        switch (getItemViewType(position)){
            case VIEWTYPE_CURRENT:
                isCurrentDay = true;
                break;
            default:
                isCurrentDay = false;
        }

        if(isCurrentDay){
            //we are binding a Model Forecast to the layout with its respective values to the variable defined on the layout xml
            holder.firstBinding.setForecast(forecast);
            //we are doing this to enable onClick to refer directly to our Presenter using a lambda expression in the layout file
            holder.firstBinding.setActionListener(actionListener);
            holder.firstBinding.executePendingBindings();//important to add this line otherwise recyclerView might have to measure twice before getting it right
            return;
        }
        //Binding to the layout using a Model Forecast
        holder.binding.setForecast(forecast);
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
