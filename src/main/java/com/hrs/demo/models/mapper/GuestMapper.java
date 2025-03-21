package com.hrs.demo.models.mapper;


import com.hrs.demo.models.dto.request.GuestRequestDto;
import com.hrs.demo.models.dto.response.GuestResponseDto;
import com.hrs.demo.models.entity.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GuestMapper {

    GuestMapper guestMapper = Mappers.getMapper(GuestMapper.class);

    /**
     * @description Convert guest to guestResponseDto
     * @param guest - guest Guest
     * @return guestResponseDto
     * */
    GuestResponseDto guestToGuestResponseDto(Guest guest);

    /**
     * @description Convert GuestRequestDto to guest
     * @param guestRequestDto - guestRequestDto GuestRequestDto
     * @return guest
     * */
    Guest guestRequestDtoToGuest(GuestRequestDto guestRequestDto);


}
