package com.hisu.hisumal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hisu.hisumal.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Hisumal_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String PRODUCT_TABLE = "Products";
    private static final String PRODUCT_SPEC_TABLE = "Specifications";
    private static final String CUSTOMER_TABLE = "Customers";
    private static final String ORDER_TABLE = "Orders";

    public MyDatabaseHelper(@Nullable @org.jetbrains.annotations.Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        return products;
    }

    private boolean addProduct(ContentValues product) {
        return true;
    }
}