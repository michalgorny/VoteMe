package pl.michalgorny.voteme.gcm;

import android.content.Context;
import android.content.Intent;

import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;

import pl.michalgorny.voteme.LaunchActivity;

public class PushBroadcastReceiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        context.startActivity(new Intent(context, LaunchActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        ParseAnalytics.trackAppOpenedInBackground(intent);
    }

}
