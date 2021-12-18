package br.com.contact.repository;


import br.com.contact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    public List<Contact> findByName(String name);

}
