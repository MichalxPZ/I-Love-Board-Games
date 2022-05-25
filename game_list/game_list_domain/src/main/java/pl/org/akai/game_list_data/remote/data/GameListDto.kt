package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "items", strict = false)
data class GameListDto(
    @param:ElementList(inline = true, required = false)
    @get:ElementList(name = "items", required = false)
    val list: List<GameItemDto>? = null,

    @field:Attribute(name = "totalitems", required = false)
    @param:Attribute(name = "totalitems", required = false)
    val id: String? = null,


    @field:Attribute(name = "termsofuse", required = false)
    @param:Attribute(name = "termsofuse", required = false)
    val termsOfUse: String? = null,

    @field:Attribute(name = "pubdate", required = false)
    @param:Attribute(name = "pubdate", required = false)
    val pubdate: String? = null,
) {
    @Root(name = "item", strict = false)
    data class Item(

        @param:Attribute(name = "objectid")
        @get:Attribute(name = "objectid")
        val id: String? = null

    )
}