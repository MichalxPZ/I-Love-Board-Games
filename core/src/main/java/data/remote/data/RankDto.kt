package data.remote.data

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList

@ElementList(name = "ranks", required = false)
data class RankDto(
        @param:Attribute(name = "type", required = false)
        @get:Attribute(name = "type", required = false)
        val pos: String? = "",

        @param:Attribute(name = "id", required = false)
        @get:Attribute(name = "id", required = false)
        val id: String? = "",

        @param:Attribute(name = "name", required = false)
        @get:Attribute(name = "name", required = false)
        val category: String? = "",

        @param:Attribute(name = "friendlyname", required = false)
        @get:Attribute(name = "friendlyname", required = false)
        val friendlyname: String? = "",

        @param:Attribute(name = "bayesaverage", required = false)
        @get:Attribute(name = "bayesaverage", required = false)
        val bayesaverage: String? = "",

        @param:Attribute(name = "value", required = false)
        @get:Attribute(name = "value", required = false)
        val value: String? = "Not Ranked"
)