package pl.michalgorny.voteme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vote_button:
                vote();
                break;
            case R.id.results_button:
                showResults();
                break;
            case R.id.logout_button:
                logoutUser();
                break;
        }
    }

    private void vote() {
        switchActivity(VoteActivity.class);
    }

    private void showResults() {
        switchActivity(VotingResultActivity.class);
    }

    private void logoutUser() {
        ParseUser.logOut();
        switchActivity(LaunchActivity.class);
        finish();
    }

    private void switchActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

}
