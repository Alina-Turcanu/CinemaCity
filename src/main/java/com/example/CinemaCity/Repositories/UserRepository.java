package com.example.CinemaCity.Repositories;

import com.example.CinemaCity.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
