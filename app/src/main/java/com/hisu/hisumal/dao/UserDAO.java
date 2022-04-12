package com.hisu.hisumal.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hisu.hisumal.entity.User;

@Dao
public interface UserDAO {
    @Query("select * from users where userID = :userID")
    User getUser(int userID);

    @Insert
    long addUser(User user);

    @Update
    void updateUserInfo(User user);
}