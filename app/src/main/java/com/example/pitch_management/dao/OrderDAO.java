package com.example.pitch_management.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pitch_management.model.Order;

import java.util.List;

@Dao
public interface OrderDAO {

    @Query("SELECT * FROM ORDERS ORDER BY id DESC")
    List<Order> getAll();

    @Insert
    void insert(Order order);

    @Delete
    void delete(Order order);

    @Update
    void update(Order order);

//    @Query("SELECT * FROM ORDERS WHERE pitchId = :pitchId AND datePlay = :date")
//    List<Order> getOrderWithPitchAndDate(int pitchId,String date);

    @Query("SELECT MAX(id) FROM ORDERS")
    int getIdMax();

    @Query("SELECT * FROM ORDERS WHERE ID = :id")
    List<Order> getOrderWithID(int id);

//    @Query("DELETE FROM ORDERS")
//    void deleteAll();

//    @Query("SELECT PITCH.name,COUNT(ORDERS.pitchId) FROM PITCH " +
//            "INNER JOIN ORDERS ON PITCH.id = ORDERS.pitchId " +
//            "WHERE ORDERS.dateCreate LIKE :date GROUP BY ORDERS.pitchId")
//    Cursor getPopularPitch(String date);
    
    @Query("SELECT * FROM ORDERS WHERE customerId == :customerId ORDER BY id DESC")
    List<Order> getOrderWithCustomerId(int customerId);

    @Query("SELECT * FROM ORDERS WHERE customerId == :id AND status =:status ORDER BY id DESC")
    List<Order> getOrderWithCustomerIdAndStatus(int id, int status);


}
