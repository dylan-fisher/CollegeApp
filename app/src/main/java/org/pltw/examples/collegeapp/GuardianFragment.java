package org.pltw.examples.collegeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wdumas on 2/18/2015.
 */
public class GuardianFragment extends Fragment {

    private static final String TAG = "GuardianFragment";
    private final String FILENAME = "Guardian.json";
    private static final String KEY_FIRST_NAME = "firstname";

    private Guardian mGuardian;
    private TextView mFirstName;
    private TextView mLastName;
    private EditText mEnterFirstName;
    private EditText mEnterLastName;
    private Context mAppContext;
    private TextView mGPA;
    private TextView mEnterGPA;


    GuardianJSONStorer mStorer;

    //@Override
    // public void onCreate(Bundle savedInstanceState) {
    //   mGuardian = new Guardian();
    // }
    public GuardianFragment() {
        // tries to load previous version
        try {
            mGuardian = mStorer.load();//loads saved version
        } catch (Exception e) {//catches errors
            mGuardian = new Guardian();//creates new guardian
            Log.e(TAG, "Error loading guardian: " + FILENAME, e);//logs error message
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuardian = new Guardian();

        if (savedInstanceState != null) {
            mGuardian.setFirstName(savedInstanceState.getString(KEY_FIRST_NAME));
            Log.i(TAG, "The name is " + mGuardian.getFirstName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_guardian, container, false);

        mGuardian = (Guardian) Family.get().getFamily().get(getActivity().
                getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, 0));


        mFirstName = (TextView) rootView.findViewById(R.id.first_name);
        mEnterFirstName = (EditText) rootView.findViewById(R.id.enter_first_name);
        mLastName = (TextView) rootView.findViewById(R.id.last_name);
        mEnterLastName = (EditText) rootView.findViewById(R.id.enter_last_name);
        mGPA = (TextView) rootView.findViewById(R.id.gpa);
        mEnterGPA = (EditText) rootView.findViewById(R.id.enter_gpa);

        mFirstName.setText(mGuardian.getFirstName());
        mLastName.setText(mGuardian.getLastName());


        FirstNameTextChanger firstNameTextChanger = new FirstNameTextChanger();
        LastNameTextChanger lastNameTextChanger = new LastNameTextChanger();

        mEnterFirstName.addTextChangedListener(firstNameTextChanger);

        mEnterLastName.addTextChangedListener(lastNameTextChanger);

        mAppContext = this.getActivity();
        Log.d(TAG, "Context: " + mAppContext);

        mStorer = new GuardianJSONStorer(mAppContext, FILENAME);

        return rootView;

    }

    private class FirstNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mGuardian.setFirstName(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, 0);
            family.set(index, (FamilyMember) mGuardian);
        }

        @Override
        public void afterTextChanged(Editable s) {
            mFirstName.setText(mGuardian.getFirstName());
        }

    }


    private class LastNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mGuardian.setLastName(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, 0);
            family.set(index, (FamilyMember) mGuardian);
        }


        @Override
        public void afterTextChanged(Editable s) {
            mLastName.setText(mGuardian.getLastName());
        }
    }


/*public boolean saveGuardian(){
        try{
            mStorer.save(mGuardian);
            Log.d(TAG, "Guardian saved to file.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving guardian: ", e);
            return false;
        }
    }*/
    public void loadGuardian(){
        try {
            mGuardian=mStorer.load();
            Log.d(TAG, "Loaded " + mGuardian.getFirstName());
            mFirstName.setText(mGuardian.getFirstName());
            mLastName.setText(mGuardian.getLastName());
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading guardian from: " + FILENAME, e);
        }
        Log.d(TAG, "Fragment resumed.");
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Fragment started.");
    }

    @Override
    public void onPause() {
        super.onPause();
        //saveGuardian();
        Log.d(TAG, "Fragment paused.");
    }

    /*@Override
    public void onResume() {
        super.onResume();
        try {
            mGuardian=mStorer.load();
            Log.d(TAG, "Loaded " + mGuardian.getFirstName());
            mFirstName.setText(mGuardian.getFirstName());
            mLastName.setText(mGuardian.getLastName());
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading guardian from: " + FILENAME, e);
        }
        Log.d(TAG, "Fragment resumed.");
    }*/





    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Fragment stopped.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment destroyed.");
    }

}
