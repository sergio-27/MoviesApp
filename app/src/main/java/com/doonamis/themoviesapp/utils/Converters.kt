package com.doonamis.themoviesapp.utils

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type


object Converters {

        @TypeConverter
        fun fromString(value: String?): List<String> {
            val listType: Type = object : TypeToken<List<String?>?>() {}.getType()
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromArrayList(list: List<String?>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }


    @TypeConverter
    fun saveIntList(list: List<Int>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun getIntList(list: String): List<Int> {
        return Gson().fromJson(
            list,
            object : TypeToken<List<Int>>() {}.type
        )
    }
}