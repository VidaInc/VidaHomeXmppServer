package net.vidainc.home.server.xmpp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by Aaron on 12/07/2015.
 */
public class BeaconData {
    public static final String BLUETOOTH_ADDRESS_KEY = "bluetooth_address";
    public static final String BLUETOOTH_NAME_KEY = "bluetooth_name";
    public static final String DISTANCE_KEY = "distance";
    public static final String SERVICE_UUID_KEY = "service_uuid";
    public static final String RSSI_KEY = "rssi";
    public static final String IDENTIFIERS_KEY = "identifier_uuids";

    private String mBluetoothAddress;
    private String mBluetoothName;
    private double mDistance;
    private int mServiceUuid;
    private int mRssi;
    private UUID mIdentifier1;
    private UUID mIdentifier2;
    private UUID mIdentifier3;

    public String getBluetoothAddress() {
        return mBluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        mBluetoothAddress = bluetoothAddress;
    }

    public String getBluetoothName() {
        return mBluetoothName;
    }

    public void setBluetoothName(String bluetoothName) {
        mBluetoothName = bluetoothName;
    }

    public double getDistance() {
        return mDistance;
    }

    public void setDistance(double distance) {
        mDistance = distance;
    }

    public int getServiceUuid() {
        return mServiceUuid;
    }

    public void setServiceUuid(int serviceUuid) {
        mServiceUuid = serviceUuid;
    }

    public int getRssi() {
        return mRssi;
    }

    public void setRssi(int rssi) {
        mRssi = rssi;
    }

    public UUID getIdentifier1() {
        return mIdentifier1;
    }

    public void setIdentifier1(UUID identifier1) {
        mIdentifier1 = identifier1;
    }

    public UUID getIdentifier2() {
        return mIdentifier2;
    }

    public void setIdentifier2(UUID identifier2) {
        mIdentifier2 = identifier2;
    }

    public UUID getIdentifier3() {
        return mIdentifier3;
    }

    public void setIdentifier3(UUID identifier3) {
        mIdentifier3 = identifier3;
    }

    public List<UUID> getIdentifiers() {
        List<UUID> identifiers = new ArrayList<>(3);
        identifiers.add(mIdentifier1);
        identifiers.add(mIdentifier2);
        identifiers.add(mIdentifier3);
        return identifiers;
    }

    public void setIdentifiers(List<UUID> identifiers) {
        mIdentifier1 = identifiers.get(0);
        mIdentifier2 = identifiers.get(1);
        mIdentifier3 = identifiers.get(2);
    }

    public String toJsonString() throws JSONException {
        JSONObject beaconData = getJSONObject(mBluetoothAddress, mBluetoothName, mDistance,
                mServiceUuid, mRssi, mIdentifier1, mIdentifier2, mIdentifier3);
        return beaconData.toString();
    }

    private static JSONObject getJSONObject(String bluetoothAddress, String bluetoothName,
                                            double distance, int serviceUuid, int rssi, UUID identifier1,
                                            UUID identifier2, UUID identifier3) throws JSONException {
        JSONObject beaconData = new JSONObject();
        beaconData.put(BLUETOOTH_ADDRESS_KEY, bluetoothAddress);
        beaconData.put(BLUETOOTH_NAME_KEY, bluetoothName);
        beaconData.put(DISTANCE_KEY, distance);
        beaconData.put(SERVICE_UUID_KEY, serviceUuid);
        beaconData.put(RSSI_KEY, rssi);
        JSONArray identifiers = new JSONArray();
        identifiers.put(identifier1.toString());
        identifiers.put(identifier2.toString());
        identifiers.put(identifier3.toString());
        beaconData.put(IDENTIFIERS_KEY, identifiers);
        return beaconData;
    }

    public static String toJsonString(List<BeaconData> beaconDataList) throws JSONException {
        JSONArray beaconDataJson = new JSONArray();
        for (BeaconData beaconData : beaconDataList) {
            beaconDataJson.put(getJSONObject(beaconData.mBluetoothAddress, beaconData.mBluetoothName,
                    beaconData.mDistance, beaconData.mServiceUuid, beaconData.mRssi, beaconData.mIdentifier1,
                    beaconData.mIdentifier2, beaconData.mIdentifier3));
        }
        return beaconDataJson.toString();
    }

    public static BeaconData fromJsonString(String jsonStr) throws JSONException {
        BeaconData beaconData = new BeaconData();
        JSONObject beaconJson = new JSONObject(jsonStr);
        beaconData.setBluetoothAddress(beaconJson.getString(BLUETOOTH_ADDRESS_KEY));
        beaconData.setBluetoothName(beaconJson.getString(BLUETOOTH_NAME_KEY));
        beaconData.setDistance(beaconJson.getDouble(DISTANCE_KEY));
        beaconData.setServiceUuid(beaconJson.getInt(SERVICE_UUID_KEY));
        beaconData.setRssi(beaconJson.getInt(RSSI_KEY));
        JSONArray identifiersJson = beaconJson.getJSONArray(IDENTIFIERS_KEY);
        List<UUID> identifiers = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            identifiers.add(UUID.fromString(identifiersJson.getString(i)));
        }
        beaconData.setIdentifiers(identifiers);
        return beaconData;
    }

    public static List<BeaconData> listFromJsonString(String jsonStr) throws JSONException {
        List<BeaconData> beaconDataList = new ArrayList<>();
        JSONArray beaconDataJson = new JSONArray(jsonStr);
        for (int i = 0; i < beaconDataJson.length(); i++) {
            beaconDataList.add(fromJsonString(beaconDataJson.getJSONObject(i).toString()));
        }
        return beaconDataList;
    }
}
