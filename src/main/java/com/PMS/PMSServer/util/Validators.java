
package com.PMS.PMSServer.util;

import com.PMS.PMSServer.model.AddressType;
import com.PMS.PMSServer.model.PMSParcelDeliveryType;
import com.PMS.PMSServer.model.PMSParcelPackingPreference;
import com.PMS.PMSServer.model.PMSParcelParcelStatus;
import com.PMS.PMSServer.model.PMSParcelPaymentStatus;
import com.PMS.PMSServer.model.PMSUserRole;



import java.util.regex.Pattern;

public class Validators {

    
    public static void validateEmail(String fieldName,String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email == null || !Pattern.matches(regex, email)) {
            throw new IllegalArgumentException("Invalid "+fieldName+" format.Email must have valid Format.");
        }
    }

   
    public static void validateName(String fieldName,String name) {
        String regex = "^[A-Za-z ]{2,50}$";
        if (name == null || !Pattern.matches(regex, name)) {
            throw new IllegalArgumentException("Invalid "+fieldName +". Only letters and spaces allowed (2-50 characters).");
        }
    }

  
    public static void validateId(String fieldName,int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid "+ fieldName+". ID must be a positive number.");
        }
    }

   
    public static void validatePhoneNumber(String fieldName,String phoneNumber) {
        String regex = "^[6-9][0-9]{9}$";
        if (phoneNumber == null || !Pattern.matches(regex, phoneNumber)) {
            throw new IllegalArgumentException("Invalid"+fieldName+" Must be a 10-digit number starting with 6-9.");
        }
    }

   
    public static void validatePassword(String fieldName,String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (password == null || !Pattern.matches(regex, password)) {
            throw new IllegalArgumentException("Invalid "+fieldName+". Must include uppercase, lowercase, and a digit, min 8 characters.");
        }
    }

    
    public static void validateAddress(String fieldName,String address) {
        String regex = "^[A-Za-z0-9\\s,.-]{5,100}$";
        if (address == null || !Pattern.matches(regex, address)) {
            throw new IllegalArgumentException("Invalid "+fieldName+" Should be 5 to 100 characters.");
        }
    }

   
    public static void validatePincode(String fieldName,String pincode) {
        String regex = "^[1-9][0-9]{5}$";
        if (pincode == null || !Pattern.matches(regex, pincode)) {
            throw new IllegalArgumentException("Invalid "+fieldName+" Must be a 6-digit number.");
        }
    }

    
    public static void validateWeight(String fieldName,Double weight) {
        if (weight == null || weight <= 0) {
            throw new IllegalArgumentException("Invalid "+fieldName+". Must be a positive number greater than 0.");
        }
    }

   
    public static void validateTrackingId(String fieldName,int trackingId) {
       // String regex = "^[A-Za-z0-9]{8,20}$";
        if ( trackingId<0 || trackingId>100000) {
            throw new IllegalArgumentException("Invalid "+fieldName+". Must be between 1 to 100000.");
        }
    }

  
    public static void validateNonEmpty(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid " + fieldName + ". It must not be empty.");
        }
    }

    
    public static void validateUserRole(String fieldName,String role) {
        try {
            PMSUserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + ". Allowed values are: ADMIN, CUSTOMER.");
        }
    }

   
    public static void validateDeliveryType(String fieldName,String deliveryType) {
        try {
            PMSParcelDeliveryType.valueOf(deliveryType.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + ". Allowed values are: STANDARD, EXPRESS, SAME_DAY, INTERNATIONAL etc.");
        }
    }

   
    public static void validateSenderName(String fieldName,String name) {
    	 String regex = "^[A-Za-z ]{2,50}$";
         if (name == null || !Pattern.matches(regex, name)) {
             throw new IllegalArgumentException("Invalid "+fieldName +". Only letters and spaces allowed (2-50 characters).");
         }// reuse existing name validator
    }
 
    
    public static void validateParcelPin(String fieldName,String parcelPin) {
    	 String regex = "^[1-9][0-9]{5}$";
         if (parcelPin == null || !Pattern.matches(regex, parcelPin)) {
             throw new IllegalArgumentException("Invalid "+fieldName+" Must be a 6-digit number.");
         } // reuse pincode validator
    }

  
    public static void validateServiceCost(String fieldName,Double cost) {
        if (cost == null || cost < 0) {
            throw new IllegalArgumentException("Invalid  "+fieldName+". Must be zero or positive.");
        }
    }

    
    public static void validatePaymentStatus(String fieldName,String status) {
        try {
            PMSParcelPaymentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid "+fieldName+" Allowed values: PAID, UNPAID, PENDING, etc.");
        }
    }

    
    public static void validateParcelStatus(String fieldName,String status) {
        try {
            PMSParcelParcelStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid  "+fieldName+". Allowed values: CREATED, IN_TRANSIT, DELIVERED, etc.");
        }
    }
    public static void validatePackingPreference(String fieldName,String status) {
        try {
            PMSParcelPackingPreference.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid  "+fieldName+". Allowed values: BOX, ENVELOPE, BUBBLE_WRAP, CUSTOM etc.");
        }
    }
    public static void validateParcelContentsDescription(String fieldName,String name) {
        String regex = "^[A-Za-z ]{2,50}$";
        if (name == null || !Pattern.matches(regex, name)) {
            throw new IllegalArgumentException("Invalid "+fieldName +". Only letters and spaces allowed (2-50 characters).");
        }
    }
    public static void validateAddressType(String fieldName,String addressType) {
        try {
            AddressType.valueOf(addressType.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid "+fieldName+" Allowed values: HOME, WORK, OTHER etc.");
        }
    }
    public static void validateFeedBack(String fieldName,String feedBack) {
//        String regex = "^[A-Za-z0-9 ]{7,50}$";
    	//!Pattern.matches(regex, feedBack)
        if (feedBack.length() <= 0) {
            throw new IllegalArgumentException("Invalid "+fieldName +" (7-50 characters).");
        }
    }
    public static void validateFeedBackRating(String fieldName,Double rating) {
        if(rating <=0 || rating==null || rating<0.0) {
             throw new IllegalArgumentException("Invalid "+fieldName +". FeedBack Should Be Between ( 0 AND 5 ).");
        }
    }
    
}

