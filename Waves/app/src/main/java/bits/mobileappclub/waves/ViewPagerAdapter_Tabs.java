package bits.mobileappclub.waves;

/**
 * Created by Aronzxxx on 03-10-2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ViewPagerAdapter_Tabs extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter_Tabs is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter_Tabs is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter_Tabs(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Tab1 tab1 = new Tab1();
            return tab1;
        }

        if(position == 1)
        {
            Tab2 tab2 = new Tab2();
            return tab2;
        }

        if(position == 2)
        {
            Tab3 tab3 = new Tab3();
            return tab3;
        }


        else             // As we are having 4 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Tab4 tab4 = new Tab4();
            return tab4;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
