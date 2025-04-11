package org.example.movieservice

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieServiceApplication(val movieRepository: MovieRepository) : CommandLineRunner {

	override fun run(vararg args: String?) {
		if (movieRepository.count() == 0L) {
			val movies = listOf(
				Movie(title = "Inception", length = 148),
				Movie(title = "The Matrix", length = 136),
				Movie(title = "Interstellar", length = 169),
				Movie(title = "The Godfather", length = 175),
				Movie(title = "Pulp Fiction", length = 154)
			)
			movieRepository.saveAll(movies)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<MovieServiceApplication>(*args)
}
