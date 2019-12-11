package com.soa.plantes.controllers;

import com.soa.plantes.dao.FleurRepository;
import com.soa.plantes.models.Fleur;
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
@RequestMapping("/Fleur")
public class FleurController {




    @Autowired
    public FleurRepository fleur;

    @GetMapping("/allProducts")
    public Collection<Fleur> allFleurs()
    {return fleur.findAll();
    }


    @GetMapping("/FleurByid/{id}")
    public Fleur findFleur(@PathVariable Long id)
    {
        return fleur.findById(id).get();
    }

    @PostMapping("/newFleur")
    public ResponseEntity<Void> ajouterFleur(@RequestBody Fleur fleur) {

        Fleur fleurAdded =  this.fleur.save(fleur);

        if (fleurAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(fleurAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/FleurByName/{name}")
    public List<Fleur> getFleurByName(@PathVariable String name) {

        List <Fleur> l1  = new ArrayList();
        List <Fleur> l2  = new ArrayList();

        l1 = this.fleur.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getNom().equals(name)) l2.add(l1.get(i));
        }
        return l2;

    }
    @PutMapping("/updateFleur/{id}")
    public ResponseEntity<Fleur> modifierFleur(@Valid @RequestBody Fleur fleur , @PathVariable("id") Long id) {
        {
            Optional<Fleur> fleurOptional = this.fleur.findById(id);


            if (!fleurOptional.isPresent())
                return ResponseEntity.notFound().build();

            Fleur fleur1 = fleurOptional.get();
            fleur1.setNom(fleur.getNom());
            fleur1.setPrix(fleur.getPrix());
            fleur1.setStock(fleur.getStock());
            fleur1.setOdeur(fleur.getOdeur());

            Fleur result = this.fleur.save(fleur1);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removeFleur/{id}")
    public ResponseEntity<?> deleteFleur(@PathVariable("id") Long id) {

        fleur.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
