package com.db.chart.gaw;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.db.chart.view.LineChartView;
import com.db.williamchart.R;

public class MainActivity extends Activity {
	private LineChartView chartView;
	private ImageButton imageButton;
	
	private Button fresh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		chartView = (LineChartView) findViewById(R.id.chartView);
		imageButton = (ImageButton) findViewById(R.id.imageButton);
		fresh = (Button) findViewById(R.id.fresh);
		
		final GawLineChart gaw = new GawLineChart();
		
		gaw.initalChart(getBaseContext(), chartView, imageButton);
		
		ArrayList<String> labels = new ArrayList<String>();
		final ArrayList<Float> values = new ArrayList<Float>();
		for (int i = 0;i < 5;i++){
			labels.add("7ÔÂ");
		}
		float date = 9.7f;
		
			for (int i = 0;i < 5;i++){
				date = date - 0.2f;
				values.add(date);
			}
			gaw.addDate(labels, values, Color.RED);
			
		gaw.showChart();
		
		fresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				values.clear();
				for (int i = 0;i < 5;i++){
					
					values.add(2.2f);
				}
				gaw.updateY(0, values);
				gaw.updateChart();
			}
		});
		
		
	}
}
