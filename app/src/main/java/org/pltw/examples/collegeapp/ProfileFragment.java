package org.pltw.examples.collegeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by wdumas on 12/23/2014.
 */
public class ProfileFragment extends Fragment{

    private static final String TAG = "ProfileFragment";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DOB = 0;
    private static final String KEY_FIRST_NAME = "firstname";
    private final String FILENAME = "profile.json";

    private Profile mProfile;
    private TextView mFirstName;
    private EditText mEnterFirstName;
    private TextView mGPA;
    private EditText mEnterGPA;
    private TextView mLastName;
    private EditText mEnterLastName;
    private Button mDoBButton;
    private Context mAppContext;
    ProfileJSONStorer mStorer;



    public ProfileFragment(){
        // tries to load previous version
        try {
            mProfile = mStorer.load();//loads saved version
        } catch (Exception e) {//catches errors
            mProfile = new Profile();//creates new profile
            Log.e(TAG, "Error loading profile: " + FILENAME, e);//logs error message
        }
    }
    private void updateDoB() {
        mDoBButton.setText(mProfile.dobToString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DOB) {
            Date dob = (Date)data
                    .getSerializableExtra(DoBPickerFragment.EXTRA_DOB);
            mProfile.setmDateOfBirth(dob);
            updateDoB();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProfile = new Profile();

        if (savedInstanceState != null) {
            mProfile.setFirstName(savedInstanceState.getString(KEY_FIRST_NAME));
            Log.i(TAG, "The name is " + mProfile.getFirstName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        getActivity().setTitle(R.string.profile_title);

        mFirstName = (TextView)rootView.findViewById(R.id.first_name);
        mEnterFirstName = (EditText)rootView.findViewById(R.id.enter_first_name);
        mLastName = (TextView)rootView.findViewById(R.id.last_name);
        mEnterLastName = (EditText)rootView.findViewById(R.id.enter_last_name);
        mDoBButton = (Button)rootView.findViewById(R.id.dob_button);
        mGPA = (TextView)rootView.findViewById(R.id.gpa);
        mEnterGPA = (EditText)rootView.findViewById(R.id.enter_gpa);

        mFirstName.setText(mProfile.getFirstName());
        mLastName.setText(mProfile.getLastName());
        mGPA.setText(mProfile.getGPA());

        FirstNameTextChanger firstNameTextChanger = new FirstNameTextChanger();
        LastNameTextChanger lastNameTextChanger = new LastNameTextChanger();
        //GPATextChanger gpaTextChanger = new GPATextChanger();


        DoBButtonOnClickListener doBButtonOnClickListener = new DoBButtonOnClickListener();

        updateDoB();
        mDoBButton.setOnClickListener(doBButtonOnClickListener);

        mEnterFirstName.addTextChangedListener(firstNameTextChanger);


        //mGPA.addTextChangedListener(gpaTextChanger);

        mEnterLastName.addTextChangedListener(lastNameTextChanger);



        mAppContext = this.getActivity();
        Log.d(TAG, "Context: " + mAppContext);
        mStorer = new ProfileJSONStorer(mAppContext, FILENAME);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState got called and it was a BLAST!!!1: " + mProfile.getFirstName());
        savedInstanceState.putString(KEY_FIRST_NAME, mProfile.getFirstName());
    }

    private class FirstNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setFirstName(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mFirstName.setText(mProfile.getFirstName());
        }
    }

    private class LastNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setLastName(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mLastName.setText(mProfile.getLastName());
        }
    }

    /*private class GPATextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setGPA(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mGPA.setText(mProfile.getGPA());
        }
    }*/

    private class DoBButtonOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            FragmentManager fm = getActivity()
                    .getSupportFragmentManager();
            DoBPickerFragment dialog = DoBPickerFragment
                    .newInstance(mProfile.getDateOfBirth());
            dialog.setTargetFragment(ProfileFragment.this, REQUEST_DOB);
            dialog.show(fm, DIALOG_DATE);
        }
    }
    public boolean saveProfile(){
        try{
            mStorer.save(mProfile);
            Log.d(TAG, "profile saved to file.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving profile: ", e);
            return false;
        }
    }
    public void loadProfile(){
        try {
            mProfile=mStorer.load();
            Log.d(TAG, "Loaded " + mProfile.getFirstName());
            mFirstName.setText(mProfile.getFirstName());
            mLastName.setText(mProfile.getLastName());
            mGPA.setText(mProfile.getGPA());
            updateDoB();
        } catch (Exception e) {
            mProfile = new Profile();
            Log.e(TAG, "Error loading profile from: " + FILENAME, e);
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
        saveProfile();
        Log.d(TAG, "Fragment paused.");
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            mProfile=mStorer.load();
            Log.d(TAG, "Loaded " + mProfile.getFirstName());
            mFirstName.setText(mProfile.getFirstName());
            mLastName.setText(mProfile.getLastName());
            //mGPA.setText(mProfile.getGPA());
            updateDoB();
        } catch (Exception e) {
            mProfile = new Profile();
            Log.e(TAG, "Error loading profile from: " + FILENAME, e);
        }
        Log.d(TAG, "Fragment resumed.");
    }





    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Fragment stoped.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment destroyed.");
    }

}

