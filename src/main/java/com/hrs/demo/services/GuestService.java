package com.hrs.demo.services;

import com.hrs.demo.models.dto.request.GuestRequestDto;
import com.hrs.demo.models.dto.response.GuestResponseDto;
import com.hrs.demo.models.dto.response.ParcelResponseDto;
import com.hrs.demo.models.entity.Guest;

import java.util.List;
import java.util.UUID;

public interface GuestService {
    Guest saveGuest(Guest guest);

    /**
     * @param guestRequestDto - guestRequestDto GuestRequestDto
     * @return the guest response detail
     * */
    GuestResponseDto checkInGuest(GuestRequestDto guestRequestDto);

    /**
     * @param guestId - guest uuid
     * @return the guest response detail
     * */
    GuestResponseDto checkOutGuest(UUID guestId);

    /**
     * @param guestId - guest uuid
     * @return the list parcel response detail
     * */
    List<ParcelResponseDto> getParcelsForGuest(UUID guestId);

    /**
     * @param guestId - guest uuid
     * @return the boolean
     * */
    boolean isGuestCheckedIn(UUID guestId);
}
