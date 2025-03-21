package com.hrs.demo.repository;

import com.hrs.demo.models.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, UUID> {
    List<Parcel> findByGuestIdAndPickedUpFalse(UUID guestId);

}
