package com.vannt.projecticdayroi.services;

import com.vannt.projecticdayroi.entity.ContactEntity;
import com.vannt.projecticdayroi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServicesImp implements ContactServices{

    @Autowired
    ContactRepository contactRepository;

    @Override
    public void saveItem(ContactEntity contactEntity) {
        contactRepository.save(contactEntity);
    }
}
