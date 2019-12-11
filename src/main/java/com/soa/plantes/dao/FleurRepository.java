package com.soa.plantes.dao;

import com.soa.plantes.models.Fleur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleurRepository extends JpaRepository<Fleur,Long> {
}
