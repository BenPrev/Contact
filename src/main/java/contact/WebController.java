
package contact;

import contact.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class WebController {
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    MailRepository mailRepository;

    @Autowired
    AdresseRepository adresseRepository;

    @GetMapping("/")
    public String index(){
        return ("index");
    }
    @GetMapping("/autre")
    public String autre(){
        return ("autre");
    }

    @GetMapping("/login")
    public String login(){
        return ("login");
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
    @PostMapping("/creerContact")
    public String creerContact(@RequestParam(name="lastname") String lastname, @RequestParam(name = "firstname") String firstname, @RequestParam(name = "NDeRue")int NDeRue, @RequestParam(name = "Rue" ) String rue,@RequestParam(name="Ville")String Ville, @RequestParam(name="CP") int CP,@RequestParam(name="adresseMail") String mail){
        Adresse adresse = new Adresse(rue,NDeRue,CP,Ville);
        adresseRepository.save(adresse);
        Collection<Adresse> adds = new ArrayList<Adresse>();
        adds.add(adresse);
        Contact contact = new Contact(firstname,lastname,adds);
        contactRepository.save(contact);
        Mail mail1 = new Mail(mail, contact);
        mailRepository.save(mail1);

        return ("index");
    }

    @PostMapping("/deleteContact")
    public String deleteContact(@RequestParam(name = "id") Long id){
        Contact c = contactRepository.findById(id).get();
        for (Mail m : mailRepository.findByContact(c))
            mailRepository.delete(m);
        contactRepository.delete(c);
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