package com.example.online.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromIntList(value: List<Int>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIntList(value: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromIntSetMap(value: Map<Int, Set<Int>>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIntSetMap(value: String): Map<Int, Set<Int>> {
        val mapType = object : TypeToken<Map<Int, Set<Int>>>() {}.type
        return gson.fromJson(value, mapType)
    }
} 