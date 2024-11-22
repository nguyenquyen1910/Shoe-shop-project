package com.btl.snaker.repository;

import com.btl.snaker.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByIsReadFalse();
}
