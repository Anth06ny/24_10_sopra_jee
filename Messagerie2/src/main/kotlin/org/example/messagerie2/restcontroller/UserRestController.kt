package org.example.messagerie2.restcontroller

import org.example.messagerie2.UserBean
import org.example.messagerie2.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserRestController {

    @PostMapping
    fun create(@RequestBody bean: UserBean): ResponseEntity<UserBean> {

        UserService.save(bean)

        return ResponseEntity(bean, HttpStatus.CREATED)
    }

    @GetMapping
    fun read(): ArrayList<UserBean> {
        return UserService.load()
    }

    @GetMapping("/{id}")
    fun read(@PathVariable id : Long): ResponseEntity<UserBean> {
        val user =  UserService.findById(id)
        return if(user != null){
            ResponseEntity(user, HttpStatus.OK)
        }
        else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping ("/{id}")
    fun put(@PathVariable id : Long, @RequestBody newUser:UserBean): ResponseEntity<UserBean> {

        if(UserService.findById(id) == null) {
            return ResponseEntity.notFound().build()
        }

        newUser.id = id
        UserService.save(newUser)
        return ResponseEntity(newUser, HttpStatus.OK )
    }

    @DeleteMapping    ("/{id}")
    fun delete(@PathVariable id : Long): ResponseEntity<UserBean> {

        if(UserService.findById(id) == null) {
            return ResponseEntity.notFound().build()
        }

        UserService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}