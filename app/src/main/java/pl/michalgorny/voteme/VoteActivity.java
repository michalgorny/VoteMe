package pl.michalgorny.voteme;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;


public class VoteActivity extends ActionBarActivity {

    @InjectViews({R.id.ratingBarDetails, R.id.ratingBarFeelings, R.id.ratingBarNecessity, R.id.ratingBarPlace, R.id.ratingBarTasks, R.id.ratingBarTrainer})
    List<RatingBar> mRatingsBars;

    @InjectView(R.id.ratingBarDetails)
    RatingBar mRatingBarDetails;

    @InjectView(R.id.ratingBarFeelings)
    RatingBar mRatingBarFeelings;

    @InjectView(R.id.ratingBarNecessity)
    RatingBar mRatingBarNecessity;

    @InjectView(R.id.ratingBarPlace)
    RatingBar mRatingBarPlace;

    @InjectView(R.id.ratingBarTasks)
    RatingBar mRatingBarTasks;

    @InjectView(R.id.ratingBarTrainer)
    RatingBar mRatingBarTrainer;

    @InjectView(R.id.formAdditionalText)
    EditText mAddtionalNotes;

    @InjectView(R.id.formMoreTopics)
    EditText mMoreTopics;

    static final ButterKnife.Action<RatingBar> DEFAULT_RATE = new ButterKnife.Action<RatingBar>() {
        @Override
        public void apply(RatingBar view, int index) {
            view.setRating(0.0f);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.clearFormButton)
    public void setQuestionnaireToDefault(){
        ButterKnife.apply(mRatingsBars, DEFAULT_RATE);
        mAddtionalNotes.setText("");
        mMoreTopics.setText("");
    }

    @OnClick(R.id.sendFormButton)
    public void sendQuestionnaire() {
        ParseObject ratings = new ParseObject("ratings");

        Map<String, Object> results = getResults();

        for (String key : results.keySet()) {
            ratings.add(key, results.get(key));
        }

        ratings.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(VoteActivity.this, R.string.vote_sent, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    e.printStackTrace();
                    Toast.makeText(VoteActivity.this, R.string.error_sent, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public Map<String,Object> getResults() {
        Map<String, Object> results = new HashMap<>();
        results.put("details", mRatingBarDetails.getRating());
        results.put("feelings", mRatingBarFeelings.getRating());
        results.put("necessity", mRatingBarNecessity.getRating());
        results.put("place", mRatingBarPlace.getRating());
        results.put("tasks", mRatingBarTasks.getRating());
        results.put("trainer", mRatingBarTrainer.getRating());
        results.put("comments", mAddtionalNotes.getText().toString());
        results.put("topics", mMoreTopics.getText().toString());
        return results;
    }
}
