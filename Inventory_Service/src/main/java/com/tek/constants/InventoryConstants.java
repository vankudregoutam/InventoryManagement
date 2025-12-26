package com.tek.constants;

public class InventoryConstants {
    public static final String INVENTORY_CREATED_CODE = "INV_201";
    public static final String INVENTORY_UPDATED_CODE = "INV_200";
    public static final String INVENTORY_DELETED_CODE = "INV_204";
    public static final String INVENTORY_FETCHED_CODE = "INV_200";
    public static final String INVENTORY_NOT_FOUND_CODE = "INV_404";
    public static final String INVENTORY_BAD_REQUEST_CODE = "INV_400";
    public static final String INVENTORY_OUT_OF_STOCK_CODE = "INV_410";
    public static final String INVENTORY_ALREADY_EXISTS_CODE = "INV_409";
    public static final String STOCK_DEDUCT_CODE = "INV-200-D";
    public static final String STOCK_ROLLBACK_CODE = "INV-200-R";

    public static final String INVENTORY_CREATED_MSG = "Inventory record created successfully";
    public static final String INVENTORY_UPDATED_MSG = "Inventory record updated successfully";
    public static final String INVENTORY_DELETED_MSG = "Inventory deleted successfully";
    public static final String INVENTORY_FETCHED_MSG = "Inventory fetched successfully";
    public static final String STOCK_DEDUCT_MSG = "Stock deducted successfully";
    public static final String STOCK_ROLLBACK_MSG = "Stock rollback completed successfully";

    public static final String INVENTORY_NOT_FOUND_MSG = "Requested inventory item does not exist";
    public static final String INVENTORY_BAD_REQUEST_MSG = "Invalid inventory details provided";
    public static final String INVENTORY_ALREADY_EXISTS_MSG = "Inventory record already exists for this product";
    public static final String INVENTORY_OUT_OF_STOCK_MSG = "Product is currently out of stock";

}
