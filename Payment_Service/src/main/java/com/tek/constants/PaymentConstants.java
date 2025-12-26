package com.tek.constants;

public final class PaymentConstants {
    public static final String PAYMENT_INITIATED_CODE = "PAY_100";
    public static final String PAYMENT_SUCCESS_CODE = "PAY_200";
    public static final String PAYMENT_FAILED_CODE = "PAY_402";
    public static final String PAYMENT_REFUNDED_CODE = "PAY_205";
    public static final String PAYMENT_CANCELLED_CODE = "PAY_204";
    public static final String PAYMENT_NOT_FOUND_CODE = "PAY_404";
    public static final String PAYMENT_BAD_REQUEST_CODE = "PAY_400";
    public static final String PAYMENT_DUPLICATE_CODE = "PAY_409";
    public static final String PAYMENT_SERVER_ERROR_CODE = "PAY_500";

    public static final String PAYMENT_INITIATED_MSG = "Payment initiated successfully";
    public static final String PAYMENT_SUCCESS_MSG = "Payment completed successfully";
    public static final String PAYMENT_REFUNDED_MSG = "Payment refunded successfully";
    public static final String PAYMENT_CANCELLED_MSG = "Payment cancelled successfully";

    public static final String PAYMENT_FAILED_MSG = "Payment failed due to processing issue";
    public static final String PAYMENT_NOT_FOUND_MSG = "Requested payment record does not exist";
    public static final String PAYMENT_BAD_REQUEST_MSG = "Invalid payment request details";
    public static final String PAYMENT_DUPLICATE_MSG = "Duplicate payment request detected";
    public static final String PAYMENT_SERVER_ERROR_MSG = "Unexpected error occurred during payment processing";
}
