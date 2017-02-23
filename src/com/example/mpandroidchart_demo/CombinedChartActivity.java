package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class CombinedChartActivity extends Activity {
	private CombinedChart combinedChart;
	private CombinedData CombinedData = null;
	private ArrayList<String> xVals = new ArrayList<String>();
	private CombinedData combinedData = null;
	private Random random;
	private LineData lineData = null;
	private BarData barData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		combinedChart = new CombinedChart(this);
		setContentView(combinedChart);

		initChart();
		// loadChartData_simple(50);
		loadChartData(60);
	}

	private void loadChartData_simple(int count) {
		for (int i = 0; i < count; i++) {
			xVals.add(i + "");
		}
		combinedData = new CombinedData(xVals);
		lineData = generateLineData(count);
		combinedData.setData(lineData);

		barData = generateBarData(count);
		combinedData.setData(barData); // 增加柱形图

		combinedChart.setData(combinedData);// 当前屏幕会显示所有的数据
		combinedChart.invalidate();
	}

	private LineData generateLineData(int count) {
		ArrayList<Entry> yVals = new ArrayList<Entry>();
		random = new Random();
		for (int i = 0; i < count; i++) {
			float profix = random.nextFloat() + 2;
			yVals.add(new Entry(profix, i));
		}

		LineDataSet dataSet = new LineDataSet(yVals, "公司年度利润");
		dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		LineData data = new LineData(xVals, dataSet);
		return data;
	}

	private void initChart() {
		combinedChart.setDescription("");
		combinedChart.setDrawGridBackground(true);
		combinedChart.setBackgroundColor(Color.WHITE);
		combinedChart.setGridBackgroundColor(Color.GRAY);
		combinedChart.setScaleYEnabled(false);
		combinedChart.setPinchZoom(true);
		combinedChart.setDrawValueAboveBar(false);
		combinedChart.setNoDataText("加载中...");
		combinedChart.setAutoScaleMinMaxEnabled(true);
		combinedChart.setDragEnabled(true);
		combinedChart.setDrawOrder(
				new CombinedChart.DrawOrder[] { CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.BAR });

		XAxis xAxis = combinedChart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(true);
		xAxis.setGridColor(Color.BLACK);
		xAxis.setTextColor(Color.BLUE);
		xAxis.setSpaceBetweenLabels(4);

		YAxis leftAxis = combinedChart.getAxisLeft();
		leftAxis.setLabelCount(4, false);
		leftAxis.setDrawGridLines(true);
		leftAxis.setDrawAxisLine(true);
		leftAxis.setGridColor(Color.YELLOW);
		leftAxis.setTextColor(Color.RED);

		YAxis rightAxis = combinedChart.getAxisRight();
		rightAxis.setEnabled(false);

		// combinedChart.setData(CombinedData);// 设置数据

		Legend legend = combinedChart.getLegend();
		legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
		legend.setTextColor(Color.WHITE);

	}

	private void loadChartData(int count) {
		for (int i = 0; i < count; i++) {
			xVals.add(i + "");
		}

		combinedData = new CombinedData(xVals);

		random = new Random();
		/* ma5 */
		ArrayList<Entry> ma5Entries = new ArrayList<Entry>();
		for (int index = 0; index < count; index++) {
			float profix = random.nextFloat() + 1;
			ma5Entries.add(new Entry(profix, index));
		}
		/* ma10 */
		ArrayList<Entry> ma10Entries = new ArrayList<Entry>();
		for (int index = 0; index < count; index++) {
			float profix = random.nextFloat() + 2;
			ma10Entries.add(new Entry(profix, index));
		}
		/* ma20 */
		ArrayList<Entry> ma20Entries = new ArrayList<Entry>();
		for (int index = 0; index < count; index++) {
			float profix = random.nextFloat() + 2.4f;
			ma20Entries.add(new Entry(profix, index));
		}

		lineData = generateMultiLineData(generateLineDataSet(ma5Entries, Color.GREEN, "ma5"),
				generateLineDataSet(ma10Entries, Color.RED, "ma10"),
				generateLineDataSet(ma20Entries, Color.BLUE, "ma20"));

		combinedData.setData(lineData);// 增加LINE图

		barData = generateBarData(count);
		combinedData.setData(barData); // 增加柱形图

		combinedChart.setData(combinedData);// 当前屏幕会显示所有的数据
		combinedChart.invalidate();
	}

	private BarData generateBarData(int count) {

		ArrayList<BarEntry> entries = new ArrayList<>();// 显示条目
		// ArrayList<String> xVals = new ArrayList<String>();// 横坐标标签
		random = new Random();// 随机数
		for (int i = 0; i < count; i++) {
			float profit = random.nextFloat() / 10;
			// entries.add(BarEntry(float val,int positon);
			entries.add(new BarEntry(profit, i));
			// xVals.add((i + 1) + "日");
		}

		BarDataSet dataSet = new BarDataSet(entries, "公司年利润报表");
		dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		BarData data = new BarData(xVals, dataSet);
		return data;
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
}
