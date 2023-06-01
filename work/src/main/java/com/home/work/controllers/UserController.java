package com.home.work.controllers;;
import com.home.work.entity.Item;
import com.home.work.models.ActiveUserDTO;
import com.home.work.models.ItemDTO;
import com.home.work.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PutMapping("/item")
    public ResponseEntity<?> addItem( @RequestBody ItemDTO item,  Principal principal) {
        try {
            return ResponseEntity.ok(userService.addItem(item, principal));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/item/{userId}")
    public ResponseEntity<List<Item>> getAllItemForUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(userService.getAllItemForUser(userId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
