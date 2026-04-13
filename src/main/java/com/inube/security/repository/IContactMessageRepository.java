package com.inube.security.repository;

import com.inube.security.model.ContactMessageModel;

import java.util.List;

public interface IContactMessageRepository {
    int save(ContactMessageModel message);

    int updateStatus(String messageId, String status, String userResponse);

    int logicalDelete(String messageId);

    List<ContactMessageModel> findAll();

    List<ContactMessageModel> findByStatus(String status);

    ContactMessageModel findById(String messageId);
}
