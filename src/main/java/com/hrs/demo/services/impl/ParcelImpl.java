package com.hrs.demo.services.impl;

import com.hrs.demo.models.dto.response.ParcelResponseDto;
import com.hrs.demo.models.entity.Parcel;
import com.hrs.demo.repository.ParcelRepository;
import com.hrs.demo.services.ParcelService;
import com.hrs.demo.validators.GuestValidator;
import com.hrs.demo.validators.ParcelValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.hrs.demo.models.mapper.ParcelMapper.parcelMapper;

@AllArgsConstructor
@Service
public class ParcelImpl implements ParcelService {
    private final ParcelRepository parcelRepository;

    private final GuestValidator guestValidator;

    private final ParcelValidator parcelValidator;

    @Override
    public ParcelResponseDto addParcel(UUID guestId) {
        guestValidator.validateGuestChecking(guestId);

        Parcel parcel = Parcel.builder()
                .guestId(guestId)
                .receivedAt(LocalDateTime.now())
                .pickedUp(Boolean.FALSE)
                .build();

        parcel = parcelRepository.save(parcel);

        return parcelMapper.parcelToParcelResponseDto(parcel);
    }

    @Override
    public ParcelResponseDto markParcelPickedUp(UUID parcelId) {
        Parcel parcel = parcelValidator.validateParcelPickedUp(parcelId);
        parcel.setPickedUp(Boolean.TRUE);
        parcel = parcelRepository.save(parcel);
        return parcelMapper.parcelToParcelResponseDto(parcel);
    }
}
