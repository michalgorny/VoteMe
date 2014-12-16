package pl.michalgorny.voteme;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RatingsFragment extends Fragment {
    private static final String ARG_VALUES = "values";
    private HashMap<String, Float> valuesMap;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RatingsFragment.
     */
    public static RatingsFragment newInstance(HashMap<String, Float> ratings) {
        RatingsFragment fragment = new RatingsFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_VALUES, ratings);

        fragment.setArguments(args);
        return fragment;
    }

    public RatingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            valuesMap = (HashMap) getArguments().getSerializable(ARG_VALUES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ratings, container, false);

        LinearLayout ratingsContainer = (LinearLayout) view.findViewById(R.id.ratingBarsContainer);

        for (String title : valuesMap.keySet()) {
            TextView header = new TextView(getActivity());
            header.setTextAppearance(getActivity(), R.style.FormText);
            header.setText(title);
            header.setGravity(Gravity.CENTER_HORIZONTAL);

            RatingBar bar = new RatingBar(getActivity());
            bar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            bar.setRating(valuesMap.get(title));
            bar.setIsIndicator(true);
            bar.setStepSize(0.5f);
            bar.setNumStars(5);

            ratingsContainer.addView(header);
            ratingsContainer.addView(bar);
        }

        return view;
    }





}
