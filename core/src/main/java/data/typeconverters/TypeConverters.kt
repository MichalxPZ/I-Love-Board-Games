package data.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

object MapTypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToMap(value: String): Map<String, Int> {
        return try {
            Gson().fromJson(value, object : TypeToken<Map<String, Int>>() {}.type)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            mapOf<String, Int>()
        }
    }


    @TypeConverter
    @JvmStatic
    fun mapToString(value: Map<String, Int>?): String {
        return if (value.isNullOrEmpty()) "" else Gson().toJson(value)
    }
}