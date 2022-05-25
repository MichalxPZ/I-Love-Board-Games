package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element

@Element(name = "name", required = false)
data class PrimaryDto(

    @param:Attribute(name = "type", required = false)
    @get:Attribute(name = "type", required = false)
    val type: String? = null,

    @param:Attribute(name = "sortindex", required = false)
    @get:Attribute(name = "sortindex", required = false)
    val sortindex: String ? = null,

    @param:Attribute(name = "value", required = false)
    @get:Attribute(name = "value", required = false)
    var elementPrimaryName: String? = null
)
