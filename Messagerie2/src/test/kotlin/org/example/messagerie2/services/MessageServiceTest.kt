package org.example.messagerie2.services

import org.example.messagerie2.model.MessageBean
import org.example.messagerie2.model.MessageService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertTrue

@SpringBootTest
class MessageServiceTest(@Autowired val messageService: MessageService) {

    init {
        messageService.deleteAll()
    }

    @Test
    fun testAddMessage(){
        // Préparation des données
        val messageBean = MessageBean(pseudo = "Alice", message = "Hello World")

        // Exécution de la méthode à tester
        messageService.createMessage(messageBean)

        assertNotNull(messageBean.id, "Id non ajouté");
        assertTrue(messageBean.id!! > 0 , "L'id n'a pas été modifié");

        // Vérification que messageRepository.save() a été appelé avec le bon argument
        val inDatabase = messageService.findById(messageBean.id ?:0);

        assertNotNull(inDatabase, "Message non retrouvé en base");
        assertEquals(messageBean, inDatabase, "Les attributs sont différents");
        assertNotSame(messageBean, inDatabase, "C'est la même instance de message");
    }

    @Test
    fun testGet10Last() {
        // Préparation des données
        for (i in 1..15) {
            val message = MessageBean(pseudo = "User$i", message = "Message $i")
            messageService.createMessage(message)
        }

        // Exécution de la méthode à tester
        val messages = messageService.get10Last()

        // Vérifications
        Assertions.assertEquals(10, messages.size)
        Assertions.assertEquals("Message 15", messages[0].message)
    }
}