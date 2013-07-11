package bupt.sse.monitoringsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

import monitoringsystem.second.WeatherChartActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import bupt.sse.monitoringsystem.msg.util.Weather;

public class QueryWeather extends Activity {

	private TextView temperatureNow, publishTime, radiationNow, humidityNow,
			winddirectionNow, windspeedNow, airpressureNow;

	private Button more;
	private Temperature t;
    private long n=0;
	// private Button refreshButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_weather);
		t=new Temperature();
        t.loadWeatherData(n);
        findView();
        showData();
        bindListener();
	}

	private void findView() {
		more = (Button) findViewById(R.id.weather_more);
		temperatureNow = (TextView) findViewById(R.id.temperatureNow);
		publishTime = (TextView) findViewById(R.id.publishTime);
		radiationNow = (TextView) findViewById(R.id.radiationNow);
		humidityNow = (TextView) findViewById(R.id.humidityNow);
		winddirectionNow = (TextView) findViewById(R.id.airdirectionNow);
		windspeedNow = (TextView) findViewById(R.id.windspeedNow);
		airpressureNow = (TextView) findViewById(R.id.airpressureNow);
	}
	
	private void showData(){
		double value[];
		value=t.getTemperature();
		int nowIndex=value.length-1;
		temperatureNow.setText((int)value[nowIndex]+"��");
		publishTime.setText(t.getLatelyTime()+"����");
		value=t.getRadiation();
		radiationNow.setText("��������"+value[nowIndex]);
		value=t.getHumidity();
		humidityNow.setText("ʪ�ȣ�"+value[nowIndex]);
		value=t.getWindDirection();
		winddirectionNow.setText("����"+value[nowIndex]);
		value=t.getWindSpeed();
		windspeedNow.setText("���٣�"+value[nowIndex]);
		value=t.getAirPressure();
		airpressureNow.setText("��ѹ��"+value[nowIndex]);
		
	}
	private void bindListener(){
		more.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t=new Intent();
				t.setClass(getApplicationContext(), WeatherChartActivity.class);
				startActivity(t);
				
			}
		});
	}

}
