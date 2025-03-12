package com.example.CinemaCity.Repositories;

import com.example.CinemaCity.Entities.Role;
import com.example.CinemaCity.Entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRoleType(RoleType roleType);
}
