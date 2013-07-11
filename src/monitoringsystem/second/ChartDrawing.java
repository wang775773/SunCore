package monitoringsystem.second;

import java.util.Arrays;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.annotation.SuppressLint;
import android.graphics.Color;

@SuppressLint("NewApi")
/**
 * 在android中画折线图、柱状图、饼图等统计图，可以用achartengine这个工具，可通过下载achartengine.jar
 * 主要通过设置几个对象
 * 1、XYSeries对象：用于存储一条线的数据信息；
 * 2、XYMultipleSeriesDataset对象：即数据集，可添加多个XYSeries对象，因为一个折线图中可能有多条线。
 * 3、XYSeriesRenderer对象：主要是用来设置一条线条的风格，颜色啊，粗细之类的。
 * 4、XYMultipleSeriesRenderer对象：主要用来定义一个图的整体风格，设置xTitle,yTitle,chartName等等整体性的风格，
 *    可添加多个XYSeriesRenderer对象，因为一个图中可以有多条折线。
 * 设置完那些对象之后，可通过 org.achartengine.ChartFactory调用数据集XYMultipleSeriesDataset对象
 * 与XYMultipleSeriesRenderer对象来画图并将图加载到GraphicalView中，
 * ChartFactory有多种api，通过这些api调用来决定是画折线图还是柱状图。
 * */
public class ChartDrawing {

	private String xTitle, yTitle, chartTitle;
	private String xLabel[];
	private XYMultipleSeriesDataset dataset;
	private XYMultipleSeriesRenderer multiRenderer;
	private XYSeriesRenderer seriesRenderer;


	public XYSeriesRenderer getSeriesRenderer() {
		return seriesRenderer;
	}

	public XYMultipleSeriesRenderer getMultiRenderer() {
		return multiRenderer;
	}

	public XYMultipleSeriesDataset getDataset() {
		return dataset;
	}
	

	public ChartDrawing(String xTitle, String yTitle, String chartTitle,
			String xLabel[]) {

		this.xTitle = xTitle;
		this.yTitle = yTitle;
		this.xLabel = Arrays.copyOf(xLabel, xLabel.length);
		this.chartTitle = chartTitle;
		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		this.multiRenderer = new XYMultipleSeriesRenderer();
		// Creating a dataset to hold each series
		this.dataset = new XYMultipleSeriesDataset();
	}
	
	
	/**
	 * 给XYSeries对象复制。并将其加到数据集 XYMultipleSeriesDataset对象中去
	 * */
	public void set_XYSeries(double[] temp, String lineName) {
		// 创建一个XYSeries存放线为lineName上的数据
		XYSeries oneSeries = new XYSeries(lineName);
		// Adding data to Series
		for (int i = 0; i < temp.length; i++) {
			oneSeries.add(i + 1, temp[i]);

		}
		// Adding Series to the dataset
		this.dataset.addSeries(oneSeries);
	}
   
	
	public XYSeriesRenderer set_XYSeriesRender_Style() {
		this.seriesRenderer = new XYSeriesRenderer();
		//设置线条的颜色
		this.seriesRenderer.setColor(Color.BLUE);
		this.seriesRenderer.setFillPoints(false);
		this.seriesRenderer.setPointStyle(PointStyle.TRIANGLE);
		//设置线条的宽度
		this.seriesRenderer.setLineWidth(3);
		this.seriesRenderer.setDisplayChartValues(true);
		return seriesRenderer;

	}

	public void set_XYMultipleSeriesRenderer_Style(XYSeriesRenderer renderer) {
		// 设置 X 轴不显示数字(改用我们手动添加的文字标签)
		this.multiRenderer.setXLabels(0);
		//设置Y轴的结点数
		this.multiRenderer.setYLabels(10);
		
		//设置X轴的代表的名称
		this.multiRenderer.setXTitle(xTitle);
		//设置Y轴的代表的名称
		this.multiRenderer.setYTitle(yTitle);
		//设置柱状图中柱子之间的间隔
		this.multiRenderer.setBarSpacing(0.5);
		this.multiRenderer.setMarginsColor(Color.argb(0,0xff, 0, 0));
		this.multiRenderer.setAxisTitleTextSize(16);
		this.multiRenderer.setBackgroundColor(Color.TRANSPARENT); // 设置背景色透明
        //this.multiRenderer.setYAxisMin(0.00);
		this.multiRenderer.setApplyBackgroundColor(true); // 使背景色生效
		this.multiRenderer.setAxesColor(Color.GREEN);
		//this.multiRenderer.setShowGrid(true);//显示网线
		//this.multiRenderer.setPanEnabled(false, false);//设置不能移动
		this.multiRenderer.setZoomButtonsVisible(true);
		for (int i = 0; i < xLabel.length; i++) {
			//添加X轴便签
			this.multiRenderer.addXTextLabel(i + 1, this.xLabel[i]);
		}
		
		this.multiRenderer.addSeriesRenderer(renderer);

	}

	

}
