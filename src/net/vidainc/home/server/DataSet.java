package net.vidainc.home.server;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;

/*[{
 * "bluetooth_address":"B4:99:4C:89:72:9E",
 * "bluetooth_name":"ABLight",
 * "distance":5.84321999791504,
 * "service_uuid":-1,
 * "rssi":-84,
 * "identifier_uuids":["e2c56db5-dffb-48d2-b060-d0f5a71096e0","1234","15"]},
 * {
 * "bluetooth_address":"D0:FF:50:67:7C:4A",
 * "bluetooth_name":"ABLight",
 * "distance":6.622300400957262,"service_uuid":-1,"rssi":-82,"identifier_uuids":["e2c56db5-dffb-48d2-b060-d0f5a71096e4","1234","16"]},{"bluetooth_address":"B4:99:4C:89:70:53","bluetooth_name":"ABLight","distance":1.7663291263286853,"service_uuid":-1,"rssi":-60,"identifier_uuids":["e2c56db5-dffb-48d2-b060-d0f5a71096e0","1234","14"]}]

*
*/
public class DataSet {
	private JSONArray json;
	private ArrayList<Binfo> beaconList = new ArrayList<Binfo>();
	private int len;
	
	
	
	public int getLen() {
		return len;
	}


	//constructor
	public DataSet(String str) throws IOException{
		this.json = new JSONArray(str);
		this.len = json.length();
		parsing();
		
	}
	
	
	//methods
	private void parsing(){
		for (int i=0; i<len;i++){
			beaconList.add(new Binfo(json.getJSONObject(i)));
		}
	}
	
	public Binfo getBeaconByMinor(int minor){
		for(int i=0;i<len;i++){
			if(this.beaconList.get(i).getUuids().getInt(2)==minor){
				return this.beaconList.get(i);
			}
		}
		return null;
	}
	
	public Binfo getBeaconByMajor(int major){
		for(int i=0;i<len;i++){
			if(this.beaconList.get(i).getUuids().getInt(1)==major){
				return this.beaconList.get(i);
			}
		}
		return null;
	}
	
	
}
