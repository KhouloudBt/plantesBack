package com.soa.plantes.dao;

import com.soa.plantes.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByLogin(String login );
}
