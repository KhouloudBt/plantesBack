package com.soa.plantes.controllers;


import com.soa.plantes.dao.UtilisationRepository;
import com.soa.plantes.models.Utilisation;
import com.soa.plantes.views.I_Utilisation;
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
@CrossOrigin(origins = "*")
@RequestMapping("/Utilisation")

public class UtilisationController implements I_Utilisation {

    @Autowired
    private UtilisationRepository utilisation;

    @GetMapping("/allUtilisations")
    public Collection<Utilisation> allUtilisations()
    {return utilisation.findAll();
    }


    @GetMapping("/utilisationtByid/{id}")
    public Utilisation findutilisation(@PathVariable Long id)
    {
        return utilisation.findById(id).get();
    }

    @PostMapping("/newUtilisation")
    public ResponseEntity<Void> ajouterUtilisation(@RequestBody Utilisation utilisation ) {
        Utilisation  utilisationAdded =  this.utilisation.save(utilisation);
        if (utilisationAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(utilisationAdded.getCode())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/UtilisationByTitre/{name}")
    public List<Utilisation> getUtilisationtByName(@PathVariable String name) {

        List <Utilisation> l1  = new ArrayList();
        List <Utilisation> l2  = new ArrayList();

        l1 = this.utilisation.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getTitre().equals(name)) l2.add(l1.get(i));
        }
        return l2;

    }
    @PutMapping("/updateUtilisation/{id}")
    public ResponseEntity<Utilisation> modifierutilisation(@Valid @RequestBody Utilisation utilisation , @PathVariable("id") Long id) {
        {
            Optional<Utilisation> UtilisationOptional = this.utilisation.findById(id);


            if (!UtilisationOptional.isPresent())
                return ResponseEntity.notFound().build();

            Utilisation utilisation1 = UtilisationOptional.get();
            utilisation1.setTitre(utilisation.getTitre());
            utilisation1.setGuide(utilisation.getGuide());
            Utilisation result = this.utilisation.save(utilisation1);
            return ResponseEntity.ok().body(result);}
    }


    @DeleteMapping("/removeUtilisation/{id}")
    public ResponseEntity<?> deleteUtilisation(@PathVariable("id") Long id) {

        utilisation.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
