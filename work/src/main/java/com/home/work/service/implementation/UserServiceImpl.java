package com.home.work.service.implementation;

import com.home.work.entity.ApplicationUser;
import com.home.work.entity.Item;
import com.home.work.models.ActiveUserDTO;
import com.home.work.models.ItemDTO;
import com.home.work.repository.ItemRepository;
import com.home.work.repository.UserRepository;
import com.home.work.service.UserService;
import com.home.work.service.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    private final PasswordEncoder passwordEncoder;
    private final SessionRegistry sessionRegistry;

    private final ModelMapper modelMapper;


    @Override
    public List<ActiveUserDTO> getActiveUsers() {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        List<ActiveUserDTO> activeUsers = new ArrayList<>();

        for (Object principal : principals) {
            if (principal instanceof ApplicationUser user) {
                List<SessionInformation> sessionInfoList = sessionRegistry.getAllSessions(principal, false);
                for (SessionInformation sessionInfo : sessionInfoList) {
                    if (sessionInfo.isExpired()) {
                        sessionRegistry.removeSessionInformation(sessionInfo.getSessionId());
                    } else {
                        ActiveUserDTO activeUserDTO = modelMapper.map(user, ActiveUserDTO.class);
                        activeUserDTO.setSessionId(sessionInfo.getSessionId());
                        activeUsers.add(activeUserDTO);
                    }
                }
            }
        }

        return activeUsers;
    }

    @Override
    public List<ApplicationUser> getInfoAllUsers() {
        List<ApplicationUser> users = userRepository.findAll();
        for (ApplicationUser user : users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return users;
    }

    @Override
    public void createBasketForUser(String username) {
        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow((UserNotFoundException::new));


        user.setBasket(new ArrayList<>());
        userRepository.save(user);
    }

    @Override
    public ApplicationUser addItem(ItemDTO item, Principal principal) {
        ApplicationUser user = userRepository.findByUsername(principal.getName())
                .orElseThrow((UserNotFoundException::new));

                user.getBasket().add(modelMapper.map(item, Item.class));

                return user;
    }

    @Override
    public void deleteBasketForUser(String username) {
        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow((UserNotFoundException::new));

        user.setLastConnectionDate(LocalDateTime.now());
        user.setBasket(null);
        userRepository.save(user);
    }

    @Override
    public List<Item> getAllItemForUser(Long userId) {
        return itemRepository.findByUserId(userId);
    }
}
