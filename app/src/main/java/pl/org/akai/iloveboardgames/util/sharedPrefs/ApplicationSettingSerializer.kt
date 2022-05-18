package pl.org.akai.iloveboardgames.util.sharedPrefs

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object ApplicationSettingSerializer : Serializer<ApplicationSettings> {
    override val defaultValue: ApplicationSettings
        get() = ApplicationSettings()

    override suspend fun readFrom(input: InputStream): ApplicationSettings {
        return try {
            Json.decodeFromString(
                deserializer = ApplicationSettings.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: ApplicationSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = ApplicationSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}