package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wdumas on 2/11/2015.
 */
public class Guardian extends FamilyMember {
    private String mOccupation;
    private static final String JSON_OCCUPATION = "occupation";
    private static final String JSON_GUARDIAN_FIRST_NAME = "FirstName";
    private static final String JSON_GUARDIAN_LAST_NAME = "LastName";


    public int compareTo(FamilyMember familyMember) {
        if(this.mFirstName.equals(familyMember.getFirstName()) && this.mLastName.equals(familyMember.getLastName())){
            return 0;
        }
        return 1;
    }

    public Guardian() {
        super();
        setFirstName("Rodger");
        setLastName("Dumas");
        setOccupation("unknown");
        setRelation(GUARDIAN);
    }

    public Guardian(String firstName, String lastName){
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setOccupation("unknown");
        setRelation(GUARDIAN);
    }

    public Guardian(String firstName, String lastName, String occupation) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setOccupation(occupation);
        setRelation(GUARDIAN);
    }

    public String getOccupation() {
        return mOccupation;
    }

    public void setOccupation(String occupation) {
        mOccupation = occupation;
    }

    public Guardian(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_FIRST_NAME);
        mLastName = json.getString(JSON_LAST_NAME);
        mOccupation = json.getString(JSON_OCCUPATION);

    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_GUARDIAN_FIRST_NAME, mFirstName);
        json.put(JSON_GUARDIAN_LAST_NAME, mLastName);

        json.put(JSON_OCCUPATION, mOccupation);

        return json;
    }

}

