
package com.example.appiness.appsrivnews.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("items")
    @Expose
    private java.util.List<Item> items = null;

    public java.util.List<Item> getItems() {
        return items;
    }

    public void setItems(java.util.List<Item> items) {
        this.items = items;
    }

}
