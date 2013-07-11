package monitoringsystem.second;

import java.util.Calendar;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import bupt.sse.monitoringsystem.R;
import bupt.sse.monitoringsystem.Temperature;

public class WeatherChartActivity extends Activity {
	private ImageView fuzhaoliang, wendu, shidu, fengsu;
	private Temperature t;
	private double weatherData[];
	private String time[];
	private GraphicalView chartView = null;
	private String xTitle = "时刻", yTitle, chartTitle, lineName;
	private ImageView searchTime;
	private LinearLayout chartContainer;
	private Calendar cd = Calendar.getInstance();
	private long n = 0;
	private int pastyear, pastmonth, pastday;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_charts);
		t = new Temperature();
		t.loadWeatherData(n);
		findView();
		bindListener();
	}

	public void findView() {
		fuzhaoliang = (ImageView) findViewById(R.id.img_fuzhaoliang);
		wendu = (ImageView) findViewById(R.id.img_wendu);
		shidu = (ImageView) findViewById(R.id.img_shidu);
		fengsu = (ImageView) findViewById(R.id.img_fengsu);
		searchTime = (ImageView) findViewById(R.id.moreTime);
		chartContainer = (LinearLayout) findViewById(R.id.weatherChartContainer);

	}

	public void bindListener() {
		fuzhaoliang.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				searchTime.setImageDrawable(getResources().getDrawable(
						R.drawable.search_time));
				fuzhaoliang.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_radiation_pressed));
				wendu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature));
				shidu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature));
				fengsu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_windspeed));
				weatherData = t.getRadiation();
				time = t.getTime();
				yTitle = " 辐照量";
				lineName = t.getLineName()+"辐照量";
				chartTitle = "辐照量变化图";
				draw();

			}
		});

		wendu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchTime.setImageDrawable(getResources().getDrawable(
						R.drawable.search_time));
				fuzhaoliang.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_radiation));
				wendu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature_pressed));
				shidu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature));
				fengsu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_windspeed));
				weatherData = t.getTemperature();
				time = t.getTime();
				yTitle = " 温度 ";
				lineName = t.getLineName()+"温度 ";

				chartTitle = " 温度变化图";
				draw();

			}
		});

		shidu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchTime.setImageDrawable(getResources().getDrawable(
						R.drawable.search_time));
				fuzhaoliang.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_radiation));
				wendu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature));
				shidu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature_pressed));
				fengsu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_windspeed));
				weatherData = t.getHumidity();
				time = t.getTime();
				yTitle = " 湿度";
				lineName = t.getLineName()+"湿度";
				Log.v("a", lineName + "," + t.getLineName());
				chartTitle = "湿度变化图";
				draw();
			}
		});

		fengsu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchTime.setImageDrawable(getResources().getDrawable(
						R.drawable.search_time));
				fuzhaoliang.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_radiation));
				wendu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature));
				shidu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature));
				fengsu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_windspeed_pressed));
				weatherData = t.getWindSpeed();
				time = t.getTime();
				yTitle = " 风速";
				lineName = t.getLineName()+"湿度";
				chartTitle = " 风速变化图";
				draw();
			}
		});

		searchTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (chartView != null) {
					chartContainer.removeAllViews();
					Log.v("is", "dsds");
				}
				fuzhaoliang.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_radiation));
				wendu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature));
				shidu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_temperature));
				fengsu.setImageDrawable(getResources().getDrawable(
						R.drawable.weather_windspeed));
				searchTime.setImageDrawable(getResources().getDrawable(
						R.drawable.search_time_pressed));
				new DatePickerDialog(WeatherChartActivity.this,
						new OnDateSetListener() {
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								Log.v("123", pastyear + " ," + pastmonth + ","
										+ pastday + "," + n);

								cd.set(Calendar.YEAR, year);
								cd.set(Calendar.MONTH, monthOfYear);
								cd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								cd.set(Calendar.HOUR_OF_DAY, 8);
								n = cd.getTimeInMillis();
								if (n != 0) {

									t.loadWeatherData(n);
								}

							}
						},

						cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd
								.get(Calendar.DAY_OF_MONTH)).show();

			}

		});
	}

	private void draw() {
		if (chartView != null) {
			chartContainer.removeAllViews();
			Log.v("is", "dsds");
		}
		ChartDrawing chart = new ChartDrawing(xTitle, yTitle, chartTitle, time);
		chart.set_XYSeries(weatherData, lineName);
		Log.v("12345", "lineName:" + lineName + " ,temperature[0]:"
				+ weatherData[0]);
		chart.set_XYMultipleSeriesRenderer_Style(chart
				.set_XYSeriesRender_Style());
		chart.getSeriesRenderer().setColor(
				getResources().getColor(R.color.chartLine));
		chart.getMultiRenderer().setAxesColor(
				getResources().getColor(R.color.chartAxesColor));
		chart.getMultiRenderer().setLabelsColor(
				getResources().getColor(R.color.chartLine));
		chartView = (GraphicalView) ChartFactory.getLineChartView(
				getBaseContext(), chart.getDataset(), chart.getMultiRenderer());
		chartView.setBackgroundColor(Color.TRANSPARENT);

		// Adding the Line Chart to the LinearLayout
		chartContainer.addView(chartView);

	}

}
