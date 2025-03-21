package com.hrs.demo.models.mapper;

import com.hrs.demo.models.dto.response.ParcelResponseDto;
import com.hrs.demo.models.entity.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParcelMapper {

    ParcelMapper parcelMapper = Mappers.getMapper(ParcelMapper.class);

    /**
     * @description Convert parcel to parcelResponseDto
     * @param parcel - parcel Parcel
     * @return parcelResponseDto
     * */
    ParcelResponseDto parcelToParcelResponseDto(Parcel parcel);

}
