package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.Random;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.os.Bundle;

public class BarChartActivity extends Activity {
	private Random random;// ���ڲ��������
	private BarChart chart;
	private BarData data;
	private BarDataSet dataSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		init_chart();

	}

	private void init_chart() {
		// ������ʾͼ��
		chart = new BarChart(this);
		setContentView(chart);
		/** ͼ��������� */
		ArrayList<BarEntry> entries = new ArrayList<>();// ��ʾ��Ŀ
		ArrayList<String> xVals = new ArrayList<String>();// �������ǩ
		random = new Random();// �����
		for (int i = 0; i < 12; i++) {
			float profit = random.nextFloat() * 1000;
			// entries.add(BarEntry(float val,int positon);
			entries.add(new BarEntry(profit, i));
			xVals.add((i + 1) + "��");
		}

		dataSet = new BarDataSet(entries, "��˾�����󱨱�");
		dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		data = new BarData(xVals, dataSet);

		chart.setData(data);
		// ����Y�����϶���animateY(int time);
		chart.animateY(3000);
		// ͼ������
		chart.setDescription("��˾ǰ������񱨱�(��λ����Ԫ)");

	}
}
