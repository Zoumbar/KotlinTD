package hamy.yona.td2

import java.io.Serializable

data class Task (val id: String, val title: String, var description: String = "default" ) : Serializable