package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PLTW on 1/12/2016.
 */
public class Sibling extends FamilyMember implements ApplicantData {
    private static final String JSON_SIBLING_FIRST_NAME = "FirstName";
    private static final String JSON_SIBLING_LAST_NAME = "LastName";

    public Sibling(String firstName, String lastName){
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setRelation(SIBLING);
    }

    public Sibling(JSONObject json) throws JSONException{
        mFirstName = json.getString(JSON_FIRST_NAME);
        mLastName = json.getString(JSON_LAST_NAME);
    }

    public Sibling() {
        super();
        setFirstName("Morgan");
        setLastName("Freeman");
        setRelation(SIBLING);
    }

    public JSONObject toJSON() throws  JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);
        json.put(JSON_SIBLING_FIRST_NAME, mFirstName);
        json.put(JSON_SIBLING_LAST_NAME, mLastName);
        return json;
    }

    public String getFirstName() { return mFirstName;}
    public void setFirstName(String FirstName){ mFirstName = FirstName;}

    @Override
    public int compareTo(FamilyMember another) {
        return 0;
    }
}
