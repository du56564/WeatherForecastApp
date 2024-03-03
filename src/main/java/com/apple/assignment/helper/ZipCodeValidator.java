package com.apple.assignment.helper;

import com.apple.assignment.constants.CommonConstants;

import java.util.regex.Pattern;

public class ZipCodeValidator {

    public static boolean isValidZipCode(String zipcode) {
        return Pattern.matches(CommonConstants.ZIP_CODE_PATTERN, zipcode);
    }

}
