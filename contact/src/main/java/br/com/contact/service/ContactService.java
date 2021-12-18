package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.exception.ContactNotFoundException;
import br.com.contact.model.Contact;

import java.util.List;

public interface ContactService {

    public ContactResponse createContact(ContactRequest contact);
    public void removeContact(Long id) throws ContactNotFoundException;
    public List<ContactResponse> getAllContact();
    public List<ContactResponse> getByName(String name);
    public ContactResponse getById(Long id);
    public ContactResponse saveContact(ContactRequest contact, Long id) throws ContactNotFoundException;

}
