package pl.michalgorny.voteme;

import android.app.Application;

import com.parse.Parse;

public class VoteMeAppplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, getString(R.string.app_id), getString(R.string.client_id));
    }
}
