package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.dto.SentReqRequest;
import io.reflectoring.humsafar.dto.UserPreferenceRequest;
import io.reflectoring.humsafar.model.*;
import io.reflectoring.humsafar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class SentRequestController {
      private  final SentRequestRepository sentRequestsRepository;

  private final UserRepository appUserRepository;

    // 🔹 CREATE
    @PostMapping("/create-sent-request")
    public ResponseEntity<SentRequests> createUserPreference(@RequestBody SentReqRequest request) {
        SentRequests resRequest = mapSentRequest(request, new SentRequests());

        sentRequestsRepository.save(resRequest);
        return ResponseEntity.ok(resRequest);
    }

    // 🔹 READ All
    @GetMapping("/sent-requests")
    public ResponseEntity<List<SentRequests>> getAllSentRequests() {
        return ResponseEntity.ok(sentRequestsRepository.findAll());
    }

    // 🔹 READ By ID
    @GetMapping("/sent-request/{id}")
    public ResponseEntity<SentRequests> getSentRequestById(@PathVariable Long id) {
        SentRequests restRequest = sentRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SentRequest not found with ID: " + id));
        return ResponseEntity.ok(restRequest);
    }

    // 🔹 UPDATE
    @PutMapping("/update-sent-request/{id}")
    public ResponseEntity<SentRequests> updateUserPreference(@PathVariable Long id, @RequestBody SentReqRequest request) {
        SentRequests existing = sentRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SentRequest not found with ID: " + id));
        mapSentRequest(request, existing);


        sentRequestsRepository.save(existing);
        return ResponseEntity.ok(existing);
    }

    // 🔹 DELETE
    @DeleteMapping("/delete-sent-request/{id}")
    public ResponseEntity<String> deleteUserPreference(@PathVariable Long id) {
        SentRequests preference = sentRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SentRequest not found with ID: " + id));
        sentRequestsRepository.delete(preference);
        return ResponseEntity.ok("SentRequest deleted successfully.");
    }

    // 🔁 MAPPER FUNCTION
    private SentRequests mapSentRequest(SentReqRequest request, SentRequests request1) {

        if (request.getStatus() != null) request1.setStatus(request1.getStatus());


        if(request.getToUserId() != null) {
            AppUser user = appUserRepository.findById(request.getToUserId())
                    .orElseThrow(() -> new RuntimeException("Invalid user ID"));
            request1.setToUser(user);
        }

        if(request.getFromUserId() != null) {
            AppUser user = appUserRepository.findById(request.getFromUserId())
                    .orElseThrow(() -> new RuntimeException("Invalid user ID"));
            request1.setFromUser(user);
        }



        return request1;
    }
}
