package com.motomami.model;

public class VehicleDto {
    private String idVehicle;
    private String idVehicleExternal;
    private String numberPlate;
    private String vehicleType;
    private String brand;
    private String model;
    private String color;
    private String serialNumber;

    public VehicleDto() {
    }

    public VehicleDto(String idVehicle, String idVehicleExternal, String numberPlate, String vehicleType, String brand, String model, String color, String serialNumber) {
        this.idVehicle = idVehicle;
        this.idVehicleExternal = idVehicleExternal;
        this.numberPlate = numberPlate;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.serialNumber = serialNumber;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getIdVehicleExternal() {
        return idVehicleExternal;
    }

    public void setIdVehicleExternal(String idVehicleExternal) {
        this.idVehicleExternal = idVehicleExternal;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
