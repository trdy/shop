package com.yt.shop.dao;

import com.yt.shop.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpa extends JpaRepository<Person,Long>{
}
