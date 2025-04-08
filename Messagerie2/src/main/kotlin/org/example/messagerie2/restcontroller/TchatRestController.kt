package org.example.messagerie2.restcontroller

import org.example.messagerie2.model.MessageBean
import org.example.messagerie2.model.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tchat")
class TchatRestController(val messageService: MessageService) {

    //http://localhost:8080/tchat/saveMessage
    @PostMapping("/saveMessage")
    fun saveMessage(@RequestBody message: MessageBean) {
        println("/saveMessage : $message")
        messageService.createMessage(message)
    }

    //http://localhost:8080/tchat/allMessages
    @GetMapping("/allMessages")
    fun allMessages() = messageService.get10Last()
}