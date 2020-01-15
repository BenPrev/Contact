
package Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collection;

@Controller
public class WebController{
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    MailRepository  mailRepository;

    @GetMapping("/")
    public String index(){
        return ("index");
    }

    @ModelAttribute("contacts")
    public Iterable<Contact> afficherContacts(){
        return contactRepository.findAll();
    }

    @ModelAttribute("mails")
    public Iterable<Mail> afficherMailsContact(){
        return mailRepository.findAll();
    }

}