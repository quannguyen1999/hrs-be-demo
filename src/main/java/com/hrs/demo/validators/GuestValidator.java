package com.hrs.demo.validators;

import com.hrs.demo.models.dto.request.GuestRequestDto;
import com.hrs.demo.models.entity.Guest;
import com.hrs.demo.repository.GuestRepository;
import com.hrs.demo.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.hrs.demo.constants.MessageErrors.*;
import static com.hrs.demo.utils.CommonValidator.*;

@AllArgsConstructor
@Component
public class GuestValidator {

    private final GuestRepository guestRepository;

    private final ParcelRepository parcelRepository;

    /**
     * @description Validate when guest check in
     * @param guestRequestDto - guestRequestDto GuestRequestDto
     * */
    public void validateCheckInGuest(GuestRequestDto guestRequestDto) {
        validateCheckUserName(guestRequestDto.getName());
        validateCheckPhone(guestRequestDto.getPhone());
    }

    /**
     * @description Validate check guest is exists or not
     * @param guestId - guestId UUID
     * @return guest
     * */
    public Guest validateGuestId(UUID guestId) {
        Guest guest = guestRepository.findById(guestId).orElse(null);
        checkEmpty().accept(guest, GUEST_NOT_FOUND);
        return guest;
    }

    /**
     * @description Validate guest check out
     * @param guestId - guestId UUID
     * @return guest
     * */
    public Guest validateCheckoutGuest(UUID guestId) {
        Guest guest = guestRepository.findById(guestId).orElse(null);
        checkEmpty().accept(guest, GUEST_NOT_FOUND);
        checkCondition().accept(!guest.isCheckedIn(), GUEST_ALREADY_CHECKOUT);

        //Check does guest still have any parcels ot not
        checkCondition().accept(!parcelRepository.findByGuestIdAndPickedUpFalse(guest.getId())
                        .isEmpty(),
                GUEST_NOT_PICK_UP_PARCEL);
        return guest;
    }

    /**
     * @description Validate check guest is checking or not
     * @param guestId - guestId UUID
     * */
    public void validateGuestChecking(UUID guestId) {
        Guest guest = validateGuestId(guestId);
        checkCondition().accept(!guest.isCheckedIn(), GUEST_NOT_CHECKING);
    }

    /**
     * @description Validate check name guest
     * @param name - name String
     * */
    private void validateCheckUserName(String name) {
        checkEmpty().accept(name, GUEST_NAME_IS_EMPTY);
    }

    /**
     * @description Validate check phone
     * @param phone - phone String
     * */
    private void validateCheckPhone(String phone) {
        checkEmpty().accept(phone, GUEST_PHONE_INVALID);
        checkCondition().accept(!phone.matches(PHONE_REGEX), GUEST_PHONE_INVALID);
    }
}
