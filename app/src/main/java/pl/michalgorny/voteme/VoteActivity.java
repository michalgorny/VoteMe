package pl.michalgorny.voteme;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;


public class VoteActivity extends ActionBarActivity {

    @InjectViews({R.id.ratingBarDetails, R.id.ratingBarFeelings, R.id.ratingBarNecessity, R.id.ratingBarPlace, R.id.ratingBarTasks, R.id.ratingBarTrainer})
    List<RatingBar> mRatingsBars;

    static final ButterKnife.Action<RatingBar> DEFAULT_RATE = new ButterKnife.Action<RatingBar>() {
        @Override
        public void apply(RatingBar view, int index) {
            view.setRating(5.0f);
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
    }

}
