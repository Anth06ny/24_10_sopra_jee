package org.example.messagerie2.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Entity
@Table(name = "Message")
data class MessageBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var message: String = "",
    var pseudo: String = ""
)

@Repository                       //<Bean, Typage Id>
interface MessageRepository : JpaRepository<MessageBean, Long> {
    fun findTop10ByOrderByIdDesc() : List<MessageBean>
}

@Service
class MessageService(val messageRep:MessageRepository) {

    //@Transactional si je souhaite le faire dans une transaction
    fun createMessage(messageBean: MessageBean) {
        if(messageBean.message.isBlank()){
            throw Exception("Message missing")
        }
        else if(messageBean.pseudo.isBlank()){
            throw Exception("Pseudo missing")
        }
        messageRep.save(messageBean)
    }


    fun findById(id:Long) = messageRep.findByIdOrNull(id)


    fun get10Last() = messageRep.findTop10ByOrderByIdDesc()

    fun getAll() = messageRep.findAll()

    fun deleteAll() = messageRep.deleteAll()
}