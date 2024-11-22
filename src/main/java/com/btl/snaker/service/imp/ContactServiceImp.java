package com.btl.snaker.service.imp;

import com.btl.snaker.entity.Contact;
import com.btl.snaker.payload.ResponseData;
import com.btl.snaker.payload.request.ContactRequest;

public interface ContactServiceImp {
    ResponseData getAllContacts(int page);
    ResponseData getContactById(long id);
    ResponseData createContact(ContactRequest contactRequest);
    ResponseData solveRead(long contactId);
}
