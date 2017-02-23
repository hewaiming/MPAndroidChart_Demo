package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.Random;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class MultiBarChartActivity extends Activity {
	private Random random;// ���ڲ��������
	private BarChart multi_chart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
	
		BarData resultData = getMultiBarData(12);
		showChart(resultData);
	}

	private void showChart(BarData resultData) {
		multi_chart = new BarChart(this);
		setContentView(multi_chart);

		multi_chart.setData(resultData);
		multi_chart.getLegend().setPosition(LegendPosition.BELOW_CHART_LEFT);// ����ע���λ�������Ϸ�
		multi_chart.getLegend().setForm(LegendForm.CIRCLE);// ���������ʾСͼ�����״

		multi_chart.getXAxis().setPosition(XAxisPosition.BOTTOM);// ����X���λ��
		multi_chart.getXAxis().setDrawGridLines(false);// ����ʾ����

		multi_chart.getAxisRight().setEnabled(false);// �Ҳ಻��ʾY��
		multi_chart.getAxisLeft().setAxisMinValue(0.0f);// ����Y����ʾ��Сֵ����Ȼ0������п�϶
		multi_chart.getAxisLeft().setDrawGridLines(false);// ������Y������
	
		multi_chart.setDescription("No Deal");// ��������
		multi_chart.setDescriptionTextSize(20.f);// ������������
		multi_chart.animateXY(1000, 2000);// ���ö���

	}

	private BarData getMultiBarData(int count) {
		ArrayList<BarEntry> yVals = new ArrayList<>();// Y�᷽���һ������
		ArrayList<BarEntry> yVals2 = new ArrayList<>();// Y�᷽��ڶ�������
		ArrayList<BarEntry> yVals3 = new ArrayList<>();// Y�᷽�����������
		ArrayList<String> xVals = new ArrayList<>();// X������
		random = new Random();
		
		for (int i = 0; i < count; i++) {// �������Դ
			xVals.add((i + 1) + "��");
			yVals.add(new BarEntry(random.nextInt(10000), i));			
			yVals2.add(new BarEntry(random.nextInt(10000), i));
			if(i%2==0){
					yVals3.add(new BarEntry(500, i));
			}else{
				yVals3.add(new BarEntry(0, i));
			}		
			

		}

		BarDataSet barDataSet = new BarDataSet(yVals, "С��ÿ��֧��");
		barDataSet.setColor(Color.RED);// ���õ�һ��������ɫ
        barDataSet.setDrawValues(false); //����ʾ��ֵ
		
		BarDataSet barDataSet2 = new BarDataSet(yVals2, "С��ÿ��֧��");
		barDataSet2.setColor(Color.GREEN);// ���õڶ���������ɫ

		BarDataSet barDataSet3 = new BarDataSet(yVals3, "С��ÿ��֧��");
		barDataSet3.setColor(Color.YELLOW);// ���õ�����������ɫ
		barDataSet3.setDrawValues(false); //����ʾ��ֵ
		
		ArrayList<IBarDataSet> threebardata = new ArrayList<>();// IBarDataSet
																// �ӿںܹؼ�������Ӷ������ݵĹؼ��ṹ��LineChartҲ�ǿ��Բ��ö�Ӧ�Ľӿ��࣬Ҳ������Ӷ�������
		threebardata.add(barDataSet);
		threebardata.add(barDataSet2);
		threebardata.add(barDataSet3);

		BarData multi_bardata = new BarData(xVals, threebardata);
		return multi_bardata;
	}	
}
