package com.hrs.demo.repository;

import com.hrs.demo.models.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID> {

    boolean existsByIdAndCheckedInTrue(UUID guestId);

    Guest findByPhone(String phone);

}
