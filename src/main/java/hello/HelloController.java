package hello;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @CrossOrigin
    @RequestMapping("/")
    public String index() {
        /*HttpServletResponse response = new HttpServletResponse() {
        }*/
        //komc
        System.out.println("tutaj " + new Date());
        return "{\"name\": \"Greetings from Spring Boot!\", \"data\": \"" + new Date() + "\"}";
    }

}