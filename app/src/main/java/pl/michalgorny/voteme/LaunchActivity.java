package pl.michalgorny.voteme;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseRole;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;


public class LaunchActivity extends ActionBarActivity {

    public static final int PARSE_LOGIN_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ParseUser.getCurrentUser() == null){
            showLoginScreen();
        }else{
            startMainActivity();
        }

    }

    private void showLoginScreen() {
        ParseLoginBuilder builder = new ParseLoginBuilder(this);
        customizeLoginScreen(builder);
        startActivityForResult(builder.build(), PARSE_LOGIN_REQUEST_CODE);
    }

    private void customizeLoginScreen(ParseLoginBuilder builder) {
        builder.setAppLogo(R.drawable.mustache)
                .setTwitterLoginEnabled(true)
                .setFacebookLoginEnabled(true);

    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PARSE_LOGIN_REQUEST_CODE && resultCode == RESULT_OK){
            startMainActivity();
        }

        finish();
    }
}
