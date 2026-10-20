package com.codewithmosh.store.repositories;

import com.codewithmosh.store.dtos.UserSummary;
import com.codewithmosh.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Query("SELECT p.id AS id, p.user.email AS email FROM Profile p WHERE p.loyaltyPoints > :loyaltyPoints ORDER BY p.user.email")
    @EntityGraph(attributePaths = {"user"})
    List<UserSummary> findLoyalProfiles(@Param("loyaltyPoints") int loyaltyPoints);
}
