package com.example.sampleapplication.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrentResults implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("designation")
    @Expose
    private String designation;

    @SerializedName("employee_id")
    @Expose
    private String employee_id;

    }
