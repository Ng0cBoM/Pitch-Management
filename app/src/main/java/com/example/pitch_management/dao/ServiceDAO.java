package com.example.pitch_management.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pitch_management.model.ServiceBall;

import java.util.List;

@Dao
public interface ServiceDAO {

    @Query("SELECT * FROM SERVICE")
    List<ServiceBall> getAll();

    @Insert
    void insert(ServiceBall serviceBall);

    @Delete
    void delete(ServiceBall serviceBall);

    @Update
    void update(ServiceBall serviceBall);

    @Query("SELECT * FROM SERVICE WHERE id = :id")
    List<ServiceBall> getServiceWithId(int id);

}
