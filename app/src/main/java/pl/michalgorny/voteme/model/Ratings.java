package pl.michalgorny.voteme.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("ratings")
public class Ratings extends ParseObject{

    public double getFeelings() {
        return getDouble("feelings");
    }

    public void setFeelings(double feelings) {
        put("feelings", feelings);
    }
}
