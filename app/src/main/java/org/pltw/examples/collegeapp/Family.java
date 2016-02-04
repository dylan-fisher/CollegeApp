package org.pltw.examples.collegeapp;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by PLTW on 12/15/2015.
 */
public class Family {
    private static String TAG = "Family";
    private static Family sFamily;

    Arraylist<FamilyMember> mFamily;



    private Family(){
        mFamily = new Arraylist<FamilyMember>();
        mFamily.add(new Guardian());
        mFamily.add(new Guardian("John", "Nueberry"));
        mFamily.add(new Sibling("Derek", "Carr"));
    }
    public static Family get(){
        if (sFamily == null){
            Exception e;
            Log.e(TAG, "Error getting Family");
            sFamily = new Family();
            Log.d(TAG, "create new Family");
        }
        return sFamily;
    }
    public ArrayList<FamilyMember> getFamily() {return mFamily;}

    public void setFamily(String e) { mFamily = new Arraylist<FamilyMember>();}




    public void addFamilyMember(FamilyMember familyMember){
        if (familyMember instanceof Guardian){
            mFamily.add(new Guardian());
        }
        else if (familyMember instanceof Sibling) {
            mFamily.add(new Sibling());
        }
    }
    public void deleteFamilyMember(FamilyMember familyMember){}


    private class Arraylist<T> extends ArrayList<FamilyMember> {
    }
}
