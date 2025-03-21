package com.hrs.demo.service;

import com.hrs.demo.models.dto.request.GuestRequestDto;
import com.hrs.demo.models.dto.response.GuestResponseDto;
import com.hrs.demo.models.dto.response.ParcelResponseDto;
import com.hrs.demo.models.entity.Guest;
import com.hrs.demo.repository.GuestRepository;
import com.hrs.demo.services.impl.GuestImpl;
import com.hrs.demo.validators.GuestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private GuestRepository guestRepository;

    @Mock
    private GuestValidator guestValidator;

    @InjectMocks
    private GuestImpl guestService;

    @Test
    public void testCheckInGuest() {
        // Arrange
        GuestRequestDto guestRequestDto = GuestRequestDto.builder()
                .name("Quan")
                .phone("0708821227")
                .build();

        Guest guest = Guest.builder()
                .id(UUID.randomUUID())
                .name(guestRequestDto.getName())
                .phone(guestRequestDto.getPhone())
                .build();

        // Simulate the DB assigning an ID after save
        Mockito.when(guestRepository.save(Mockito.any(Guest.class)))
                .thenReturn(guest);

        // Act
        GuestResponseDto response = guestService.checkInGuest(guestRequestDto);

        // Assert
        assertNotNull(response);
        assertEquals(guest.getName(), response.getName());
        verify(guestValidator).validateCheckInGuest(guestRequestDto);
        verify(guestRepository).save(any(Guest.class));
    }

    @Test
    public void testCheckOutGuest() {
        // Arrange
        UUID guestId = UUID.randomUUID();
        Guest guest = Guest.builder().id(guestId)
                .checkedIn(true).build();

        // Simulate the DB assigning an ID after save
        when(guestValidator.validateCheckoutGuest(guestId)).thenReturn(guest);

        // Act
        GuestResponseDto result = guestService.checkOutGuest(guestId);

        // Assert
        assertFalse(result.isCheckedIn());
        assertNotNull(result.getCheckOutDate());
    }

    @Test
    public void testGetParcelsForGuest() {
        // Arrange
        UUID guestId = UUID.randomUUID();
        Guest guest = Guest.builder().id(guestId).build();

        // Simulate the DB assigning an ID after save
        when(guestValidator.validateGuestId(guestId)).thenReturn(guest);

        List<ParcelResponseDto> parcels = guestService.getParcelsForGuest(guestId);

        // Assert
        assertNotNull(parcels);
        assertEquals(0, parcels.size());
    }

    @Test
    public void testIsGuestCheckedIn() {
        // Arrange
        UUID guestId = UUID.randomUUID();

        // Simulate the DB assigning an ID after save
        when(guestRepository.existsByIdAndCheckedInTrue(guestId)).thenReturn(true);

        boolean result = guestService.isGuestCheckedIn(guestId);

        // Assert
        assertTrue(result);
    }
}
