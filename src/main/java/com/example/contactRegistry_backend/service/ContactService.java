package com.example.contactRegistry_backend.service;

import com.example.contactRegistry_backend.dto.ContactDto;
import com.example.contactRegistry_backend.entity.Contact;
import com.example.contactRegistry_backend.exception.ContactCreationException;
import com.example.contactRegistry_backend.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public Contact createContact(ContactDto contact) {
        contactRepository.findByEmail(contact.getEmail()).ifPresent(c -> {
            log.error("Contact with email {} already exists", contact.getEmail());
            throw new ContactCreationException("Contact with email " + contact.getEmail() + " already exists");
        });
        contactRepository.findByPhoneNumber(contact.getPhoneNumber()).ifPresent(c -> {
            log.error("Contact with phone number {} already exists", contact.getPhoneNumber());
            throw new ContactCreationException("Contact with phone number " + contact.getPhoneNumber() + " already exists");
        });
        contactRepository.findByIdNumber(contact.getIdNumber()).ifPresent(c -> {
            log.error("Contact with ID number {} already exists", contact.getIdNumber());
            throw new ContactCreationException("Contact with ID number " + contact.getIdNumber() + " already exists");
        });

        Contact newContact = new Contact();
        BeanUtils.copyProperties(contact, newContact);
        Contact saved = contactRepository.save(newContact);
        if (saved != null) {
            log.info("Contact created successfully {}", contact);
            return newContact;
        } else {
            log.error("Failed to create contact {}", contact);
            throw new ContactCreationException("Failed to create contact");
        }

    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


}
