package io.reflectoring.humsafar.controller;
import io.reflectoring.humsafar.dto.MessageRequest;

import io.reflectoring.humsafar.model.AppUser;
import io.reflectoring.humsafar.model.Message;

import io.reflectoring.humsafar.repository.MessageRepository;

import io.reflectoring.humsafar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class MessageController {
    private  final MessageRepository messageRepository;

    private final UserRepository appUserRepository;

    // 🔹 CREATE
    @PostMapping("/create-message")
    public ResponseEntity<Message> createMessage(@RequestBody MessageRequest request) {
        Message message = mapMessageRequest(request, new Message());

        messageRepository.save(message);
        return ResponseEntity.ok(message);
    }

    // 🔹 READ All
    @GetMapping("/sent-messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageRepository.findAll());
    }

    // 🔹 READ By ID
    @GetMapping("/sent-message/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with ID: " + id));
        return ResponseEntity.ok(message);
    }

    // 🔹 UPDATE
    @PutMapping("/update-message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody MessageRequest request) {
        Message existing = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with ID: " + id));
        mapMessageRequest(request, existing);


        messageRepository.save(existing);
        return ResponseEntity.ok(existing);
    }

    // 🔹 DELETE
    @DeleteMapping("/delete-message/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        Message preference = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SentRequest not found with ID: " + id));
        messageRepository.delete(preference);
        return ResponseEntity.ok("Message deleted successfully.");
    }

    // 🔁 MAPPER FUNCTION
    private Message mapMessageRequest(MessageRequest message, Message request) {

        if (message.getMessage() != null) request.setMessage(request.getMessage());


        if(message.getReceiverId() != null) {
            AppUser user = appUserRepository.findById(message.getReceiverId())
                    .orElseThrow(() -> new RuntimeException("Invalid receiver ID"));
            request.setReceiver(user);
        }

        if(message.getSenderId() != null) {
            AppUser user = appUserRepository.findById(message.getSenderId())
                    .orElseThrow(() -> new RuntimeException("Invalid sender ID"));
            request.setSender(user);
        }

        return request;
    }
}

