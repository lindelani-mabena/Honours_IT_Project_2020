package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

public class Address {
    int address_id;
    String address_line1;
    String address_line2;
    String address_town;
    String address_city;
    String address_province;
    int address_postalcode;
    int address_phonenumber;

    public int getAddress_PhoneNumber() {
        return address_phonenumber;
    }

    public void setAddress_PhoneNumber(int address_PhoneNumber) {
        address_phonenumber = address_PhoneNumber;
    }

    public int getAddress_Id() {
        return address_id;
    }

    public void setAddress_Id(int address_Id) {
        address_id = address_Id;
    }

    public String getAddress_Line1() {
        return address_line1;
    }

    public void setAddress_Line1(String address_Line1) {
        address_line1 = address_Line1;
    }

    public String getAddressLine2() {
        return address_line2;
    }

    public void setAddressLine2(String addressLine2) {
        address_line2 = addressLine2;
    }

    public String getAddress_Town() {
        return address_town;
    }

    public void setAddress_Town(String address_Town) {
        address_town = address_Town;
    }

    public String getAddress_City() {
        return address_city;
    }

    public void setAddress_City(String address_City) {
        address_city = address_City;
    }

    public String getAddress_Province() {
        return address_province;
    }

    public void setAddress_Province(String address_Province) {
        address_province = address_Province;
    }

    public int getAddress_PostalCode() {
        return address_postalcode;
    }

    public void setAddress_PostalCode(int address_PostalCode) {
        address_postalcode = address_PostalCode;
    }
}
