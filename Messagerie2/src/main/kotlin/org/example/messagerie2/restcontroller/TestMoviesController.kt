package org.example.messagerie2.restcontroller

import org.example.messagerie2.MovieBean
import org.example.messagerie2.MovieResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/mymovies")
class TestMoviesController(
    @Qualifier("basic")
    val restTemplate: RestTemplate,
    @Qualifier("loadbalancing")
    val restTemplateLB: RestTemplate
) {

    //Accès classique par lien direct à un autre MicroService
    //http://localhost:8080/mymovies/directAccess
    @GetMapping("/directAccess")
    fun directAccess() : List<MovieBean>? {
        println("/directAccess")

        val movies = restTemplate.getForObject("http://localhost:8081/movies", MovieResponse::class.java)
        println(movies)

        return movies?._embedded?.movies
    }

    //Accès utilisant le lien de LoadBalancing
    //http://localhost:8080/mymovies/directAccessLB
    @GetMapping("/directAccessLB")
    fun directAccessLB() : List<MovieBean>? {
        println("/directAccessLB")

        val movies = restTemplateLB.getForObject("http://MovieService/movies", MovieResponse::class.java)
        println(movies)

        return movies?._embedded?.movies
    }
}