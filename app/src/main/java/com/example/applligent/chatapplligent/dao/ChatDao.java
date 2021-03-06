package com.example.applligent.chatapplligent.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.applligent.chatapplligent.model.Chat;

import java.util.List;
@Dao
public interface ChatDao {
    @Query("select * from Chat")
    List<Chat> getAll();

    @Query("delete  from Chat ")
    void deleteAll();
    @Insert
    void insertAll(List<Chat> chats );

    @Insert
    void insert(Chat chat );

    @Update
    void update(Chat chat );

    @Delete
    void delete( Chat chat);
}
