package com.vkomissarov.customer.utils;

import com.vkomissarov.customer.exceptions.BadRequestAlertException;

import static com.vkomissarov.customer.utils.StringConstants.ENTITY_CUSTOMER_NAME;

public class StringUtils {

    public static void validate(String customerId) {
        if (org.apache.commons.lang3.StringUtils.isBlank(customerId)) {
            throw new BadRequestAlertException("No Customer", ENTITY_CUSTOMER_NAME, "noid");
        }
    }
}
