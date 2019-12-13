package com.soa.plantes.controllers;
import com.soa.plantes.dao.AdminRepository;
import com.soa.plantes.models.Admin;
import com.soa.plantes.models.Client;
import com.soa.plantes.views.I_Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Admin")

public class AdminController implements I_Admin {
    @Autowired
    private AdminRepository admin;

    @GetMapping("/allAdmins")
    public List<Admin> allAdmins()
    {return admin.findAll();
    }

    @GetMapping("/AdminByLogin/{login}")
    public Admin findAdmin(@PathVariable String login)
    {
        Admin ad=new Admin();
        int i=0;
        Boolean found = false;
        List<Admin> admins = this.allAdmins();
        while( i < admins.size() && found == false)
        {
            if (admins.get(i).getLogin().equals(login))
            { ad=admins.get(i);
                found =true ;}
            else i++;
        }
         return ad;
    }



    @PostMapping("/newAdmin")
    public ResponseEntity<Void> ajouterAdmin(@RequestBody Admin admin) {

        Admin AdminAdded =  this.admin.save(admin);

        if (AdminAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(AdminAdded.getLogin())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<Admin> modifierClient(@Valid @RequestBody Admin admin , @PathVariable("id") Long id) {
        {
            Optional<Admin> adminOptional = this.admin.findById(id);
            if (!adminOptional.isPresent())
                return ResponseEntity.notFound().build();

            Admin admin1 = adminOptional.get();
            admin1.setPassword(admin.getPassword());
            admin1.setLogin(admin.getLogin());

            Admin result = this.admin.save(admin1);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removeadmin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {

        admin.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
