package com.example.neozensoft.internproject.tab_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by neozensoft on 2017-10-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String code;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, String code) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.code = code;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle1=new Bundle();
        bundle1.putString("code", code);

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                tab1.setArguments(bundle1);
                return tab1;
//            case 1:
//                TabFragment1 tab1_ = new TabFragment1();
//                tab1_.setArguments(bundle1);
//                return tab1_;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                tab2.setArguments(bundle1);
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();
                tab3.setArguments(bundle1);
                return tab3;
            case 3:
                TabFragment4 tab4 = new TabFragment4();
                tab4.setArguments(bundle1);
                return tab4;
            case 4:
                TabFragment5 tab5 = new TabFragment5();
                tab5.setArguments(bundle1);
                return tab5;
            case 5:
                TabFragment6 tab6 = new TabFragment6();
                tab6.setArguments(bundle1);
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
