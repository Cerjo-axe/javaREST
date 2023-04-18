package com.sergioluiz.simpleAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergioluiz.simpleAPI.Models.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}
