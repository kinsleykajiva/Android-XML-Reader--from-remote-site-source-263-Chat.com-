package Supports;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by kinsley kajiva on 4/19/2016.
 */
public class ConnectivityReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(ConnectivityReceiver.class.getSimpleName(), "action: "
                + intent.getAction());
    }

}