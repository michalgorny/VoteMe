package pl.michalgorny.voteme;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;

import pl.michalgorny.voteme.model.Ratings;

public class VoteMeAppplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Ratings.class);
        Parse.initialize(this, getString(R.string.app_id), getString(R.string.client_id));
        ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key), getString(R.string.twitter_consumer_secret));
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));
    }
}
