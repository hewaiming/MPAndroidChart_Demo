package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class MultiLineChartActivity extends Activity {
	private LineChart mLineChartchart;
	private LineData data;
	private ArrayList<String> xVals;
	private LineDataSet dataSet;
	private ArrayList<Entry> yVals;
	private Random random;
	private LineData lineData=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		init_chart();	
		loadChartData(50);
	}

	private void loadChartData(int count) {
		xVals=new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			xVals.add(i + "");
		}	
		random = new Random();
		/* ma5 */
		ArrayList<Entry> ma5Entries = new ArrayList<Entry>();
		for (int index = 0; index < count; index++) {
			float profix = random.nextFloat()+1;
			ma5Entries.add(new Entry(profix, index));
		}
		/* ma10 */
		ArrayList<Entry> ma10Entries = new ArrayList<Entry>();
		for (int index = 0; index < count; index++) {
			float profix = random.nextFloat()+2;
			ma10Entries.add(new Entry(profix, index));
		}
		/* ma20 */
		ArrayList<Entry> ma20Entries = new ArrayList<Entry>();
		for (int index = 0; index < count; index++) {
			float profix = random.nextFloat()+2.4f;
			ma20Entries.add(new Entry(profix, index));
		}

		lineData = generateMultiLineData(generateLineDataSet(ma5Entries, Color.GREEN, "ma5"),
				generateLineDataSet(ma10Entries, Color.RED, "ma10"),
				generateLineDataSet(ma20Entries, Color.BLUE, "ma20"));
		
		mLineChartchart.setData(lineData);// 当前屏幕会显示所有的数据
		mLineChartchart.invalidate();
	}
	
	private LineData generateMultiLineData(LineDataSet... lineDataSets) {
		List<ILineDataSet> dataSets = new ArrayList<>();
		for (int i = 0; i < lineDataSets.length; i++) {
			dataSets.add(lineDataSets[i]);
		}
		
		LineData data = new LineData(xVals, dataSets);
		return data;
	}
	
	private LineDataSet generateLineDataSet(ArrayList<Entry> entries, int color, String label) {
		LineDataSet set = new LineDataSet(entries, label);
		set.setColor(color);
		set.setLineWidth(1f);
		set.setDrawCubic(true);// 圆滑曲线
		set.setDrawCircles(false);
		set.setDrawCircleHole(false);
		set.setDrawValues(false);
		set.setHighlightEnabled(false);
		set.setAxisDependency(YAxis.AxisDependency.LEFT);

		return set;
	}	
	
	private void init_chart() {
		mLineChartchart = new LineChart(this);
		setContentView(mLineChartchart);
		/*xVals = new ArrayList<>();
		yVals = new ArrayList<>();
		random = new Random();
		for (int i = 0; i < 12; i++) {
			float profix = random.nextFloat();
			yVals.add(new Entry(profix, i));
			xVals.add((i + 1) + "月");
		}
		
		dataSet = new LineDataSet(yVals, "公司年度利润");
		dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		data = new LineData(xVals, dataSet);	
		
		mLineChartchart.setData(data);*/
		mLineChartchart.setDescription("公司年度利润");
		mLineChartchart.animateY(3000);
		//chart.saveToGallery("mychart.jpg", 100); // 保存图表，85 is the quality of the image
	}
}
