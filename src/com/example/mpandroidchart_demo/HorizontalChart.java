package com.example.mpandroidchart_demo;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class HorizontalChart extends Activity {
	protected HorizontalBarChart mChart;
	private SeekBar mSeekBarX, mSeekBarY;
	private TextView tvX, tvY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		init_view();
		init_chart();
	}

	private void init_view() {
		setContentView(R.layout.activity_horizontal_chart);

		tvX = (TextView) findViewById(R.id.tvXMax);
		tvY = (TextView) findViewById(R.id.tvYMax);

		mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);

		mChart = (HorizontalBarChart) findViewById(R.id.chart1);

	}

	private void init_chart() {

		/*
		 * tvX = new TextView(getApplicationContext()); tvY = new
		 * TextView(getApplicationContext());
		 * 
		 * mSeekBarX = new SeekBar(getApplicationContext()); mSeekBarY = new
		 * SeekBar(getApplicationContext());
		 * 
		 * mChart = new HorizontalBarChart(getApplicationContext());
		 */
		// mChart.setOnChartValueSelectedListener(this);
		// mChart.setHighlightEnabled(false);

		mChart.setDrawBarShadow(false);

		mChart.setDrawValueAboveBar(true);

		mChart.setDescription("Ë®Æ½ÖùÐÎÍ¼");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(60);

		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);

		// draw shadows for each bar that show the maximum value
		// mChart.setDrawBarShadow(true);

		mChart.setDrawGridBackground(false);

		XAxis xl = mChart.getXAxis();
		xl.setPosition(XAxisPosition.BOTTOM);
		// xl.setTypeface(mTfLight);
		xl.setDrawAxisLine(true);
		xl.setDrawGridLines(false);
		// xl.setGranularity(10f);

		YAxis yl = mChart.getAxisLeft();
		// yl.setTypeface(mTfLight);
		yl.setDrawAxisLine(true);
		yl.setDrawGridLines(true);
		yl.setAxisMinValue(0f); // this replaces setStartAtZero(true)
		// yl.setInverted(true);

		YAxis yr = mChart.getAxisRight();
		// yr.setTypeface(mTfLight);
		yr.setDrawAxisLine(true);
		yr.setDrawGridLines(false);
		yr.setAxisMinValue(0f); // this replaces setStartAtZero(true)
		// yr.setInverted(true);

		setData(12, 50);
		// mChart.setFitBars(true);
		mChart.animateY(2500);

		// setting data
		mSeekBarY.setProgress(50);
		mSeekBarX.setProgress(12);

		// mSeekBarY.setOnSeekBarChangeListener(this);
		// mSeekBarX.setOnSeekBarChangeListener(this);

		Legend l = mChart.getLegend();
		// l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		// l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		// l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		// l.setDrawInside(false);
		l.setFormSize(8f);
		l.setXEntrySpace(4f);
	}

	private void setData(int count, int range) {
		float barWidth = 9f;
		float spaceForBar = 10f;
		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
		ArrayList<String> x = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			x.add(i + "");
			int val = (int) (Math.random() * range);
			yVals1.add(new BarEntry(val, i));
		}

		BarDataSet set1;

		set1 = new BarDataSet(yVals1, "DataSet 1");
		set1.setColors(ColorTemplate.COLORFUL_COLORS);

		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(x, dataSets);
		data.setValueTextSize(10f);
		mChart.setData(data);
	}
}