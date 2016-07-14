package design.ivan.app.weatherrss.MainScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by ivanm on 7/14/16.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{

    public static interface ForecastAdapterOnClickHandler {
        void onClick(String date);
    }

    public ForecastAdapter(Context context, ForecastAdapterOnClickHandler clickHandler){

    }


    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onClick(View view) {

        }
    }

}
