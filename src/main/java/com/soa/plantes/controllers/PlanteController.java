package com.soa.plantes.controllers;

import com.soa.plantes.dao.PlanteRepository;
import com.soa.plantes.models.Plante;
import com.soa.plantes.models.Produit;
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
@RequestMapping("/Plante")
public class PlanteController {


    @Autowired
    public PlanteRepository plante;

    @GetMapping("/allProducts")
    public Collection<Plante> allPlantes()
    {return plante.findAll();
    }


    @GetMapping("/PlanteByid/{id}")
    public Produit findPlante(@PathVariable Long id)
    {
        return plante.findById(id).get();
    }

    @PostMapping("/newPlante")
    public ResponseEntity<Void> ajouterPlante(@RequestBody Plante plante) {

        Plante PlanteAdded =  this.plante.save(plante);

        if (PlanteAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(PlanteAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/PlanteByName/{name}")
    public List<Plante> getPlanteByName(@PathVariable String name) {

        List <Plante> l1  = new ArrayList();
        List <Plante> l2  = new ArrayList();

        l1 = this.plante.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getNom().equals(name)) l2.add(l1.get(i));
        }
        return l2;

    }
    @PutMapping("/updatePlante/{id}")
    public ResponseEntity<Plante> modifierPlante(@Valid @RequestBody Plante plante , @PathVariable("id") Long id) {
        {
            Optional<Plante> planteOptional = this.plante.findById(id);


            if (!planteOptional.isPresent())
                return ResponseEntity.notFound().build();

            Plante plante1 = planteOptional.get();
            plante1.setNom(plante.getNom());
            plante1.setPrix(plante.getPrix());
            plante1.setStock(plante.getStock());
            plante1.setDescription(plante.getDescription());
            plante1.setOrigine(plante.getOrigine());
            Plante result = this.plante.save(plante1);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removePlante/{id}")
    public ResponseEntity<?> deletePlante(@PathVariable("id") Long id) {

        plante.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
