package com.hisu.hisumal.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hisu.hisumal.dao.ProductDAO;
import com.hisu.hisumal.entity.Product;
import com.hisu.hisumal.util.ImageTypeConverter;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1)
@TypeConverters(ImageTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "HMal_DB";
    private static AppDatabase INSTANCE;
    public abstract ProductDAO productDAO();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
        return INSTANCE;
    }

    public static void closeConnection() {
        if (INSTANCE != null) INSTANCE.close();
    }
}