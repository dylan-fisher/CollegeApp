package org.pltw.examples.collegeapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by PLTW on 1/21/2016.
 */
public class FamilyMemberActivity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        GuardianFragment fragment = null;// = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            if (getIntent().getIntExtra(FamilyMember.EXTRA_RELATION, 0) ==
                    FamilyMember.GUARDIAN) {
                fragment = new GuardianFragment();
                fm.beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
            }
            else if (getIntent().getIntExtra(FamilyMember.EXTRA_RELATION, 0) ==
                    FamilyMember.SIBLING) {
                fragment = new GuardianFragment();
                fm.beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
            }
        }
    }

}
