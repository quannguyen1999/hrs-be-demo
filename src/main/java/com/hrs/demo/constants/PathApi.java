package com.hrs.demo.constants;

import lombok.experimental.UtilityClass;

/**
 * Define Path api
 * Guild: MODEL_<name meaning api>
 * */
@UtilityClass
public class PathApi {
    public final String GUEST = "/guests";
    public final String GUEST_CHECK_IN = "/checkIn";
    public final String GUEST_CHECK_OUT = "/{guestId}/checkout";
    public final String GUEST_LIST_PARCELS = "/{guestId}/parcels";
    public final String PARCEL = "/parcels";
    public final String PARCEL_ADD = "/guest/{guestId}";
    public final String PARCEL_PICKUP = "/{parcelId}/pickup";

}
