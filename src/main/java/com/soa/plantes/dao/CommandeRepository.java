package com.soa.plantes.dao;

import com.soa.plantes.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {
    public Collection<List> allCommandes(@PathVariable Long id_client);

}
