package com.android.washingnose;

public class Device {
    String macAddress;
    String nameDevice;



    public Device(String macAddress , String nameDevice){
        this.macAddress = macAddress;
        this.nameDevice = nameDevice;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.nameDevice = macAddress;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }
}
