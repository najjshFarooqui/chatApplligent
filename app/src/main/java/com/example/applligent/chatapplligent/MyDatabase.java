package com.example.applligent.chatapplligent;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.applligent.chatapplligent.dao.ChatDao;
import com.example.applligent.chatapplligent.model.Chat;
@Database(entities = {Chat.class,} , version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ChatDao chatDao();
}
