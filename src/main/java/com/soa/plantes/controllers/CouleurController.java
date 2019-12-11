package com.soa.plantes.controllers;


import com.soa.plantes.dao.CouleurRepository;
import com.soa.plantes.models.Couleur;
import com.soa.plantes.views.I_Couleur;
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
@RequestMapping("/Couleur")

public class CouleurController implements I_Couleur {

    @Autowired
    private CouleurRepository couleur;

    @GetMapping("/allCouleurs")
    public Collection<Couleur> allCouleurs()
    {return couleur.findAll();
    }


    @GetMapping("/couleurByid/{id}")
    public Couleur findcouleur(@PathVariable Long id)
    {
        return couleur.findById(id).get();
    }

    @PostMapping("/newClient")
    public ResponseEntity<Void> ajouterClient(@RequestBody Couleur couleur) {

        Couleur couleurAdded =  this.couleur.save(couleur);

        if (couleurAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(couleurAdded.getId_c())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/CouleurByName/{name}")
    public List<Couleur> getClientByName(@PathVariable String name) {

        List <Couleur> l1  = new ArrayList();
        List <Couleur> l2  = new ArrayList();

        l1 = this.couleur.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getnom().equals(name)) l2.add(l1.get(i));
        }
        return l2;

    }
    @PutMapping("/updateCouleur/{id}")
    public ResponseEntity<Couleur> modifierCouleur(@Valid @RequestBody Couleur couleur , @PathVariable("id") Long id) {
        {
            Optional<Couleur> couleurOptional = this.couleur.findById(id);


            if (!couleurOptional.isPresent())
                return ResponseEntity.notFound().build();

            Couleur couleur1 = couleurOptional.get();
            couleur1.setnom(couleur.getnom());

            Couleur result = this.couleur.save(couleur);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removeCouleur/{id}")
    public ResponseEntity<?> deleteCouleur(@PathVariable("id") Long id) {

        couleur.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
