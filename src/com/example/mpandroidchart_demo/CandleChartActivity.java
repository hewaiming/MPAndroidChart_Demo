package com.example.mpandroidchart_demo;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class CandleChartActivity extends Activity {
	private CandleStickChart candlechart;
	private CandleData candleData = null;
	public ArrayList<String> x = new ArrayList<String>();
	public ArrayList<Entry> y = new ArrayList<Entry>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		candlechart = new CandleStickChart(this);
		setContentView(candlechart);
		CandleData resultData = getCandleData(40, 100);
		showChart();
	}

	private void showChart() {
		candlechart.setBackgroundColor(Color.WHITE);

		candlechart.setDescription("蜡烛图");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		candlechart.setMaxVisibleValueCount(60);

		// scaling can now only be done on x- and y-axis separately
		candlechart.setPinchZoom(false);

		candlechart.setDrawGridBackground(false);

		XAxis xAxis = candlechart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);

		YAxis leftAxis = candlechart.getAxisLeft(); //左边Y轴数据刻度
		// leftAxis.setEnabled(false);
		leftAxis.setLabelCount(7, false);
		leftAxis.setDrawGridLines(false);
		leftAxis.setDrawAxisLine(false);

		YAxis rightAxis = candlechart.getAxisRight();//右边Y轴数据刻度
		rightAxis.setEnabled(false);
		// rightAxis.setStartAtZero(false);
		candlechart.setData(candleData);

		Legend legend = candlechart.getLegend();// 设置比例图片标示，就是那一组Y的value
		legend.setForm(Legend.LegendForm.CIRCLE);// 样式
		legend.setFormSize(6f);// 字体
		legend.setTextColor(Color.BLUE);// 设置颜色
		candlechart.animateY(2000);// X轴的动画

	}

	private CandleData getCandleData(int count, int range) {
		ArrayList<CandleEntry> yVals1 = new ArrayList<CandleEntry>();
		ArrayList<String> xVals = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			xVals.add(i + ""); // x轴数据
		}

		for (int i = 0; i < count; i++) { // y轴数据
			float mult = (range + 1);
			float val = (float) (Math.random() * 40) + mult;

			float high = (float) (Math.random() * 9) + 8f;
			float low = (float) (Math.random() * 9) + 8f;

			float open = (float) (Math.random() * 6) + 1f;
			float close = (float) (Math.random() * 6) + 1f;

			boolean even = i % 2 == 0;

			yVals1.add(new CandleEntry(i, val + high, val - low, even ? val + open : val - open,
					even ? val - close : val + close));
		}

		CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");
		set1.setAxisDependency(AxisDependency.LEFT);
		// set1.setColor(Color.rgb(80, 80, 80));
		set1.setShadowColor(Color.DKGRAY);
		set1.setShadowWidth(0.7f);
		set1.setDecreasingColor(Color.RED);
		// set1.setDecreasingPaintStyle(Paint.Style.FILL);
		set1.setIncreasingColor(Color.rgb(122, 242, 84));
		// set1.setIncreasingPaintStyle(Paint.Style.STROKE);
		// set1.setNeutralColor(Color.BLUE);
		// set1.setHighlightLineWidth(1f);
		// candleData = new CandleData();

		candleData = new CandleData(xVals);
		candleData.addDataSet(set1);

		return candleData;
	}

}
