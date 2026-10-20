package com.codewithmosh.store.services;

import com.codewithmosh.store.entities.Address;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityState() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@gmail.com")
                .password("password")
                .build();

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");

        userRepository.save(user);

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@gmail.com")
                .password("password")
                .build();

        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(1L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void manageProducts() {
        productRepository.deleteById(4L);
    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte)1);
    }

    @Transactional
    public void fetchProducts(){
        var products = productRepository.findProducts(BigDecimal.valueOf(1), BigDecimal.valueOf(15));
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUser() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void printLoyalProfiles() {
        var profiles = profileRepository.findLoyalProfiles(2);
        profiles.forEach(p -> System.out.println(p.getId() + ": " + p.getUser().getEmail()));
    }
}
