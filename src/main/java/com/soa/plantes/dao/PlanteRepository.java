package com.soa.plantes.dao;

import com.soa.plantes.models.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanteRepository extends JpaRepository<Plante,Long> {
}
