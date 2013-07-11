package bupt.sse.monitoringsystem.msg;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import android.util.Log;
import bupt.sse.monitoringsystem.msg.util.Alarm;
import bupt.sse.monitoringsystem.msg.util.Confirm;
import bupt.sse.monitoringsystem.msg.util.DeviceDetail;
import bupt.sse.monitoringsystem.msg.util.EquArray;
import bupt.sse.monitoringsystem.msg.util.Order;
import bupt.sse.monitoringsystem.msg.util.OrderDetail;
import bupt.sse.monitoringsystem.msg.util.Station;
import bupt.sse.monitoringsystem.msg.util.Unit;
import bupt.sse.monitoringsystem.msg.util.User;
import bupt.sse.monitoringsystem.msg.util.Weather;
import bupt.sse.monitoringsystem.msg.util.net.DoHttpTransmission;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GetMsg {
	// private static final String urlBase =
	// "http://10.0.2.2:8080/DetectionServer";
	private static final String urlBase = "http://59.64.167.57:8080/DetectionServer";
	private static final Gson gson = new GsonBuilder().setDateFormat(
			"yyyy-MM-dd HH:mm:ss").create();
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final DecimalFormat format = new DecimalFormat(".00");

	// 验证用户登录，成功返回null，否则返回失败的原因
	public static String checkUser(User user) {
		String url = urlBase + "/user";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", user.getUserName());
		map.put("password", user.getPassword());
		String result = "";
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			Confirm confirm = gson.fromJson(response, Confirm.class);
			if (confirm == null) {
				Log.e("check user", "server error");
				result = response;
			} else if (confirm.isSuccess()) {
				result = null;
			} else {
				result = confirm.getReason() + "";
				Log.e("check user", result);
			}
		} catch (Exception e) {
			result = e.getMessage() + "";
			Log.e("check user", result);
			e.printStackTrace();
		}
		return result;
	}

	// time==0返回当天数据
	// time!=0返回指定日起数据
	public static List<Weather> getWeathers(long time) {
		String url = urlBase + "/weather";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("milltime", time + "");
		List<Weather> weathers = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			Type type = new TypeToken<List<Weather>>() {
			}.getType();
			weathers = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getWeathers", e.getMessage() + "");
			e.printStackTrace();
		}
		return weathers == null || weathers.size() == 0 ? null : weathers;
	}

	// 返回最近的天气数据
	public static Weather getWeather() {
		String url = urlBase + "/weather";
		List<Weather> weathers = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					null);
			Type type = new TypeToken<List<Weather>>() {
			}.getType();
			weathers = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getWeathers", e.getMessage() + "");
			e.printStackTrace();
		}
		return weathers == null || weathers.size() == 0 ? null : weathers
				.get(0);
	}

	// 返回所有未解决的告警信息
	public static List<Alarm> getAlarms() {
		String url = urlBase + "/alarm";
		List<Alarm> alarms = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					null);
			Type type = new TypeToken<List<Alarm>>() {
			}.getType();
			alarms = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getAlarms", e.getMessage() + "");
			e.printStackTrace();
		}
		return alarms == null || alarms.size() == 0 ? null : alarms;
	}

	// 返回设备详细信息
	public static DeviceDetail getDeviceDetail(String deviceno) {
		String url = urlBase + "/devdt";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dn", deviceno);
		List<DeviceDetail> details = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			// System.out.println(response);
			Type type = new TypeToken<List<DeviceDetail>>() {
			}.getType();
			details = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getWeathers", e.getMessage() + "");
			e.printStackTrace();
		}
		return details == null || details.size() == 0 ? null : details.get(0);
	}

	// 返回所有实时工单信息
	public static List<Order> getOrders(String username) {
		String url = urlBase + "/order";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", username + "");
		List<Order> orders = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			Type type = new TypeToken<List<Order>>() {
			}.getType();
			orders = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getOrders", e.getMessage() + "");
			e.printStackTrace();
		}
		return orders == null || orders.size() == 0 ? null : orders;
	}

	// 返回所有实时工单详细信息
	public static OrderDetail getOrderDetail(String instanceName,
			String tenantName) {
		String url = urlBase + "/orderdetail";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("instanceName", instanceName + "");
		map.put("tenantName", tenantName + "");
		List<OrderDetail> orders = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			// System.out.println(response);
			Type type = new TypeToken<List<OrderDetail>>() {
			}.getType();
			orders = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getOrders", e.getMessage() + "");
			e.printStackTrace();
		}
		return orders == null || orders.size() == 0 ? null : orders.get(0);
	}

	// 参数为1表示过去一周
	// 参数为0表示当前

	// 返回当日阵列电量信息
	public static List<EquArray> getArrays(String dn, int flag) {
		String url = urlBase + "/array";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "" + flag);
		map.put("dn", dn + "");
		List<EquArray> arrays = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			System.out.println(response);
			Type type = new TypeToken<List<EquArray>>() {
			}.getType();
			arrays = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getArrays", e.getMessage() + "");
			e.printStackTrace();
		}
		return arrays == null || arrays.size() == 0 ? null : arrays;
	}

	// 返回所有单元电量信息
	public static List<Unit> getUnits(String dn, int flag) {
		String url = urlBase + "/unit";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "" + flag);
		map.put("dn", dn + "");
		List<Unit> units = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			Type type = new TypeToken<List<Unit>>() {
			}.getType();
			units = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getUnits", e.getMessage() + "");
			e.printStackTrace();
		}
		return units == null || units.size() == 0 ? null : units;
	}

	// 返回所有电站电量信息
	public static List<Station> getStations(String dn, int flag) {
		String url = urlBase + "/station";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "" + flag);
		map.put("dn", dn + "");
		List<Station> stations = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			Type type = new TypeToken<List<Station>>() {
			}.getType();
			stations = gson.fromJson(response, type);
		} catch (Exception e) {
			Log.e("getStations", e.getMessage() + "");
			e.printStackTrace();
		}
		return stations == null || stations.size() == 0 ? null : stations;
	}

	public static String receipt(String instanceName, String tenantName,
			String user, String info) {
		String url = urlBase + "/receipt";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("instanceName", instanceName + "");
		map.put("tenantName", tenantName + "");
		map.put("user", user + "");
		map.put("info", info + "");
		String result = "";
		Confirm confirm = null;
		try {
			String response = DoHttpTransmission.doHttpPostTransmission(url,
					map);
			confirm = gson.fromJson(response, Confirm.class);
			if (confirm.isSuccess()) {
				result = null;
			} else {
				result = confirm.getReason();
			}
		} catch (Exception e) {
			result = e.getMessage() + "";
			Log.e("getStations", result);
			e.printStackTrace();
		}
		return result;
	}
}
