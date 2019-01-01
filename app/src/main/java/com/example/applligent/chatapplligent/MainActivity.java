package com.example.applligent.chatapplligent;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private Toolbar mtoolbar;
    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager)findViewById(R.id.mainPager) ;
        sectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout=(TabLayout)findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(MainActivity.this,StartActivity.class));
            finish();
            mtoolbar=(Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(mtoolbar);
            getSupportActionBar().setTitle("chats");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,LoginORRegisterActivity.class));
        }
        if(item.getItemId()==R.id.settings){
            startActivity(new Intent(MainActivity.this,SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
