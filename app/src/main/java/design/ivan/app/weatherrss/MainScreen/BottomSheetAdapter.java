package design.ivan.app.weatherrss.MainScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.ivan.app.weatherrss.Model.Place;
import design.ivan.app.weatherrss.R;

/**
 * Created by ivanm on 7/30/16.
 */
public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.PlaceViewHolder>{

    ArrayList<Place> arrayPlaces;


    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bottom_list_item, viewGroup, false);
        view.setFocusable(true);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder viewHolder, int position) {
        Place place = arrayPlaces.get(position);
        viewHolder.title.setText(place.getName());
        viewHolder.maxTemp.setText(place.getTempMax());
        viewHolder.maxPhenomenon.setText(place.getPhenomenon());
        viewHolder.minTemp.setText(place.getTempMin());
        viewHolder.minPhenomenon.setText(place.getPhenomenonExtra());
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
        @BindView(R.id.bottom_item_title)
        TextView title;
        @BindView(R.id.bottom_item_max_temp)
        TextView maxTemp;
        @BindView(R.id.bottom_item_min_temp)
        TextView minTemp;
        @BindView(R.id.bottom_item_max_phen)
        TextView maxPhenomenon;
        @BindView(R.id.bottom_item_min_phen)
        TextView minPhenomenon;
        public PlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
