package com.yt.shop.service;

import com.yt.shop.dao.PersonDao;
import com.yt.shop.dao.PersonJpa;
import com.yt.shop.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("demoService")
public class DemoService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonJpa personJpa;

    @Transactional
    public Person insertPerson(Person person) {
        return personJpa.save(person);
    }
}
