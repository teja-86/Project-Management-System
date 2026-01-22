package com.aryan.service;

import com.aryan.model.entity.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long chatId, String content) throws Exception;

    List<Message> getMessagesByProjectId(Long projectId) throws Exception;

}
