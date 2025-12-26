package com.tek.constants;

public class OrderConstants {
    public static final String ORDER_CREATED_CODE = "ORD_201";
    public static final String ORDER_UPDATED_CODE = "ORD_200";
    public static final String ORDER_DELETED_CODE = "ORD_204";
    public static final String ORDER_FETCHED_CODE = "ORD_200";
    public static final String ORDER_NOT_FOUND_CODE = "ORD_404";
    public static final String ORDER_BAD_REQUEST_CODE = "ORD_400";
    public static final String ORDER_PAYMENT_FAILED_CODE = "ORD_402";
    public static final String ORDER_ALREADY_EXISTS_CODE = "ORD_409";
    public static final String ORDER_OUT_OF_STOCK_CODE = "ORD_410";  // Stock related failure
    public static final String ORDER_PROCESSING_FAILED_CODE = "ORD_500"; // Internal process error

    public static final String ORDER_CREATED_MSG = "Order placed successfully";
    public static final String ORDER_UPDATED_MSG = "Order updated successfully";
    public static final String ORDER_DELETED_MSG = "Order cancelled successfully";
    public static final String ORDER_FETCHED_MSG = "Order details fetched successfully";

    public static final String ORDER_NOT_FOUND_MSG = "Requested order does not exist";
    public static final String ORDER_BAD_REQUEST_MSG = "Invalid order request details";
    public static final String ORDER_ALREADY_EXISTS_MSG = "Duplicate order request detected";
    public static final String ORDER_PAYMENT_FAILED_MSG = "Payment failed while processing the order";
    public static final String ORDER_OUT_OF_STOCK_MSG = "Product is out of stock";
    public static final String ORDER_PROCESSING_FAILED_MSG = "Unable to process order due to internal error";
}
