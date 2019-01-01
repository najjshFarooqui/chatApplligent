package com.example.applligent.chatapplligent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                RequesFragment requesFragment = new RequesFragment();
                return  requesFragment;
            case 1:
                ChatsFragment chatsFragment = new ChatsFragment();
                return  chatsFragment;
            case 2:
                Friends friends = new Friends();
                return  friends;
             default: return  null;


        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return"REQUETS";
            case 1:
                return "CHATS";
            case 3:
                return "FRIENDS";
                default:
                    return null;
        }

    }
}
