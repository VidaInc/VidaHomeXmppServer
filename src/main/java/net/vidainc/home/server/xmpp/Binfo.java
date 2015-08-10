package net.vidainc.home.server.xmpp;

import org.json.JSONArray;
import org.json.JSONObject;

public class Binfo {
	private String TAG_BLAdd = "bluetooth_address";
	private String TAG_BLName = "bluetooth_name";
	private String TAG_Dist = "distance";
	private String TAG_RSSI = "rssi";
	private String TAG_uuids = "identifier_uuids";
	private String BLAdd;
	private int dist;
	private int rssi;
	private JSONArray uuids;
	private String BLName;

	public Binfo(JSONObject job) {
		this.BLAdd = job.getString(TAG_BLAdd);
		this.BLName = job.getString(TAG_BLName);
		this.dist = job.getInt(TAG_Dist);
		this.rssi = job.getInt(TAG_RSSI);
		this.uuids = job.getJSONArray(TAG_uuids);
	}

	public String getBLAdd() {
		return BLAdd;
	}
	
	public String getBLName() {
		return BLName;
	}

	public int getDist() {
		return dist;
	}

	public int getRssi() {
		return rssi;
	}

	public JSONArray getUuids() {
		return uuids;
	}


}
