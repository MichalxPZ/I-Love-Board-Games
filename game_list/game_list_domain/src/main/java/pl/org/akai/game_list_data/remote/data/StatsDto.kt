package pl.org.akai.game_list_data.remote.data

import org.simpleframework.xml.*

@Root(name= "stats")
data class StatsDto (

    @param:ElementList(name = "ranks", required = false, inline = true)
    @get:ElementList(name = "ranks", required = false, inline = true)
    @field:Path("ratings/ranks")
    @param:Path("ratings/ranks")
    val stats: RanksDto = RanksDto()

)