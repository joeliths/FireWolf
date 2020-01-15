package com.example.demo.models.view;

import java.util.List;

public interface StoreCustomerView {

    String store_uuid();
    String getProduct_uuid();
    String getProduct_name();
    String getProduct_description();
    String getStore_address();
    Integer getInventory_product_price();
    String getInventory_product_uuid();
    Integer getInventory_product_stock();

}
