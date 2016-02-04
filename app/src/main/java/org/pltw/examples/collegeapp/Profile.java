package org.pltw.examples.collegeapp;



import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;


public class Profile implements ApplicantData{

    private String mFirstName;
    private String mLastName;
    private Date mDateOfBirth;
    private String mGPA;
    private static final String JSON_FIRST_NAME = "firstName";
    private static final String JSON_LAST_NAME  = "lastName";
    private static final String JSON_GPA  = "gpa";
    private static final String JSON_DOB = "dob";

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public Date getDateOfBirth() {
        return mDateOfBirth;
    }

    public String dobToString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(mDateOfBirth.getTime());
    }

    public void setmDateOfBirth(Date mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public String getGPA() {
        return mGPA;
    }

    public void setGPA(String mGPA) {
        this.mGPA = mGPA;
    }



    public Profile() {
        mFirstName = new String("Wyatt");
        mLastName = new String("Dumas");
        mDateOfBirth = new Date(83, 0, 24);
        mGPA= new String("0-4");
    }

    public String toString() {
        return mFirstName + " " + mLastName +" " + mDateOfBirth.getTime();
    }



    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);
        json.put(JSON_GPA, mGPA);
        json.put(JSON_DOB, mDateOfBirth.getTime());
        System.out.println("Date of Birth Saved: " + mDateOfBirth);
        return json;
    }
    public Profile(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_FIRST_NAME);
        mLastName = json.getString(JSON_LAST_NAME);
        mGPA = json.getString(JSON_GPA);
        mDateOfBirth = new Date(json.getLong(JSON_DOB));
    }
}

