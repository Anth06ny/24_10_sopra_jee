package org.example.messagerie2


data class StudentBean(var name: String = "", var note: Int = 0)

data class UserBean(var id: Long? = 0, var login: String = "", var password: String = "")

data class MovieBean(
    var id: Long? = null,
    var title: String = "",
    var length: Int = 0
)

data class MovieResponse(
    val _embedded: EmbeddedMovies,
)

data class EmbeddedMovies(
    val movies: List<MovieBean>
)