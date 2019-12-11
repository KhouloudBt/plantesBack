package com.soa.plantes.dao;

import com.soa.plantes.models.Utilisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisationRepository extends JpaRepository<Utilisation,Long> {
}
