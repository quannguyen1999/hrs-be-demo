package com.hrs.demo.utils;

import com.hrs.demo.constants.MessageErrors;
import com.hrs.demo.exceptions.BadRequestException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CommonValidator {
    public static final String PHONE_REGEX = "^\\+?[0-9]{7,15}$|^\\+?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";

    /**
     * @description Check data is empty or not
     * @param object - object Object
     * @return error if have
     * */
    public static BiConsumer<Object, MessageErrors> checkEmpty() {
        return (input, messageError) -> {
            if (ObjectUtils.isEmpty(input) || (input instanceof String && !StringUtils.hasLength(input.toString()))
            ) {
                badRequest().accept(messageError);
            }
        };
    }

    /**
     * @description Check condition true then return error
     * @param boolean - boolean Boolean
     * @return error if have
     * */
    public static BiConsumer<Boolean, MessageErrors> checkCondition() {
        return (input, messageError) -> {
            if (input) {
                badRequest().accept(messageError);
            }
        };
    }

    /**
     * @description Throw bad error
     * @param messageErrors - messageErrors MessageErrors
     * @return throw error
     * */
    public static Consumer<MessageErrors> badRequest() {
        return messageErrors -> {
            throw new BadRequestException(messageErrors);
        };
    }


}
