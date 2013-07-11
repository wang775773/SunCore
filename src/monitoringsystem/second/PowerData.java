package monitoringsystem.second;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import bupt.sse.monitoringsystem.entity.PowerInfo;
import bupt.sse.monitoringsystem.msg.GetMsg;
import bupt.sse.monitoringsystem.msg.util.EquArray;
import bupt.sse.monitoringsystem.msg.util.Station;
import bupt.sse.monitoringsystem.msg.util.Unit;

public class PowerData {
	private List<PowerInfo> myPowerData = new ArrayList<PowerInfo>();
	private String powerType, powerid;
	private DecimalFormat df = new DecimalFormat(".00");

	private int selectType;
	private double[] value;
	private String[] time = { "8:00", "9:00", "10:00", "11:00", "12:00",
			"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
			"20:00" };

	public List<PowerInfo> getMyPowerData() {
		return myPowerData;
	}

	public double[] getValue() {
		return value;
	}

	public String[] getTime() {
		System.out.println(" ," + time[0] + " ," + time[1]);
		return time;
	}
   public PowerData(int selectType){
	   this.selectType=selectType;
   }
	
	public void loadArrayPower(String powerid) {
		int i = 0;
		List<EquArray> arrays= GetMsg.getArrays(powerid,this.selectType);
		Log.v("!!",selectType+", arrays.size:"+arrays.size());
		value = new double[arrays.size()];
		time = new String[arrays.size()];
		for (EquArray array : arrays) {

			value[i] = array.getCapacity();
			value[i] = ((int) Math.round(value[i] * 100)) / 100.00;
			if (this.selectType == 0) {
				time[i] = array.getDate().getHours() + ":00";
			}
			if (this.selectType == 1) {
				time[i] = array.getDate().getMonth()+"月"+array.getDate().getDay()+"日";
			}
			i++;
		}
	}

	public void loadUnitPower(String powerid) {
		int i = 0;
		List<Unit> units = GetMsg.getUnits(powerid,this.selectType);
		Log.v("!!",selectType+", units.size:"+units.size());
		value = new double[units.size()];
		time = new String[units.size()];
		for (Unit unit : units) {
			value[i] = unit.getCapacity();
			value[i] = ((int) Math.round(value[i] * 100)) / 100.00;
			if (this.selectType == 0) {
				time[i] = unit.getDate().getHours() + ":00";
			}
			if (this.selectType == 1) {

				time[i] = unit.getDate().getMonth()+"月"+unit.getDate().getDay()+"日";
			}
			i++;

		}
	}

	public void loadStationPower() {

		int i = 0;
		List<Station> stations = GetMsg.getStations("glm", this.selectType);
		value = new double[stations.size()];
		time = new String[stations.size()];
		for (Station station : stations) {
			value[i] = station.getCapacity();
			value[i] = ((int) Math.round(value[i] * 100)) / 100.00;
			if (this.selectType == 0) {
				time[i] = station.getDate().getHours() + ":00";
			}
			if (this.selectType == 1) {

				time[i] = station.getDate().getMonth()+"月"+station.getDate().getDay()+"日";
			}
			i++;

		}
	}
}
