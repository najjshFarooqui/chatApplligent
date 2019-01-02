package com.example.applligent.chatapplligent;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.applligent.chatapplligent.dao.ChatDao;
import com.example.applligent.chatapplligent.model.Chat;
import com.example.applligent.chatapplligent.service.TimePickerClass;
import com.example.applligent.chatapplligent.service.TimeSuckerClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  {
    FirebaseAuth mAuth;
    private Toolbar mtoolbar;
    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private TabLayout tabLayout;
    Button selectName;
    Chat chat;
    ChatDao chatDao;
    private DatabaseReference databaseReference;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatDao = MyNagoriApplication.getDatabase().chatDao();

        viewPager = (ViewPager) findViewById(R.id.mainPager);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Appligent chat");
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        mAuth = FirebaseAuth.getInstance();
        selectName = (Button) findViewById(R.id.selectName);
        selectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("fill details");
                View view = getLayoutInflater().inflate(R.layout.alert_dialog, null);
                final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinnerList));
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
                final EditText message = (EditText) view.findViewById(R.id.message);
                DialogFragment timePicker = new TimePickerClass();
                timePicker.show(getSupportFragmentManager(), "time picker");
                alertDialog.setPositiveButton("send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!spinner.getSelectedItem().toString().equalsIgnoreCase("Select Name")) {
                            Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                            dialog.dismiss();
                        } else {
                            chat = new Chat();
                            chat.userName = spinner.getSelectedItem().toString();
                            chat.message = message.getText().toString();
                            TimeSuckerClass timeSuckerClass = new TimeSuckerClass();
                            chat.time = Integer.toString(timeSuckerClass.x) + ":" + Integer.toString(timeSuckerClass.y);
                            chatDao.insert(chat);

                            user= FirebaseAuth.getInstance().getCurrentUser();
                            String currentUid=user.getUid();
                            databaseReference= databaseReference=FirebaseDatabase.getInstance().getReference().child("users").child(currentUid);





                            Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                alertDialog.setNegativeButton("cencel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialog.setView(view);
                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }


        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            redirectUser();
            finish();
            mtoolbar = findViewById(R.id.toolbar);
            setSupportActionBar(mtoolbar);
            getSupportActionBar().setTitle("chats");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            redirectUser();
        }
        if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return true;
    }

    public void redirectUser() {
        startActivity(new Intent(MainActivity.this, StartActivity.class));
    }

}