package com.home.work.controllers;

import com.home.work.entity.ApplicationUser;
import com.home.work.models.ActiveUserDTO;
import com.home.work.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping("/info-active-users")
    public ResponseEntity<List<ActiveUserDTO>> getActiveUsers() {
        try {
            return ResponseEntity.ok(userService.getActiveUsers());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/info-all-users")
    public ResponseEntity<List<ApplicationUser>> getInfoAllUsers() {
        try {
            return ResponseEntity.ok(userService.getInfoAllUsers());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
