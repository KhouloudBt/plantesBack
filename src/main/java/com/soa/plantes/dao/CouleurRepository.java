package com.soa.plantes.dao;

import com.soa.plantes.models.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouleurRepository extends JpaRepository<Couleur,Long> {
}
