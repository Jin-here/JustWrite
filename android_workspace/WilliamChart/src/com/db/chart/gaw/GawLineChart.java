package com.db.chart.gaw;

import java.util.ArrayList;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.ChartView;
import com.db.chart.view.Tooltip;
import com.db.chart.view.animation.Animation;
import com.db.williamchart.R;

public class GawLineChart {
	private Context context;
	private ChartView chartView;
	private ImageButton imageButton;
	private boolean isUpdate = false;

	/********************** 存放数据 **********************/
	/*private final String[] mLabelsThree = { "00", "04", "08", "12", "16", "20",
			"24" };
	private final float[][] mValuesThree = {
			{ 4.5f, 5.7f, 4f, 8f, 2.5f, 3f, 6.5f },
			{ 1.5f, 2.5f, 1.5f, 5f, 5.5f, 5.5f, 3f },
			{ 8f, 7.5f, 7.8f, 1.5f, 8f, 8f, .5f } };*/
	

	/********************** 存放数据 **********************/

	public GawLineChart() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 添加曲线，需在initalChart之后
	 * @param labels	X轴显示数据
	 * @param values	Y轴显示数据
	 * @param color		曲线颜色，例如：Color.BLACK
	 */
	public void addDate(ArrayList<String> labels, ArrayList<Float> values, int color){
		LineSet dataset = new LineSet(labels, values);
		dataset.setColor(color)
				.setDotsStrokeThickness(Tools.fromDpToPx(2))
				.setDotsStrokeColor(Color.parseColor("#FF58C674"))
				.setDotsColor(Color.parseColor("#eef1f6"));
		this.chartView.addData(dataset);
	}
	
	/**
	 * 显示图表
	 * 注意：需在LineChartView和ImageButton已经初始化后执行
	 */
	public void initalChart(Context context, ChartView chartView,
			ImageButton imageButton) {
		this.context = context;
		this.chartView = chartView;
		this.imageButton = imageButton;
		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isUpdate) {
					dismissChart();
				} else {
					updateChart();
				}
				isUpdate = !isUpdate;

				// 用户另行添加的事件
				setOnImageButtonClick();
			}
		});

	}
	
	public void showChart(){
		dismissPlay();
		Runnable action = new Runnable() {
			@Override
			public void run() {
				new Handler().postDelayed(new Runnable() {
					public void run() {
						showPlay();
					}
				}, 500);
			}
		};

		produceThree(action);
	}

	private void dismissPlay() {
		this.imageButton.setEnabled(false);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
			this.imageButton.animate().alpha(0).scaleX(0).scaleY(0);
		else
			this.imageButton.setVisibility(View.INVISIBLE);
	}

	private void showPlay() {
		this.imageButton.setEnabled(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
			this.imageButton.animate().alpha(1).scaleX(1).scaleY(1);
		else
			this.imageButton.setVisibility(View.VISIBLE);
	}

	private void produceThree(Runnable action) {

		Tooltip tip = new Tooltip(this.context,
				R.layout.linechart_three_tooltip, R.id.value);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

			tip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1),
					PropertyValuesHolder.ofFloat(View.SCALE_X, 1f),
					PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f));

			tip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0),
					PropertyValuesHolder.ofFloat(View.SCALE_X, 0f),
					PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f));
		}

		this.chartView.setTooltips(tip);

		/*LineSet dataset = new LineSet(mLabelsThree, mValuesThree[0]);
		dataset.setColor(Color.parseColor("#FF58C674"))
				.setDotsStrokeThickness(Tools.fromDpToPx(2))
				.setDotsStrokeColor(Color.parseColor("#FF58C674"))
				.setDotsColor(Color.parseColor("#eef1f6"));
		this.chartView.addData(dataset);

		dataset = new LineSet(mLabelsThree, mValuesThree[1]);
		dataset.setColor(Color.parseColor("#FFA03436"))
				.setDotsStrokeThickness(Tools.fromDpToPx(2))
				.setDotsStrokeColor(Color.parseColor("#FFA03436"))
				.setDotsColor(Color.parseColor("#eef1f6"));
		this.chartView.addData(dataset);

		dataset = new LineSet(mLabelsThree, mValuesThree[2]);
		dataset.setColor(Color.parseColor("#FF365EAF"))
				.setDotsStrokeThickness(Tools.fromDpToPx(2))
				.setDotsStrokeColor(Color.parseColor("#FF365EAF"))
				.setDotsColor(Color.parseColor("#eef1f6"));
		this.chartView.addData(dataset);*/

		Paint gridPaint = new Paint();
		gridPaint.setColor(Color.parseColor("#308E9196"));
		gridPaint.setStyle(Paint.Style.STROKE);
		gridPaint.setAntiAlias(true);
		gridPaint.setStrokeWidth(Tools.fromDpToPx(1f));

		this.chartView.setBorderSpacing(1).setAxisBorderValues(0, 10, 1)
				.setXLabels(AxisController.LabelPosition.OUTSIDE)
				.setYLabels(AxisController.LabelPosition.OUTSIDE)
				.setLabelsColor(Color.parseColor("#FF8E9196")).setXAxis(false)
				.setYAxis(false).setStep(2)
				.setBorderSpacing(Tools.fromDpToPx(5))
				.setGrid(ChartView.GridType.VERTICAL, gridPaint);

		Animation anim = new Animation().setEndAction(action);

		this.chartView.show(anim);
	}

	private void dismissChart() {

		dismissPlay();

		Runnable action = new Runnable() {
			@Override
			public void run() {
				new Handler().postDelayed(new Runnable() {
					public void run() {
						showPlay();
						showChart();
					}
				}, 500);
			}
		};

		dismissThree(action);
	}

	private void dismissThree(Runnable action) {
		this.chartView.dismissAllTooltips();
		this.chartView.dismiss(new Animation().setEndAction(action));
	}

	/**
	 * 更新单个曲线数据
	 * @param i	第几个曲线，从0开始
	 * @param values	新的曲线数据
	 */
	public void updateY(int i, ArrayList<Float> values){
		this.chartView.updateValues(i, values);
	}
	
	/**
	 * 更新图表数据
	 */
	public void updateChart() {

		dismissPlay();

		updateThree();
	}

	private void updateThree() {
		this.chartView.dismissAllTooltips();
		/*
		 * this.chartView.updateValues(0, mValuesThree[2]);
		 * this.chartView.updateValues(1, mValuesThree[0]);
		 * this.chartView.updateValues(2, mValuesThree[1]);
		 */
		this.chartView.notifyDataUpdate();
	}

	/**
	 * 用户可以添加点击ImageButton的其他事件
	 */
	public void setOnImageButtonClick() {

	}
}
