package com.example.contactRegistry_backend.controller;

import com.example.contactRegistry_backend.dto.ContactDto;
import com.example.contactRegistry_backend.entity.Contact;
import com.example.contactRegistry_backend.exception.ContactCreationException;
import com.example.contactRegistry_backend.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createContact(@Valid @RequestBody ContactDto contact) {
        try {
            Contact newContact = contactService.createContact(contact);
            return new ResponseEntity<>(newContact, HttpStatus.CREATED);
        } catch (ContactCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllContacts")
    public ResponseEntity<?> getAllContacts() {
        try {
            return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving contacts");
        }
    }

    @GetMapping("/getContactById/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        try {
            Contact contact = contactService.getContactById(id);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (ContactCreationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/updateContact/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @Valid @RequestBody ContactDto contact) {
        try {
            Contact updatedContact = contactService.updateContact(id, contact);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } catch (ContactCreationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
