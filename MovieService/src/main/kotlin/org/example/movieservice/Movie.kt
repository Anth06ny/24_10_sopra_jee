package org.example.movieservice

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Entity
@Table(name = "Movie")
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String = "",
    var length: Int = 0
)

@Repository                       //<Bean, Typage Id>
interface MovieRepository : JpaRepository<Movie, Long> {
}