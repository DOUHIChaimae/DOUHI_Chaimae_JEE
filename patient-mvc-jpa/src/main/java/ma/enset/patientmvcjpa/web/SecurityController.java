package ma.enset.patientmvcjpa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/notAuthorized")
    public String notAuthorizerd(){
        return "notAuthorized";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
