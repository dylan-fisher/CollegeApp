package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PLTW on 12/14/2015.
 */
public abstract class FamilyMember implements ApplicantData, Comparable<FamilyMember>  {

    public static final String EXTRA_RELATION = "org.pltw.examples.collegeapp.relation";
    public static final String EXTRA_INDEX = "org.pltw.examples.collegeapp.index";
    protected String mFirstName;
    protected String mLastName;
    protected static final String JSON_FIRST_NAME = "firstname";
    protected static final String JSON_LAST_NAME  = "lastname";
    public static final int GUARDIAN = 0;
    public static final int SIBLING = 1;
    private int mRelation;


    public FamilyMember(){
        super();
        setFirstName(JSON_FIRST_NAME);
        setLastName(JSON_LAST_NAME);
    }
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

public FamilyMember (JSONObject json) throws JSONException{
    mFirstName = json.getString(JSON_FIRST_NAME);
    mLastName = json.getString(JSON_LAST_NAME);

}
    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();

        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);


        return json;

    }

    public int compareTo(FamilyMember familyMember){
        if (this.mFirstName.equals(familyMember.mFirstName)
                && this.mLastName.equals(familyMember.mLastName)) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public int getRelation() {
        return mRelation;
    }

    public void setRelation(int relation) {
        mRelation = relation;
    }
}
