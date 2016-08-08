package design.ivan.app.weatherrss.MainScreen;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import design.ivan.app.weatherrss.Model.Place;
import design.ivan.app.weatherrss.R;
import design.ivan.app.weatherrss.databinding.BottomListItemBinding;

/**
 * Created by ivanm on 7/30/16.
 */
public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.PlaceViewHolder>{

    ArrayList<Place> arrayPlaces;


    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        BottomListItemBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.bottom_list_item, viewGroup, false);

        return new PlaceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder viewHolder, int position) {
        Place place = arrayPlaces.get(position);
        viewHolder.binding.setPlace(place);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if(arrayPlaces == null) return 0;
        return arrayPlaces.size();
    }

    public void loadDataSet(ArrayList<Place> arrayPlaces)
    {
        this.arrayPlaces = arrayPlaces;
        notifyDataSetChanged();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder{
        BottomListItemBinding binding;

        public PlaceViewHolder(BottomListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
