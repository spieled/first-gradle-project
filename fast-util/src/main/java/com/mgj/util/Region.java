package com.mgj.util;

/**
 * Created by yanqu on 2016/7/6.
 */
public class Region {
    private int regionCode;
    private int cityCode;
    private int provinceCode;
    private String regionName;
    private String cityName;
    private String provinceName;

    public Region(int regionCode, int cityCode, int provinceCode, String regionName, String cityName, String provinceName) {
        this.regionCode = regionCode;
        this.cityCode = cityCode;
        this.provinceCode = provinceCode;
        this.regionName = regionName;
        this.cityName = cityName;
        this.provinceName = provinceName;
    }

    public int getRegionCode() {
        return regionCode;
    }

    public int getCityCode() {
        return cityCode;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public String getFullname() {
        return provinceName+cityName+regionName;
    }

    public String getFullCityname() {
        return provinceName + cityName;
    }
}
