package org.example.messagerie2.services

import org.example.messagerie2.model.MessageBean
import org.example.messagerie2.model.MessageRepository
import org.example.messagerie2.model.MessageService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest

@ExtendWith(MockitoExtension::class)
class MessageServiceTest {


    val messageRepository = Mockito.mock(MessageRepository::class.java)

    @InjectMocks
    private lateinit var messageService: MessageService

    @BeforeEach
    fun init(){
        messageService.deleteAll()
    }



    @Test
    fun testAddMessage(){
        // Préparation des données
        val messageBean = MessageBean(pseudo = "Alice", message = "Hello World")

        // Exécution de la méthode à tester
        messageService.createMessage(messageBean)

        Mockito.verify(messageRepository, times(1)).save(messageBean);

        //assertNotNull(messageBean.id, "Id non ajouté");
        //assertTrue(messageBean.id!! > 0 , "L'id n'a pas été modifié");

        // Vérification que messageRepository.save() a été appelé avec le bon argument
        val inDatabase = messageService.findById(messageBean.id ?:0)

        //assertNotNull(inDatabase, "Message non retrouvé en base");
        //assertEquals(messageBean, inDatabase, "Les attributs sont différents");
        //assertNotSame(messageBean, inDatabase, "C'est la même instance de message");
    }

    @Test
    fun testGet10Last() {
        val returnList = ArrayList<MessageBean>()
        // Préparation des données
        for (i in 1..15) {
            val message = MessageBean(pseudo = "User$i", message = "Message $i")
            messageService.createMessage(message)
            returnList.add(0, message)
        }

        Mockito.`when`(messageRepository.findTop10ByOrderByIdDesc())
            .thenReturn(returnList.take(10))


        // Exécution de la méthode à tester
        val messages = messageService.get10Last()

        Mockito.verify(messageRepository, times(1)).findTop10ByOrderByIdDesc();

        // Vérifications
        Assertions.assertEquals(10, messages.size)
        Assertions.assertEquals("Message 15", messages[0].message)
    }
}