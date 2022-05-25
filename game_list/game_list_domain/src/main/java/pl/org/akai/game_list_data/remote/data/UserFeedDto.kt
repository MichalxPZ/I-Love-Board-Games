package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "items", strict = false)
data class UserFeedDto (
    @field:ElementList(name = "item", inline = true)
    @param:ElementList(name = "item", inline = true)
    var item: ArrayList<GameItemDto>? = null
)
