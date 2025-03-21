package com.hrs.demo.services;

import com.hrs.demo.models.dto.response.ParcelResponseDto;

import java.util.UUID;

public interface ParcelService {
    /**
    * @param guestId - guest uuid
    * @return the parcel response detail
    * */
    ParcelResponseDto addParcel(UUID guestId);

    /**
     * @param parcelId - parcel uuid
     * @return the parcel response detail
     * */
    ParcelResponseDto markParcelPickedUp(UUID parcelId);
}
