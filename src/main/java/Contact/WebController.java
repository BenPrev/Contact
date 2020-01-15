
package Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class WebController{
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    MailRepository  mailRepository;

    @Autowired
    AdresseRepository adresseRepository;

    @GetMapping("/")
    public String index(){
        return ("index");
    }

    @PostMapping("/modifier")
    public String modifier(@RequestParam long id, Model model){
        model.addAttribute("contact", contactRepository.findById(id));
        return ("modifier");
    }

    @PostMapping("/finmodif")
    public String finmodif(@RequestParam(name="id") String ids, @RequestParam(name="lastname") String lastName, @RequestParam(name="firstname") String firstName, HttpServletRequest request, HttpServletRequest request2){
        long id=Long.parseLong(ids);
        Contact c=contactRepository.findById(id).get();
        c.setLastname(lastName);
        c.setFirstname(firstName);

        Collection<Adresse> listAdresses= new ArrayList<>();
        String[] adds=request.getParameterMap().get("contactAdresses");
        if (adds!=null) {
            for (String i : adds) {
                listAdresses.add(adresseRepository.findById(Long.parseLong(i)).get());
            }

        }
        c.setLieuHabitation(listAdresses);
        contactRepository.save(c);
        String [] mail = request2.getParameterMap().get("contactMail");

        if(mail!=null){
            for(String i: mail){
                Mail mail1 = mailRepository.findById(Long.parseLong(i)).get();
                mail1.setContact(c);
                mailRepository.save(mail1);
            }
        }else{
            for (Mail m : mailRepository.findByContact(c))
                mailRepository.delete(m);
        }
        return ("redirect:/");
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