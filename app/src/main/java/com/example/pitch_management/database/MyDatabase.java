package com.example.pitch_management.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pitch_management.dao.CustomerDAO;
import com.example.pitch_management.dao.HistoryBuyDAO;
import com.example.pitch_management.dao.NotificationDAO;
import com.example.pitch_management.dao.NotificationDetailsDAO;
import com.example.pitch_management.dao.OrderDAO;
import com.example.pitch_management.dao.OrderDetailsDAO;
import com.example.pitch_management.dao.PitchCategoryDAO;
import com.example.pitch_management.dao.PitchDAO;
import com.example.pitch_management.dao.ServiceDAO;
import com.example.pitch_management.dao.TimeDAO;
import com.example.pitch_management.dao.TimeOrderDetailsDAO;
import com.example.pitch_management.model.Customer;
import com.example.pitch_management.model.HistoryBuy;
import com.example.pitch_management.model.MyNotification;
import com.example.pitch_management.model.MyTime;
import com.example.pitch_management.model.NotificationDetails;
import com.example.pitch_management.model.Order;
import com.example.pitch_management.model.OrderDetails;
import com.example.pitch_management.model.Pitch;
import com.example.pitch_management.model.PithCategory;
import com.example.pitch_management.model.ServiceBall;
import com.example.pitch_management.model.TimeOrderDetails;

@Database(entities = {Customer.class,Order.class
        , OrderDetails.class, Pitch.class, PithCategory.class
        , ServiceBall.class, MyNotification.class
        , MyTime.class, TimeOrderDetails.class, HistoryBuy.class
        , NotificationDetails.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static String DB_NAME = "PITCH_MANAGER";
    private static MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract CustomerDAO customerDAO();
    public abstract OrderDAO orderDAO();
    public abstract OrderDetailsDAO orderDetailsDAO();
    public abstract PitchCategoryDAO pitchCategoryDAO();
    public abstract PitchDAO pitchDao();
    public abstract ServiceDAO serviceDAO();
    public abstract TimeOrderDetailsDAO timeOrderDetailsDAO();
    public abstract TimeDAO timeDAO();
    public abstract HistoryBuyDAO historyBuyDAO();
    public abstract NotificationDetailsDAO notificationDetailsDAO();
    public abstract NotificationDAO notificationDAO();
}
