package com.soa.plantes.controllers;
import com.soa.plantes.dao.ClientRepository;
import com.soa.plantes.dao.CommandeRepository;
import com.soa.plantes.models.Client;
import com.soa.plantes.views.I_Client;
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
@RequestMapping("/Client")

public class ClientController implements I_Client {

    @Autowired
    private ClientRepository client;

    @Autowired
    private CommandeRepository commande;
    @GetMapping("/allVisitors")
    public Collection<Client> allVisitors()
    {return client.findAll();
    }


    @GetMapping("/clientByid/{id}")
    public Client findclient(@PathVariable Long id)
    {
        return client.findById(id).get();
    }

    @PostMapping("/newClient")
    public ResponseEntity<Void> ajouterClient(@RequestBody Client client) {

        Client clientAdded =  this.client.save(client);

        if (clientAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/ClientByName/{name}")
    public List<Client> getClientByName(@PathVariable String name) {

        List <Client> l1  = new ArrayList();
        List <Client> l2  = new ArrayList();

        l1 = this.client.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getNom().toUpperCase().equals(name.toUpperCase()) ||l1.get(i).getPrénom().toUpperCase().equals(name.toUpperCase()) ) l2.add(l1.get(i));
        }
        return l2;
    }
    @PutMapping("/updateClient/{id}")
    public ResponseEntity<Client> modifierClient(@Valid @RequestBody Client client , @PathVariable("id") Long id) {
        {
            Optional<Client> clientOptional = this.client.findById(id);


            if (!clientOptional.isPresent())
                return ResponseEntity.notFound().build();

            Client client1 = clientOptional.get();
            client1.setNom(client.getNom());
            client1.setAdresse(client.getAdresse());
            client1.setCoeffFidelite(client.getCoeffFidelite());
            client1.setLogin(client.getLogin());
            client1.setCommandes(client.getCommandes());
            client1.setMail(client.getMail());
            client1.setPassword(client.getPassword());
            client1.setRemise(client.getRemise());


            Client result = this.client.save(client1);

            return ResponseEntity.ok().body(result);		}
    }


    @DeleteMapping("/removeClient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {

        client.deleteById(id);
        return ResponseEntity.ok().build();
    }

  @GetMapping("/allClients")
   public List<Client> allClients() {

    List <Client> l1  = new ArrayList();
    List <Client> l2  = new ArrayList();

    l1 = this.client.findAll();
    for(int i=0; i< l1.size(); i++)
    {
        if (l1.get(i).getType().equals(Boolean.TRUE)) l2.add(l1.get(i));
    }
    return l2;

}

    @GetMapping("/allFideles")
    public List<Client> allFideles() {

        List <Client> l1  = new ArrayList();
        List <Client> l2  = new ArrayList();

        l1 = this.client.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getCoeffFidelite()>2 ) l2.add(l1.get(i));
        }
        return l2;

    }

    @GetMapping("/allClientsinCity/{city}")
    public List<Client> allFideles( @PathVariable String city) {

        List <Client> l1  = new ArrayList();
        List <Client> l2  = new ArrayList();

        l1 = this.client.findAll();
        for(int i=0; i< l1.size(); i++)
        {
            if (l1.get(i).getAdresse().getCity().toUpperCase().contains(city.toUpperCase())) l2.add(l1.get(i));
        }
        return l2;

    }


}
