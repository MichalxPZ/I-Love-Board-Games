package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@ElementList(name = "ranks", required = false)
data class RanksDto(
        @param:Attribute(name = "type", required = false)
        @get:Attribute(name = "type", required = false)
        val pos: String = ""
)