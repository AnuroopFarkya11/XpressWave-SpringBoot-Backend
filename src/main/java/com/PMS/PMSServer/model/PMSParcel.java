//package com.PMS.PMSServer.model;
//import java.time.LocalDateTime;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class PMSParcel {
//    private int parcel_Id;
//    private String senderName;
//    private String senderAddress;
//    private String senderPin;
//    private String senderMobile;
//    private String receiverName;
//    private String receiverAddress;
//    private String receiverPin;
//    private String receiverMobile;
//    private Double parcelWeightInGram;
//    private String parcelContentsDescription;
//    private String parcelDeliveryType;
//    private String parcelPackingPreference;
//    private LocalDateTime parcelPlacedTime;
//    private LocalDateTime parcelPickupTime;
//    private LocalDateTime parcelDropoffTime;
//    private Double serviceCost;
//    private String paymentStatus;
//    private LocalDateTime parcelPaymentTime;
//    private String parcelStatus;
//
// 
//}
package com.PMS.PMSServer.model;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PMSParcel {
    private int parcel_Id;
    private String senderName;
    private String senderAddress;
    private String senderPin;
    private String senderMobile;
    private String receiverName;
    private String receiverAddress;
    private String receiverPin;
    private String receiverMobile;
    private Double parcelWeightInGram;
    private Double parcelLength;
    private Double parcelBreadth;
    private Double parcelSize;
    private String parcelContentsDescription;
    private String parcelDeliveryType;
    private String parcelPackingPreference;
    private LocalDateTime parcelPlacedTime;
    private LocalDateTime parcelPickupTime;
    private LocalDateTime parcelDropoffTime;
    private Double serviceCost;
    private String paymentStatus;
    private LocalDateTime parcelPaymentTime;
    private String parcelStatus;

 
}