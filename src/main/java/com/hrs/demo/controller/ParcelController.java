package com.hrs.demo.controller;

import com.hrs.demo.models.dto.response.ParcelResponseDto;
import com.hrs.demo.services.ParcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.hrs.demo.constants.PathApi.*;

@Tag(
        name = "API Rest Parcel",
        description = "Provide api add parcel, pick up parcel"
)
@RestController
@RequestMapping(value = PARCEL)
public class ParcelController {

    @Autowired
    private  ParcelService parcelService;

    @Operation(
            summary = "Add parcel",
            description = "Add parcel by send uuid guest id"
    )
    @GetMapping(PARCEL_ADD)
    public ResponseEntity<ParcelResponseDto> addParcel(@PathVariable UUID guestId) {
        ParcelResponseDto createdParcel = parcelService.addParcel(guestId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParcel);
    }

    @Operation(
            summary = "Pick up parcel",
            description = "Pick up parcel by send uuid guest id"
    )
    @GetMapping(PARCEL_PICKUP)
    public ResponseEntity<ParcelResponseDto> pickUpParcel(@PathVariable UUID parcelId) {
        ParcelResponseDto updatedParcel = parcelService.markParcelPickedUp(parcelId);
        return ResponseEntity.ok(updatedParcel);
    }
}
