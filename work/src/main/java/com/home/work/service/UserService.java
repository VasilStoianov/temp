package com.home.work.service;

import com.home.work.entity.ApplicationUser;
import com.home.work.entity.Item;
import com.home.work.models.ActiveUserDTO;
import com.home.work.models.ItemDTO;


import java.security.Principal;
import java.util.List;

public interface UserService {
     List<ActiveUserDTO> getActiveUsers();

    List<ApplicationUser> getInfoAllUsers();

    void createBasketForUser(String username);

    ApplicationUser addItem(ItemDTO item, Principal principal);

    void deleteBasketForUser(String username);

    List<Item> getAllItemForUser(Long userId);
}
