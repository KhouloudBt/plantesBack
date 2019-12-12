package com.soa.plantes.controllers;

import com.soa.plantes.dao.ProduitRepository;
import com.soa.plantes.models.Produit;
import com.soa.plantes.views.I_Produit;
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
@RequestMapping("/Produit")

public class ProduitController implements I_Produit {

        @Autowired
    public ProduitRepository produit;

    @GetMapping("/allProducts")
    public Collection<Produit> allProducts()
    {return produit.findAll();
    }


    @GetMapping("/produitByid/{id}")
    public Produit findproduit(@PathVariable Long id)
    {
        return produit.findById(id).get();
    }

    @PostMapping("/newProduit")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Produit produit) {

        Produit produitAdded =  this.produit.save(produit);

        if (produitAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produitAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/ProduitByName/{name}")
    public List<Produit> getProduitByName(@PathVariable String name) {

        List <Produit> l1  = new ArrayList();
        List <Produit> l2  = new ArrayList();

        l1 = this.produit.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getNom().equals(name)) l2.add(l1.get(i));
        }
        return l2;

    }
    @PutMapping("/updateProduit/{id}")
    public ResponseEntity<Produit> modifierProduit(@Valid @RequestBody Produit produit , @PathVariable("id") Long id) {
        {
            Optional<Produit> produitOptional = this.produit.findById(id);


            if (!produitOptional.isPresent())
                return ResponseEntity.notFound().build();

            Produit produit1 = produitOptional.get();
            produit1.setNom(produit.getNom());
            produit1.setPrix(produit.getPrix());
            produit1.setStock(produit.getStock());

            Produit result = this.produit.save(produit1);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removeProduit/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable("id") Long id) {

        produit.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
