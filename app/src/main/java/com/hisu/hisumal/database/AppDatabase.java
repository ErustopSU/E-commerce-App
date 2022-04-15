package com.hisu.hisumal.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.hisu.hisumal.dao.UserDAO;
import com.hisu.hisumal.util.ImageTypeConverter;
import com.hisu.hisumal.entity.Product;
import com.hisu.hisumal.entity.User;

@Database(entities = {User.class, Product.class}, version = 1)
@TypeConverters(ImageTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "HMal_DB";
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    DB_NAME).allowMainThreadQueries().build();
        return INSTANCE;
    }

    public static void closeConnection() {
        if (INSTANCE != null) INSTANCE.close();
    }

    public abstract UserDAO userDAO();
}