package com.btl.snaker.service;

import com.btl.snaker.dto.ContactDTO;
import com.btl.snaker.dto.PageContactDTO;
import com.btl.snaker.entity.Contact;
import com.btl.snaker.payload.ResponseData;
import com.btl.snaker.payload.request.ContactRequest;
import com.btl.snaker.repository.ContactRepository;
import com.btl.snaker.service.imp.ContactServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContactService implements ContactServiceImp {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ResponseData getAllContacts(int page) {
        ResponseData responseData = new ResponseData();
        Pageable pageable = PageRequest.of(page, 10);
        Page<Contact> contactPages = contactRepository.findAll(pageable);
        List<ContactDTO> contactDTOs = new ArrayList<>();
        for(Contact contact : contactPages.getContent()) {
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setId(contact.getId());
            contactDTO.setFullName(contact.getFullName());
            contactDTO.setEmail(contact.getEmail());
            contactDTO.setPhoneNumber(contact.getPhone());
            contactDTO.setContent(contact.getContent());
            contactDTO.setStatus(contact.isRead() ? "Đã đọc" : "Chưa đọc");
            contactDTOs.add(contactDTO);
        }
        PageContactDTO pageContactDTO = new PageContactDTO(contactDTOs, contactPages.getNumber(), contactPages.getTotalElements());
        responseData.setSuccess(true);
        responseData.setData(pageContactDTO);
        return responseData;
    }

    @Override
    public ResponseData getContactById(long id) {
        ResponseData responseData = new ResponseData();
        Contact contact = contactRepository.findById(id).orElse(null);
        if(contact == null) {
            responseData.setSuccess(false);
            responseData.setDescription("Contact not found");
            return responseData;
        }
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setFullName(contact.getFullName());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setPhoneNumber(contact.getPhone());
        contactDTO.setContent(contact.getContent());
        contactDTO.setStatus(contact.isRead() ? "Đã đọc" : "Chưa đọc");
        responseData.setSuccess(true);
        responseData.setData(contactDTO);
        return responseData;
    }

    @Override
    public ResponseData createContact(ContactRequest contactRequest) {
        ResponseData responseData = new ResponseData();
        Contact contact = new Contact();
        contact.setFullName(contactRequest.getFullName());
        contact.setEmail(contactRequest.getEmail());
        contact.setPhone(contactRequest.getPhone());
        contact.setContent(contactRequest.getContent());
        contact.setRead(false);
        contact.setCreatedAt(new Date());
        contactRepository.save(contact);
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setFullName(contact.getFullName());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setPhoneNumber(contact.getPhone());
        contactDTO.setContent(contact.getContent());
        contactDTO.setStatus(contact.isRead() ? "Đã đọc" : "Chưa đọc");
        responseData.setSuccess(true);
        responseData.setData(contactDTO);
        return responseData;
    }

    @Override
    public ResponseData solveRead(long contactId) {
        ResponseData responseData = new ResponseData();
        Contact contact = contactRepository.findById(contactId).orElse(null);
        if(contact == null) {
            responseData.setSuccess(false);
            responseData.setDescription("Contact not found");
            return responseData;
        }
        contact.setRead(true);
        contactRepository.save(contact);
        responseData.setSuccess(true);
        return responseData;
    }
}
