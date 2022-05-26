package games_data.mappers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ListTypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringtoList(value: String): List<String> {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }


    @TypeConverter
    @JvmStatic
    fun listToString(value: List<String>?): String {
        return if (value == null) "" else Gson().toJson(value)
    }
}