package org.example.messagerie2.restcontroller

import org.example.messagerie2.StudentBean
import org.example.messagerie2.model.TeacherBean
import org.example.messagerie2.model.TeacherService
import org.springframework.web.bind.annotation.*

@RestController
class MyRestController(val teacherService: TeacherService) {

    //http://localhost:8080/createTeacher?name=bob&code=5
    @GetMapping("/createTeacher")
    fun createTeacher(name: String = "", code: Int): MutableList<TeacherBean> {
        //name contiendra bob
        //note contiendra 12
        println("/createTeacher : name=$name code=$code")

        teacherService.createTeacher(name, code)

        return teacherService.getAll()
    }

    //http://localhost:8080/receiveStudent
//Json Attendu : {"name": "toto","note": 12}
    @PostMapping("/receiveStudent")
    fun receiveStudent(@RequestBody student: StudentBean) {
        println("/receiveStudent : $student")

        //traitement, mettre en base…
        //Retourner d'autres données
    }

    //http://localhost:8080/max2?p1=3&p2=6
    @GetMapping("/max2")
    fun max(p1:String?, p2:String?): Int? {
        //name contiendra bob
        //note contiendra 12
        println("/max : p1=$p1 p2=$p2")

        val p1Int = p1?.toIntOrNull()
        val p2Int = p2?.toIntOrNull()

        if(p1Int == null){
            return p2Int
        }
        else if(p2Int == null){
            return p1Int
        }

        return Math.max(p1Int,p2Int)
    }

    //http://localhost:8080/max?p1=3&p2=6
    @GetMapping("/max")
    fun max(p1:Int?, p2:Int?): Int? {
        //name contiendra bob
        //note contiendra 12
        println("/max : p1=$p1 p2=$p2")

        if(p1 == null){
            return p2
        }
        else if(p2 == null){
            return p1
        }

        return Math.max(p1,p2)
    }

    //http://localhost:8080/createStudent?name=bob&notation=12
    @GetMapping("/createStudent")
    fun createStudent(name: String = "",
                      @RequestParam(value = "notation", defaultValue = "0") note: Int): StudentBean {
        //name contiendra bob
        //note contiendra 12
        println("/createStudent : name=$name note=$note")

        return StudentBean(name, note)
    }


    //http://localhost:8080/test
    @GetMapping("/test")
    fun testMethode(): String {
        println("/test")

        return "helloWorld"
    }


}