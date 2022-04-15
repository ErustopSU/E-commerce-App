package com.hisu.hisumal.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hisu.hisumal.entity.Product;
import com.hisu.hisumal.entity.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("select * from users where userID = :userID")
    User getUser(int userID);

    @Insert
    long addUser(User user);

    @Update
    void updateUserInfo(User user);

    @Query("select * from products")
    List<Product> getAllProducts();

    @Insert
    long addProduct(Product product);
}