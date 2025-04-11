package org.example.movieservice

import org.springframework.data.rest.core.annotation.*
import org.springframework.stereotype.Component

@Component
@RepositoryEventHandler(Movie::class)
class MovieEventHandler {

    // Intercepte les événements avant la création d'une nouvelle entité
    @HandleBeforeCreate
    fun handleBeforeCreate(movie: Movie) {
        println("Before creating entity: $movie")
    }

    // Intercepte les événements après la création d'une nouvelle entité
    @HandleAfterCreate
    fun handleAfterCreate(movie: Movie) {
        println("After creating entity: $movie")
    }

    // Intercepte les événements avant la mise à jour d'une entité existante
    @HandleBeforeSave
    fun handleBeforeSave(movie: Movie) {
        println("Before saving entity: $movie")
    }

    // Intercepte les événements après la mise à jour d'une entité existante
    @HandleAfterSave
    fun handleAfterSave(movie: Movie) {
        println("After saving entity: $movie")
    }

    // Intercepte les événements avant la suppression d'une entité
    @HandleBeforeDelete
    fun handleBeforeDelete(movie: Movie) {
        println("Before deleting entity: $movie")
    }

    // Intercepte les événements après la suppression d'une entité
    @HandleAfterDelete
    fun handleAfterDelete(movie: Movie) {
        println("After deleting entity: $movie")
    }
}