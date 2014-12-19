package pl.michalgorny.voteme;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.ParseClassName;
import com.parse.ParseCloud;
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

        queryCloud();

        ParseQuery<Ratings> query = ParseQuery.getQuery("ratings");

        query.findInBackground(new FindCallback<Ratings>() {
            @Override
            public void done(List<Ratings> parseObjects, ParseException e) {
                if (e == null) {
                    handleResponse(parseObjects);
                } else {
                    Toast.makeText(VotingResultActivity.this, getString(R.string.error_sent), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void queryCloud() {
        ParseCloud.callFunctionInBackground("averageFeelings", new HashMap<String, Object>(), new FunctionCallback<Double>() {
            @Override
            public void done(Double result, ParseException e) {
                if (e == null){
                    Toast.makeText(VotingResultActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleResponse(List<Ratings> ratings) {
        HashMap<String, Float> values = new HashMap<>(ratings.size());

        double feelings = 0;
        double necessity = 0;
        double place = 0;
        double tasks = 0;
        double details = 0;
        double trainer = 0;


        for (Ratings rating : ratings) {
            feelings += rating.getDouble("feelings");
            necessity += rating.getDouble("necessity");
            tasks += rating.getDouble("tasks");
            details += rating.getDouble("details");
            trainer += rating.getDouble("trainer");
            place += rating.getDouble("place");
        }

        int size = ratings.size();

        values.put(getString(R.string.results_general), calculateAverage(feelings, size));
        values.put(getString(R.string.results_necessity), calculateAverage(necessity, size));
        values.put(getString(R.string.results_tasks), calculateAverage(tasks, size));
        values.put(getString(R.string.results_details), calculateAverage(details, size));
        values.put(getString(R.string.results_trainer), calculateAverage(trainer, size));
        values.put(getString(R.string.results_place), calculateAverage(place, size));

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
