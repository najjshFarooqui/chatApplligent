package com.example.applligent.chatapplligent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    private TextView userNAme ;
    private TextView userStatus;
    private CircleImageView circleImageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userNAme=(TextView)findViewById(R.id.userName);
        userStatus=(TextView)findViewById(R.id.userStatus);
        circleImageView=(CircleImageView) findViewById(R.id.userImage);

        user= FirebaseAuth.getInstance().getCurrentUser();
        String currentUid=user.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("users").child(currentUid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name= dataSnapshot.child("name").getValue().toString();
                String status =dataSnapshot.child("status").getValue().toString();
                String image= dataSnapshot.child("image").getValue().toString();
                userNAme.setText(name);
                userStatus.setText(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
