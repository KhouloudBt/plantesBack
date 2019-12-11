package com.soa.plantes.controllers;


import com.soa.plantes.dao.AdresseRepository;
import com.soa.plantes.models.Adresse;
import com.soa.plantes.views.I_Adresse;
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
@RequestMapping("/Adresse")

public class AdresseController implements I_Adresse {

    @Autowired
    private AdresseRepository adresse;

    @GetMapping("/allAdresses")
    public Collection<Adresse> allAdresses()
    {return adresse.findAll();
    }


    @GetMapping("/adresseByid/{id}")
    public Adresse findadresse(@PathVariable Long id)
    {
        return adresse.findById(id).get();
    }

    @PostMapping("/newAdresse")
    public ResponseEntity<Void> ajouterAdresse(@RequestBody Adresse adresse) {

        Adresse AdresseAdded =  this.adresse.save(adresse);

        if (AdresseAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(AdresseAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/updateAdresse/{id}")
    public ResponseEntity<Adresse> modifierAdmin(@Valid @RequestBody Adresse adresse , @PathVariable("id") Long id) {
        {
            Optional<Adresse> AdresseOptional = this.adresse.findById(id);


            if (!AdresseOptional.isPresent())
                return ResponseEntity.notFound().build();

            Adresse adresse1 = AdresseOptional.get();
            adresse1.setCity(adresse.getCity());
            adresse1.setStreetNumber(adresse.getStreetNumber());
            adresse1.setPostalCode(adresse.getPostalCode());

            Adresse result = this.adresse.save(adresse1);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removeAdresse/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {

        adresse.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allCityes")
    public List<Adresse> allCityes() {

        List <Adresse> l1  = new ArrayList();
        List <Adresse> l2  = new ArrayList();

        l1 = this.adresse.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getCity().toUpperCase().contains("CITYES")) l2.add(l1.get(i));
        }
        return l2;

    }
}
