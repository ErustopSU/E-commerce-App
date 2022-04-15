package com.hisu.hisumal.entity;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class IntegerTypeConverter {
    @TypeConverter
    public String IntToJson(List<Integer> integers) {
        return new Gson().toJson(integers);
    }

    @TypeConverter
    public List<Integer> fromJson(String json) {
        Type type = new TypeToken<List<Integer>>() {}.getType();
        return new Gson().fromJson(json, type);
    }
}