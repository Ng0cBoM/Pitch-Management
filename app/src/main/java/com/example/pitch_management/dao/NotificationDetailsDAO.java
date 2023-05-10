package com.example.pitch_management.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pitch_management.model.NotificationDetails;

import java.util.List;

@Dao
public interface NotificationDetailsDAO {
    @Query("SELECT * FROM NOTIFICATIONDETAILS")
    List<NotificationDetails> getAll();

    @Insert
    void insert(NotificationDetails details);

    @Update
    void update(NotificationDetails details);

    @Delete
    void delete(NotificationDetails details);
}
