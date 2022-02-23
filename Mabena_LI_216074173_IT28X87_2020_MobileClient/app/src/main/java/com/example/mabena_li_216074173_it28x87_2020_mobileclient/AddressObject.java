package com.example.mabena_li_216074173_it28x87_2020_mobileclient;


public class AddressObject {
    int Address_Id;
    String Address_Line1;
    String AddressLine2;
     String Address_Town;
  String Address_City;
     String Address_Province;
   int Address_PostalCode;

    public void setAddress_PostalCode(int address_PostalCode) {
        Address_PostalCode = address_PostalCode;
    }

    String Address_PhoneNumber;

    public int getAddress_Id() {
        return Address_Id;
    }

    public void setAddress_Id(int address_Id) {
        Address_Id = address_Id;
    }

    public String getAddress_Line1() {
        return Address_Line1;
    }

    public void setAddress_Line1(String address_Line1) {
        Address_Line1 = address_Line1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getAddress_Town() {
        return Address_Town;
    }

    public void setAddress_Town(String address_Town) {
        Address_Town = address_Town;
    }

    public String getAddress_City() {
        return Address_City;
    }

    public void setAddress_City(String address_City) {
        Address_City = address_City;
    }

    public String getAddress_Province() {
        return Address_Province;
    }

    public void setAddress_Province(String address_Province) {
        Address_Province = address_Province;
    }


    public String getAddress_PhoneNumber() {
        return Address_PhoneNumber;
    }

    public void setAddress_PhoneNumber(String address_PhoneNumber) {
        Address_PhoneNumber = address_PhoneNumber;
    }
}
