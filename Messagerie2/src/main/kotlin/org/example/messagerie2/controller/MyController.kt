package org.example.messagerie2.controller

import jakarta.servlet.http.HttpSession
import org.example.messagerie2.StudentBean
import org.example.messagerie2.UserBean
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
class MyController {



    //http://localhost:8080/login
    @GetMapping("/login") //Affiche le formulaire
    fun login(userBean: UserBean, httpSession: HttpSession): String {
        println("/login ${httpSession.id}")

        return "login" //Lance login.html
    }

    @PostMapping("/loginSubmit") //traite le formulaire
    fun loginSubmit(userBean: UserBean, redirectAttributes: RedirectAttributes) : String {
        println("/loginSubmit : userBean=$userBean")

        try {
            if(userBean.password.isBlank()){
                throw Exception("Il faut un password")
            }

            redirectAttributes.addFlashAttribute("userBean", userBean)
            //Traitement du formulaire
            //Afin d'éviter la duplication de code, redirige sur la page qui s'occupe de l'affichage
            return "redirect:userRegister"
        }
        catch(e:Exception) {
            e.printStackTrace()

            redirectAttributes.addFlashAttribute("userBean", userBean)
            redirectAttributes.addFlashAttribute("errorMessage", e.message)
            return "redirect:login"
        }

    }

    @GetMapping("/userRegister") //Affiche la page résultat
    fun userRegister(userBean: UserBean): String {
        println("/userRegister userBean=$userBean")
        return "userRegister" //Lance userRegister.html
    }

    //http://localhost:8080/hello
    @GetMapping("/hello")
    fun testHello(model: Model): String {
        println("/hello")

        model.addAttribute("text", "Bonjour ")
        model.addAttribute("student", StudentBean("Toto", 25))
        model.addAttribute(
            "students", arrayOf(
                StudentBean("Toto", 25),
                StudentBean("Tata", 14),
                StudentBean("Bob", 22)
            )
        )

        //Nom du fichier HTML que l'on souhaite afficher
        return "welcome"
    }
}