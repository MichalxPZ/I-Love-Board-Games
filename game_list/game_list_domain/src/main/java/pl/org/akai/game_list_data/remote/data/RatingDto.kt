package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rating")
data class RatingDto(
    @param:Element
    @get:Element(name = "average")
    val average: String? = null,

    @param:Element
    @get:Element(name = "ranks")
    val ranks: RanksDto? = null,
)
