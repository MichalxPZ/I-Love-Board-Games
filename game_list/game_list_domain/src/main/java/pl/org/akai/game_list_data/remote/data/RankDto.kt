package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "rank")
class RankDto(
    @param:Attribute(name = "type")
    val type: String = "subtype",

    @param:Attribute(name = "value")
    @get:Attribute(name="value")
    val rankPos: Int,

    @param:Attribute(name = "name")
    val name: String = "boardgame"
)
