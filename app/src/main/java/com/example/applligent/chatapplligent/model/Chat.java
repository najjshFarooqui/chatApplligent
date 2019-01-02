package com.example.applligent.chatapplligent.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Chat {
    @PrimaryKey(autoGenerate = true)
    public int sNo;
    public String userName;
    public String time;
    public String message;

    public Chat(){

    }
}
