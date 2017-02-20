package com.example.mpandroidchart_demo;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class RadarChartActivity extends Activity {
	private RadarChart radarchart;
	public ArrayList<String> x = new ArrayList<String>();
	public ArrayList<Entry> y = new ArrayList<Entry>();
	private RadarData radarData = null;
	private ArrayList<RadarDataSet> radarDataSets = new ArrayList<RadarDataSet>();;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		radarchart = new RadarChart(this);
		setContentView(radarchart);
		RadarData resultData = getRadarData(40, 100);
		showChart();
	}

	/**
	 * ������ʽ
	 */
	private void showChart() {

		radarchart.setDescription("�з��յ�����");// ��������
		radarchart.setNoDataTextDescription("����Ҫ����");// û������ʾ
		radarchart.setBackgroundColor(Color.YELLOW);// ������ɫ
		radarchart.setData(radarData);// ��������
		Legend legend = radarchart.getLegend();// ���ñ���ͼƬ��ʾ��������һ��Y��value
		legend.setForm(Legend.LegendForm.CIRCLE);// ��ʽ
		legend.setFormSize(6f);// ����
		legend.setTextColor(Color.WHITE);// ������ɫ
		radarchart.animateY(2000);// X��Ķ���

	}

	/**
	 * gv ��ʼ������ count ��ʾ����������range��ʾ����yֵ���ɵķ�Χ
	 */
	private RadarData getRadarData(int count, int range) {
		for (int i = 0; i < count; i++) { // X����ʾ������
			x.add(i + "");
		}
		for (int i = 0; i < count; i++) {// y�������
			float result = (float) (Math.random() * range) + 3;
			y.add(new Entry(result, i));
		}
		RadarDataSet radarDataSet = new RadarDataSet(y, "�״�ͼ");// y�����ݼ���
		radarDataSet.setLineWidth(1f);// �߿�
		radarDataSet.setColor(Color.RED);// ��ʵ��ɫ
		radarDataSet.setHighLightColor(Color.WHITE);// �߶��ߵ���ɫ
		// radarDataSets.add(radarDataSet);
		radarData = new RadarData(x, radarDataSet);
		return radarData;
	}
}
