package com.codewithmosh.store;

import org.springframework.stereotype.Service;

@Service("user")
public class UserService {
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUSer(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new IllegalArgumentException("User with email [" + user.getEmail() + "] already exists");
        }

        userRepository.save(user);
        notificationService.send("You've registered successfully", user.getEmail());
    }
}
