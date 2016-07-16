package design.ivan.app.weatherrss.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import design.ivan.app.weatherrss.Utility;

/**
 * Created by ivanm on 7/16/16.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    public interface NetworkChangeListener {
        void onNetworkChange(boolean connected);
    }
    private static final String TAG = "NetworkChangeReceiver";

    NetworkChangeListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: here ");
        if(listener == null)
            return;
        listener.onNetworkChange(Utility.isAppOnline(context));
    }

    public void addListener(NetworkChangeListener listener){
        this.listener = listener;
    }
}
