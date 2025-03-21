package com.hrs.demo.service;

import com.hrs.demo.models.dto.response.ParcelResponseDto;
import com.hrs.demo.models.entity.Parcel;
import com.hrs.demo.repository.ParcelRepository;
import com.hrs.demo.services.impl.ParcelImpl;
import com.hrs.demo.validators.GuestValidator;
import com.hrs.demo.validators.ParcelValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParcelServiceTest {

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private GuestValidator guestValidator;

    @Mock
    private ParcelValidator parcelValidator;

    @InjectMocks
    private ParcelImpl parcelService;

    @Test
    public void testAddParcel() {
        // Arrange
        UUID guestId = UUID.randomUUID();
        Parcel parcel = Parcel.builder()
                    .guestId(guestId)
                    .pickedUp(Boolean.FALSE)
                    .receivedAt(LocalDateTime.now())
                    .build();

        // Simulate the DB assigning an ID after save
        Mockito.when(parcelRepository.save(Mockito.any(Parcel.class)))
                    .thenReturn(parcel);

        // Act
        ParcelResponseDto response = parcelService.addParcel(guestId);

        // Assert
        assertNotNull(response);
        verify(guestValidator).validateGuestChecking(guestId);
    }

    @Test
    public void testMarkParcelPickedUp() {
        // Arrange
        UUID parseId = UUID.randomUUID();
        Parcel parcel = Parcel.builder().id(parseId)
                .pickedUp(true).build();

        // Simulate the DB assigning an ID after save
        when(parcelValidator.validateParcelPickedUp(parseId)).thenReturn(parcel);
        Mockito.when(parcelRepository.save(Mockito.any(Parcel.class)))
                .thenReturn(parcel);

        // Act
        ParcelResponseDto result = parcelService.markParcelPickedUp(parseId);

        // Assert
        assertTrue(result.isPickedUp());
        assertNotNull(result);
    }


}

