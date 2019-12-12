package com.soa.plantes.controllers;

import com.soa.plantes.dao.ClientRepository;
import com.soa.plantes.dao.CommandeRepository;
import com.soa.plantes.models.Client;
import com.soa.plantes.models.Commande;
import com.soa.plantes.views.I_Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.nio.charset.CoderMalfunctionError;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Commande")
public class CommandeController implements I_Commande {

    @Autowired
    private CommandeRepository commande;
   @Autowired
   private ClientRepository client;


    @GetMapping("/commandeByid/{idc}")
    public Commande findcommande(@PathVariable Long idc)
    {
        return commande.findById(idc).get();
    }

    @PostMapping("/newCommande")
    public ResponseEntity<Void> ajouterCommande(@RequestBody Commande commande) {

        Commande commandeAdded =  this.commande.save(commande);

        if (commandeAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commandeAdded.getId_cf())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/CommandeByDate/{date}")
    public List<Commande> getClientByName(@PathVariable Date date) {

        List <Commande> l1  = new ArrayList();
        List <Commande> l2  = new ArrayList();

        l1 = this.commande.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getDate().equals(date)) l2.add(l1.get(i));
        }
        return l2;

    }
    @PutMapping("/updateCommande/{id}")
    public ResponseEntity<Commande> modifierClient(@Valid @RequestBody Commande commande , @PathVariable("id") Long id) {
        {
            Optional<Commande> commandeOptional = this.commande.findById(id);


            if (!commandeOptional.isPresent())
                return ResponseEntity.notFound().build();

            Commande commande1 = commandeOptional.get();
            commande1.setClient(commande.getClient());
            commande1.setDate(commande.getDate());
            commande1.setQuantite(commande.getQuantite());
            commande1.setValide(commande.getValide());

            Commande result = this.commande.save(commande1);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removeCommande/{id}")
    public ResponseEntity<?> deleteCommande(@PathVariable("id") Long id) {

        commande.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
