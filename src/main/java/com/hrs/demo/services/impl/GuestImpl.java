package com.hrs.demo.services.impl;

import com.hrs.demo.models.dto.request.GuestRequestDto;
import com.hrs.demo.models.dto.response.GuestResponseDto;
import com.hrs.demo.models.dto.response.ParcelResponseDto;
import com.hrs.demo.models.entity.Guest;
import com.hrs.demo.repository.GuestRepository;
import com.hrs.demo.repository.ParcelRepository;
import com.hrs.demo.services.GuestService;
import com.hrs.demo.validators.GuestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.hrs.demo.models.mapper.GuestMapper.guestMapper;
import static com.hrs.demo.models.mapper.ParcelMapper.parcelMapper;

@AllArgsConstructor
@Service
public class GuestImpl implements GuestService {
    private final GuestRepository guestRepository;

    private final ParcelRepository parcelRepository;

    private final GuestValidator guestValidator;

    @Override
    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public GuestResponseDto checkInGuest(GuestRequestDto guestRequestDto) {
        //Validator
        guestValidator.validateCheckInGuest(guestRequestDto);

        //Process logic
        Guest guest = guestMapper.guestRequestDtoToGuest(guestRequestDto);
        guest.setCheckedIn(Boolean.TRUE);
        guest.setCheckInDate(LocalDateTime.now());
        guest = guestRepository.save(guest);

        return guestMapper.guestToGuestResponseDto(guest);
    }

    @Override
    public GuestResponseDto checkOutGuest(UUID guestId) {
        //Validate and process
        Guest guest = guestValidator.validateCheckoutGuest(guestId);
        guest.setCheckedIn(Boolean.FALSE);
        guest.setCheckOutDate(LocalDateTime.now());
        guestRepository.save(guest);
        return guestMapper.guestToGuestResponseDto(guest);
    }

    @Override
    public List<ParcelResponseDto> getParcelsForGuest(UUID guestId) {
        Guest guest = guestValidator.validateGuestId(guestId);
        return parcelRepository.findByGuestIdAndPickedUpFalse(guest.getId())
                .stream().map(parcelMapper::parcelToParcelResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isGuestCheckedIn(UUID guestId) {
        //Validator and process
        return guestRepository.existsByIdAndCheckedInTrue(guestId);
    }
}
