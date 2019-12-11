package com.soa.plantes.controllers;
import com.soa.plantes.dao.AdminRepository;
import com.soa.plantes.models.Admin;
import com.soa.plantes.models.Produit;
import com.soa.plantes.views.I_Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Admin")

public class AdminController implements I_Admin {


    @Autowired
    private AdminRepository admin;

    @GetMapping("/allAdmins")
    public Collection<Admin> allAdmins()
    {return admin.findAll();
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
            admin1.setLogin(id);
            admin1.setPassword(admin.getPassword());

            Admin result = this.admin.save(admin1);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removeadmin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {

        admin.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
