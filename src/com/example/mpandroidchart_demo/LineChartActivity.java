package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.Random;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.os.Bundle;

public class LineChartActivity extends Activity {
	private LineChart chart;
	private LineData data;
	private ArrayList<String> xVals;
	private LineDataSet dataSet;
	private ArrayList<Entry> yVals;
	private Random random;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		init_chart();			
	}

	private void init_chart() {
		chart = new LineChart(this);
		setContentView(chart);
		xVals = new ArrayList<>();
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
		
		chart.setData(data);
		chart.setDescription("公司年度利润");
		chart.animateY(3000);
		//chart.saveToGallery("mychart.jpg", 100); // 保存图表，85 is the quality of the image
	}
}
