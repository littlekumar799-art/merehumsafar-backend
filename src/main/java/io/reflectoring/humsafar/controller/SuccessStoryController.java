package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.dto.ShortListedProfileRequest;
import io.reflectoring.humsafar.dto.SuccessStoryRequest;
import io.reflectoring.humsafar.model.*;
import io.reflectoring.humsafar.repository.SuccessStoryRepository;
import io.reflectoring.humsafar.repository.UserRepository;

import io.reflectoring.humsafar.repository.UserRepository;
import io.reflectoring.humsafar.service.AppSettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/master/success-story")
public class SuccessStoryController {

    private final SuccessStoryRepository successStoryRepository;
    private    UserRepository userRepository;
    public SuccessStoryController(SuccessStoryRepository successStoryRepository) {
        this.successStoryRepository = successStoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<SuccessStory>> getAllSuccessStory() {
        return ResponseEntity.ok(successStoryRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<SuccessStory> createSuccessStory(@RequestBody SuccessStoryRequest request) {
        AppUser brideUser = userRepository.findById(request.getBrideUserId())
                .orElseThrow(() -> new RuntimeException("Bride user not found"));

        AppUser groomUser = userRepository.findById(request.getGroomUserId())
                .orElseThrow(() -> new RuntimeException("Groom user not found"));

        // Create SuccessStory entity
        SuccessStory successStory = new SuccessStory();
        successStory.setBrideUser(brideUser);
        successStory.setGroomUser(groomUser);
        successStory.setStoryTitle(request.getStoryTitle());
        successStory.setStoryDescription(request.getStoryDescription());
        successStory.setPhotoUrl(request.getPhotoUrl());
        successStory.setVideoUrl(request.getVideoUrl());
        successStory.setLocation(request.getLocation());
        successStory.setApproved("PENDING"); // default status
        successStory.setMarrageDate(request.getMarrageDate());
        successStory.setCreatedAt(OffsetDateTime.now());
        successStory.setUpdatedAt(OffsetDateTime.now());

        // Save
        SuccessStory saved = successStoryRepository.save(successStory);

        return ResponseEntity.ok(saved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessStory> updateSuccessStory(@PathVariable Long id,
                                                           @RequestBody SuccessStoryRequest request) {
        return successStoryRepository.findById(id)
                .map(story -> {
                    story.setStoryTitle(request.getStoryTitle());
                    story.setStoryDescription(request.getStoryDescription());
                    story.setPhotoUrl(request.getPhotoUrl());
                    story.setVideoUrl(request.getVideoUrl());
                    story.setLocation(request.getLocation());
                    story.setMarrageDate(request.getMarrageDate());
                    story.setUpdatedAt(OffsetDateTime.now());

                    // if brideUserId/groomUserId are updatable:
                    if (request.getBrideUserId() != null) {
                        AppUser bride = userRepository.findById(request.getBrideUserId())
                                .orElseThrow(() -> new RuntimeException("Bride not found"));
                        story.setBrideUser(bride);
                    }
                    if (request.getGroomUserId() != null) {
                        AppUser groom = userRepository.findById(request.getGroomUserId())
                                .orElseThrow(() -> new RuntimeException("Groom not found"));
                        story.setGroomUser(groom);
                    }

                    SuccessStory updated = successStoryRepository.save(story);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuccessStory(@PathVariable Long id) {
        if (successStoryRepository.existsById(id)) {
            successStoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}
