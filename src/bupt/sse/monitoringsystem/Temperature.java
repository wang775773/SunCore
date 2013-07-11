package bupt.sse.monitoringsystem;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.util.Log;
import bupt.sse.monitoringsystem.msg.GetMsg;
import bupt.sse.monitoringsystem.msg.util.Weather;

public class Temperature {

	private double[] temperature; // 温度
	private double[] humidity; // 湿度
	private double[] windSpeed; // 风速
	private double[] airPressure;// 气压
	private double[] radiation; // 辐照量
	private double[] windDirection;
	public double[] getWindDirection() {
		return windDirection;
	}

	private String[] time = { "8:00", "9:00", "10:00", "11:00", "12:00",
			"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
			"20:00" };
	private String lineName;
	private String latelyTime;

	public String getLatelyTime() {
		return latelyTime;
	}

	public String getLineName() {
		return lineName;
	}

	public double[] getTemperature() {
		return temperature;
	}

	public String[] getTime() {
		return time;
	}

	public double[] getHumidity() {
		return humidity;
	}

	public double[] getWindSpeed() {
		return windSpeed;
	}

	public double[] getAirPressure() {
		return airPressure;
	}

	public double[] getRadiation() {
		return radiation;
	}

	public void loadWeatherData(long n) {
        System.out.println("n="+n);
		List<Weather> tempData = new ArrayList<Weather>();
		// 拉取数据并存储在tempData中
		List<Weather> arrays = (List<Weather>) GetMsg.getWeathers(n);
		temperature = new double[arrays.size()];
		humidity = new double[arrays.size()];
		windSpeed = new double[arrays.size()];
		radiation = new double[arrays.size()];
		airPressure = new double[arrays.size()];
		windDirection=new double[arrays.size()];
		time = new String[arrays.size()];
		Weather a=arrays.get(arrays.size()-1);
		
		Calendar d=Calendar.getInstance();
		if(n!=0){
		
		d.setTimeInMillis(n);
 		}
		int month=d.get(Calendar.MONTH)+1;
		lineName =d.get(Calendar.YEAR)+"/"+month+"/"+d.get(Calendar.DAY_OF_MONTH)+" ";
		latelyTime=lineName+a.getDate().getHours()+":00";
		Log.v("ieee", arrays.size() + "," + temperature.length);
		int i = 0;
		for (Weather array : arrays) {
			windDirection[i]=array.getWindDirection();
			temperature[i] = array.getTemperature();
			humidity[i] = array.getHumidity();
			windSpeed[i] = array.getWindSpeed();
			radiation[i] = array.getRadiation();
			airPressure[i] = array.getAirPressure();
			temperature[i] = ((int) Math.round(temperature[i] * 100)) / 100.00;
			humidity[i] = ((int) Math.round(humidity[i] * 100)) / 100.00;
			windSpeed[i] = ((int) Math.round(windSpeed[i] * 100)) / 100.00;
			radiation[i] = ((int) Math.round(radiation[i] * 100)) / 100.00;
			airPressure[i] = ((int) Math.round(airPressure[i] * 100)) / 100.00;
			windDirection[i] = ((int) Math.round(windDirection[i] * 100)) / 100.00;
			time[i] = array.getDate().getHours() + ":00";
			Log.v("123", "i="+i+" ,lineName" + lineName + ",温度：" + temperature[i]
					+ " ，湿度：" + humidity[i] + " ，风速：" + windSpeed[i] + " ,辐照量："
					+ radiation[i]);
			i++;
           
		}

	}

}