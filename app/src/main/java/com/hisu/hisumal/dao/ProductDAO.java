package com.hisu.hisumal.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hisu.hisumal.entity.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("select * from products")
    List<Product> getAllProducts();

    @Insert
    void addProduct(Product... product);

    @Update
    void update(Product... product);
}