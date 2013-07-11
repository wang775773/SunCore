package monitoringsystem.second;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import bupt.sse.monitoringsystem.R;

public class PowerChartActivity extends Activity {

	private EditText powerSearch;
	private ImageView power_station, power_unit, power_array;
	private LinearLayout powerChartContainer;
	private ImageView search;
	private int selectType;
	private double powerData[];
	private String time[], powerID;
	private GraphicalView chartView = null;
	private String xTitle = "ʱ��", yTitle, chartTitle, lineName;
	private PowerData p;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.power_capacity_chart);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		selectType = bundle.getInt("type");
		System.out.println("selectType:" + selectType);
		Log.v("!!", selectType + "");
		p = new PowerData(selectType);
		findView();
		defaultView();
		bindListener();
	}

	public void findView() {
		powerSearch = (EditText) findViewById(R.id.powerSearch);
		power_station = (ImageView) findViewById(R.id.power_station);
		power_unit = (ImageView) findViewById(R.id.power_unit);
		power_array = (ImageView) findViewById(R.id.power_array);
		powerChartContainer = (LinearLayout) findViewById(R.id.powerChartContainer);

	}

	public void defaultView() {
		p.loadStationPower();
		Log.v("@@","power");
		powerData = p.getValue();
		Log.v("@@","powerData"+powerData.length);
		time = p.getTime();
		yTitle = " ������";
		if (selectType == 0) {
			xTitle = "ʱ��";
			lineName = " ��վ����ʵʱ����";
			chartTitle = " ��վ��һ�ܷ�������״ͼ";
		} else {
			xTitle = "����";
			lineName = " ��վ��һ�ܵķ�����";
			chartTitle = " ��վ��һ�ܷ�������״ͼ";
		}

		draw();

	}

	public void bindListener() {

		power_station.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				power_station.setImageDrawable(getResources().getDrawable(
						R.drawable.station_pressed));
				power_unit.setImageDrawable(getResources().getDrawable(
						R.drawable.unit));
				power_array.setImageDrawable(getResources().getDrawable(
						R.drawable.array));
				p.loadStationPower();
				powerData = p.getValue();
				time = p.getTime();
				yTitle = " ������";
				if (selectType == 0) {
					xTitle = "ʱ��";
					lineName = " ��վ����ʵʱ����";
					chartTitle = " ��վ��һ�ܷ�������״ͼ";
				} else {
					xTitle = "����";
					lineName = " ��վ��һ�ܵķ�����";
					chartTitle = " ��վ��һ�ܷ�������״ͼ";
				}

				draw();

			}
		});
		power_unit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getSearchID();
				power_station.setImageDrawable(getResources().getDrawable(
						R.drawable.station));
				power_unit.setImageDrawable(getResources().getDrawable(
						R.drawable.unit_pressed));
				power_array.setImageDrawable(getResources().getDrawable(
						R.drawable.array));
				p.loadUnitPower(powerID);
				powerData = p.getValue();
				time = p.getTime();
				yTitle = " ������";
				if (selectType == 0) {
					xTitle = "ʱ��";
					lineName = " ��Ԫ����ʵʱ����";
					chartTitle = " ��Ԫ��һ�ܷ�������״ͼ";
				} else {
					xTitle = "����";
					lineName = " ��Ԫ��һ�ܵķ�����";
					chartTitle = " ��Ԫ��һ�ܷ�������״ͼ";
				}

				draw();

			}
		});
		power_array.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getSearchID();
				power_station.setImageDrawable(getResources().getDrawable(
						R.drawable.station));
				power_unit.setImageDrawable(getResources().getDrawable(
						R.drawable.unit));
				power_array.setImageDrawable(getResources().getDrawable(
						R.drawable.array_pressed));
				// TODO Auto-generated method stub
				p.loadArrayPower(powerID);
				powerData = p.getValue();
				time = p.getTime();
				yTitle = " ������";
				if (selectType == 0) {
					xTitle = "ʱ��";
					lineName = " ���н���ʵʱ����";
					chartTitle = " ���н�һ�ܷ�������״ͼ";
				} else {
					xTitle = "����";
					lineName = " ���н�һ�ܵķ�����";
					chartTitle = " ���н�һ�ܷ�������״ͼ";
				}

				draw();

			}
		});

	}

	public void draw() {
		if (chartView != null) {
			powerChartContainer.removeAllViews();
			Log.v("is", "dsds");
		}
		ChartDrawing chart = new ChartDrawing(xTitle, yTitle, chartTitle, time);
		chart.set_XYSeries(powerData, lineName);
		chart.set_XYMultipleSeriesRenderer_Style(chart
				.set_XYSeriesRender_Style());
		chart.getSeriesRenderer().setColor(
				getResources().getColor(R.color.chartLine));
		chart.getMultiRenderer().setAxesColor(
				getResources().getColor(R.color.chartAxesColor));
		chart.getMultiRenderer().setLabelsColor(
				getResources().getColor(R.color.chartLine));
		if (selectType == 0) {
			chartView = (GraphicalView) ChartFactory.getLineChartView(
					getBaseContext(), chart.getDataset(),
					chart.getMultiRenderer());
		} else {
			chartView = (GraphicalView) ChartFactory.getBarChartView(
					getBaseContext(), chart.getDataset(),
					chart.getMultiRenderer(), Type.DEFAULT);
		}
		chartView.setBackgroundColor(Color.TRANSPARENT);
         
		// Adding the Line Chart to the LinearLayout
		powerChartContainer.addView(chartView);
	}

	public void getSearchID() {
		powerID = powerSearch.getText().toString();
		if (powerID == "") {
			Toast.makeText(getApplicationContext(), "������Ҫ�鿴���豸ID",
					Toast.LENGTH_SHORT).show();
		}
		Log.v("@@", powerID + "");
	}

}
