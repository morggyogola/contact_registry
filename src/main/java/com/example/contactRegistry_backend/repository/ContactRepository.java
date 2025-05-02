package com.example.contactRegistry_backend.repository;

import com.example.contactRegistry_backend.entity.Contact;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository {
    Optional<Contact> findByPhoneNumber(String phoneNumber);

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByIdNumber(String idNumber);
}
