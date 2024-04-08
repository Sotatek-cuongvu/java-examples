package org.example.models;


public class Address {
    private String province;
    private String district;
    private String village;


    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getVillage() {
        return village;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s", this.getVillage(), this.getDistrict(), this.getProvince()
        );
    }

    public Address(String province, String district, String village) {
        this.province = province;
        this.district = district;
        this.village = village;
    }
}
