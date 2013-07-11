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
 * ��android�л�����ͼ����״ͼ����ͼ��ͳ��ͼ��������achartengine������ߣ���ͨ������achartengine.jar
 * ��Ҫͨ�����ü�������
 * 1��XYSeries�������ڴ洢һ���ߵ�������Ϣ��
 * 2��XYMultipleSeriesDataset���󣺼����ݼ�������Ӷ��XYSeries������Ϊһ������ͼ�п����ж����ߡ�
 * 3��XYSeriesRenderer������Ҫ����������һ�������ķ����ɫ������ϸ֮��ġ�
 * 4��XYMultipleSeriesRenderer������Ҫ��������һ��ͼ������������xTitle,yTitle,chartName�ȵ������Եķ��
 *    ����Ӷ��XYSeriesRenderer������Ϊһ��ͼ�п����ж������ߡ�
 * ��������Щ����֮�󣬿�ͨ�� org.achartengine.ChartFactory�������ݼ�XYMultipleSeriesDataset����
 * ��XYMultipleSeriesRenderer��������ͼ����ͼ���ص�GraphicalView�У�
 * ChartFactory�ж���api��ͨ����Щapi�����������ǻ�����ͼ������״ͼ��
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
	 * ��XYSeries�����ơ�������ӵ����ݼ� XYMultipleSeriesDataset������ȥ
	 * */
	public void set_XYSeries(double[] temp, String lineName) {
		// ����һ��XYSeries�����ΪlineName�ϵ�����
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
		//������������ɫ
		this.seriesRenderer.setColor(Color.BLUE);
		this.seriesRenderer.setFillPoints(false);
		this.seriesRenderer.setPointStyle(PointStyle.TRIANGLE);
		//���������Ŀ��
		this.seriesRenderer.setLineWidth(3);
		this.seriesRenderer.setDisplayChartValues(true);
		return seriesRenderer;

	}

	public void set_XYMultipleSeriesRenderer_Style(XYSeriesRenderer renderer) {
		// ���� X �᲻��ʾ����(���������ֶ���ӵ����ֱ�ǩ)
		this.multiRenderer.setXLabels(0);
		//����Y��Ľ����
		this.multiRenderer.setYLabels(10);
		
		//����X��Ĵ��������
		this.multiRenderer.setXTitle(xTitle);
		//����Y��Ĵ��������
		this.multiRenderer.setYTitle(yTitle);
		//������״ͼ������֮��ļ��
		this.multiRenderer.setBarSpacing(0.5);
		this.multiRenderer.setMarginsColor(Color.argb(0,0xff, 0, 0));
		this.multiRenderer.setAxisTitleTextSize(16);
		this.multiRenderer.setBackgroundColor(Color.TRANSPARENT); // ���ñ���ɫ͸��
        //this.multiRenderer.setYAxisMin(0.00);
		this.multiRenderer.setApplyBackgroundColor(true); // ʹ����ɫ��Ч
		this.multiRenderer.setAxesColor(Color.GREEN);
		//this.multiRenderer.setShowGrid(true);//��ʾ����
		//this.multiRenderer.setPanEnabled(false, false);//���ò����ƶ�
		this.multiRenderer.setZoomButtonsVisible(true);
		for (int i = 0; i < xLabel.length; i++) {
			//���X���ǩ
			this.multiRenderer.addXTextLabel(i + 1, this.xLabel[i]);
		}
		
		this.multiRenderer.addSeriesRenderer(renderer);

	}

	

}
