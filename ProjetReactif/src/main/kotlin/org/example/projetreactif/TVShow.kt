package org.example.projetreactif
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Table(name = "TVShow")
data class TVShow(
    @Id
    var id: Long? = null,
    var name: String? = null,
    var genre: String? = null,
    var seasons: Int? = null
)

@Repository                       //<Bean, Typage Id>
interface TVShowRepository : ReactiveCrudRepository<TVShow, Long> {
}

@Service
class TVShowService(val tvShowRepository: TVShowRepository) {

    fun getAllTVShows(): Flux<TVShow> = tvShowRepository.findAll()

}