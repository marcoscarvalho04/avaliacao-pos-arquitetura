package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.exception.ContactNotFoundException;
import br.com.contact.model.Contact;
import br.com.contact.repository.ContactRepository;
import br.com.contact.service.ContactService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Getter
@Setter
public class ContratoServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactResponse createContact(ContactRequest contact) {
        Contact contactConverted = Contact.convertRequestToEntity(contact);
        if (contactConverted == null ) {
            return null;
        }
        var contactReturn = this.contactRepository.save(contactConverted);
        if (contactReturn == null) {
            return null;
        }
        var contactResponse = Contact.convertEntityToResponse(contactReturn);

        return contactResponse;
    }

    @Override
    public void removeContact(Long id) throws ContactNotFoundException {
        try {
            this.contactRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ContactNotFoundException();
        }


    }

    @Override
    public List<ContactResponse> getAllContact() {
        return this.contactRepository.findAll().stream().map(contact -> Contact.convertEntityToResponse(contact)).toList();
    }

    @Override
    public List<ContactResponse> getByName(String name) {
        return this.contactRepository.findByName(name).stream().map(contact -> Contact.convertEntityToResponse(contact)).toList();
    }

    public ContactResponse getById(Long id){
        Contact contact;
        ContactResponse response;
        try {
            contact = this.contactRepository.findById(id).get();
            response = Contact.convertEntityToResponse(contact);
        }catch (NoSuchElementException e) {
            return null;
        }
        return response;
    }

    public ContactResponse saveContact(ContactRequest contact, Long id) throws ContactNotFoundException {

        if (contact == null ) {
            throw new ContactNotFoundException();
        }
        var  contactResponse = Contact.convertRequestToEntity(contact);
        contactResponse.setId(id);
        try {
            contactResponse =  this.contactRepository.findById(contactResponse.getId()).get();
        }catch(NoSuchElementException e) {
            throw new ContactNotFoundException();
        }

        return Contact.convertEntityToResponse(this.contactRepository.save(contactResponse));
    }


}
