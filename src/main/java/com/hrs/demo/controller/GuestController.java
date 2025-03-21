package com.hrs.demo.controller;

import com.hrs.demo.models.dto.request.GuestRequestDto;
import com.hrs.demo.models.dto.response.GuestResponseDto;
import com.hrs.demo.models.dto.response.ParcelResponseDto;
import com.hrs.demo.services.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.hrs.demo.constants.PathApi.*;

@Tag(
        name = "API Rest Guest",
        description = "Provide api checkIn, checkout"
)
@RestController
@RequestMapping(value = GUEST)
@AllArgsConstructor
public class GuestController {

    @Autowired
    private GuestService guestService;

    @Operation(
            summary = "Check in guest",
            description = "Check in guest by send information guest"
    )
    @PostMapping(GUEST_CHECK_IN)
    public ResponseEntity<GuestResponseDto> checkIn(@RequestBody GuestRequestDto guestRequestDto) {
        GuestResponseDto checkedInGuest = guestService.checkInGuest(guestRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(checkedInGuest);
    }

    @Operation(
            summary = "Check out guest",
            description = "Check out guest by send uuid guest id"
    )
    @GetMapping(GUEST_CHECK_OUT)
    public ResponseEntity<GuestResponseDto> checkOut(@PathVariable UUID guestId) {
        GuestResponseDto checkedOutGuest = guestService.checkOutGuest(guestId);
        return ResponseEntity.ok(checkedOutGuest);
    }

    @Operation(
            summary = "Get list parcels",
            description = "Get list parcels by provide uuid guest id"
    )
    @GetMapping(GUEST_LIST_PARCELS)
    public ResponseEntity<List<ParcelResponseDto>> getParcels(@PathVariable UUID guestId) {
        List<ParcelResponseDto> parcels = guestService.getParcelsForGuest(guestId);
        return ResponseEntity.ok(parcels);
    }
}
