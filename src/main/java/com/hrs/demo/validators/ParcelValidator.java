package com.hrs.demo.validators;

import com.hrs.demo.models.entity.Parcel;
import com.hrs.demo.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.hrs.demo.constants.MessageErrors.PARCEL_NOT_FOUND;
import static com.hrs.demo.constants.MessageErrors.PARCEL_PICKED_UP;
import static com.hrs.demo.utils.CommonValidator.checkCondition;
import static com.hrs.demo.utils.CommonValidator.checkEmpty;

@AllArgsConstructor
@Component
public class ParcelValidator {

    private final ParcelRepository parcelRepository;

    /**
     * @description Validate check parcel id is exists and check is pickup or not
     * @param parcelId - parcelId UUID
     * @return parcel
     * */
    public Parcel validateParcelPickedUp(UUID parcelId) {
        Parcel parcel = parcelRepository.findById(parcelId).orElse(null);
        checkEmpty().accept(parcel, PARCEL_NOT_FOUND);
        checkCondition().accept(parcel.isPickedUp(), PARCEL_PICKED_UP);
        return parcel;
    }
}
