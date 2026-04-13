package com.inube.security.service;

import com.inube.security.dto.RequestMessangeDto;
import com.inube.security.model.ContactMessageModel;

import java.util.List;

public interface IContactMessageService {
    void create(RequestMessangeDto dto);

    void markAsRead(RequestMessangeDto dto);

    void markAsAnswered(RequestMessangeDto dto);

    void deleteLogical(String messageId);

    List<ContactMessageModel> getAll();

    List<ContactMessageModel> getRead();

    List<ContactMessageModel> getAnswered();


}
