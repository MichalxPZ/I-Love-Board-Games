package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.Element

data class StatusDto(
    @param:Element
    @get:Element(name = "rating")
    val rating: RatingDto? = null,
)
