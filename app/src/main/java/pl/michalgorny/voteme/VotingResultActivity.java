package pl.michalgorny.voteme;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;

import pl.michalgorny.voteme.model.Ratings;


public class VotingResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_result);

        /**
         * TODO: Get results from server. Use @ParseQuery and @Ratings classes
         */

    }

    private void handleResponse(List<Ratings> ratings) {
        HashMap<String, Float> values = new HashMap<>(ratings.size());

        /**
         * TODO: Get data from retrieved object and pass them to fragment displays it.
         */

        addFragment(values);
    }

    private Float calculateAverage(double feelings, int size) {
        return new Float(feelings / size);
    }

    private void addFragment(HashMap<String, Float> values) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, RatingsFragment.newInstance(values))
                .commit();
    }

}
