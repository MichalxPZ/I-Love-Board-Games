package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element

@Element(name = "yearpublished", required = false)
data class YearPublishedDto(

    @param:Attribute(name = "value", required = false)
    @get:Attribute(name = "value", required = false)
    val year: String? = null

)