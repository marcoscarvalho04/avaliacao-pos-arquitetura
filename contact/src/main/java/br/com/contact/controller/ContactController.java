package br.com.contact.controller;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.service.ContactService;
import br.com.contact.exception.ContactNotFoundException;
import br.com.contact.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static br.com.contact.constants.ContactPaths.*;

@RestController
@RequestMapping(API_VERSION+PATH_CONTATO)
public class ContactController {


    @Autowired
    private ContactService contactService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> getContact() {
        return this.contactService.getAllContact();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse createContact(@RequestBody ContactRequest contactRequest) {
        ContactResponse  c = this.contactService.createContact(contactRequest);
        return c;
    }

    @DeleteMapping
    public ResponseEntity deleteContact(@RequestParam("id") Long id) {
        try {
            this.contactService.removeContact(id);
        }catch (ContactNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(FIND_BY_NAME)
    public ResponseEntity<List<ContactResponse>> getContactByName(@RequestParam String name){
        List<ContactResponse> contact;
        contact = this.contactService.getByName(name);
        return (contact == null|| contact.size() == 0 )  ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @GetMapping(FIND_BY_ID)
    public ResponseEntity<ContactResponse> getContactById(@RequestParam Long id) {
        ContactResponse contact;
        contact = this.contactService.getById(id);
        return contact == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ContactResponse> saveContact(@RequestBody ContactRequest contact, @RequestParam Long id){
        ContactResponse contactResponse;
        try {
           contactResponse = this.contactService.saveContact(contact, id);
        }catch (ContactNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contactResponse, HttpStatus.OK);
    }
}
