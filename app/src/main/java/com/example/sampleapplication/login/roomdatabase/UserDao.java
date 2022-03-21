package com.example.sampleapplication.login.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.sampleapplication.login.model.LoginResults;
import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity userEntity);

    @Query("SELECT * FROM table_name")
    List<UserEntity> getValues();

    @Query("DELETE FROM table_name")
    void deleteAll();

    @Query("DELETE FROM table_name where userphno == :phonenumber")
    void deleteUserPhno(String phonenumber);
}

