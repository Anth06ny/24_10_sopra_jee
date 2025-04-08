package org.example.messagerie2

import org.example.messagerie2.model.MessageBean
import org.example.messagerie2.model.MessageRepository
import org.example.messagerie2.model.MessageService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Messagerie2Application(val messageRepository: MessageRepository) : CommandLineRunner{

    override fun run(vararg args: String?) {
        if(messageRepository.count() == 0L) {
            repeat(15){
                messageRepository.save(MessageBean(null, "Hello$it", "Pseudo$it"))
            }
        }
    }

}

fun main(args: Array<String>) {
    runApplication<Messagerie2Application>(*args)
}
