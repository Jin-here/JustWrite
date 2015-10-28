package com.environmentalmonitoring.fragment;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.ColoursXYSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.environmentalmonitoring.activity.MainActivity;
import com.environmentalmonitoring.activity.R;
import com.environmentalmonitoring.config.Config;
import com.environmentalmonitoring.db.MySQLiteOpenHelper;
import com.environmentalmonitoring.db.ClientApp;

public class Tab01Fragment extends Fragment {
	public static final String TAG = "TAB01FRAGMENT";
	
	private ClientApp app;
	private MainActivity activity;
	private LinearLayout linearlayout;
	
	private XYMultipleSeriesRenderer mRenderer;
	private ColoursXYSeriesRenderer rendererMajor;
	
	
	private Spinner sensor_type_spinner;
	private Spinner time_spinner;
	private ArrayAdapter<String> type_spinner_adapter;
	private int choice_type = 0;
	private int choice_time = 0;
	
	private long minus = 0;
	private String[] name = new String[]{"甲醛","pm2.5","co2","光照","温度","声音"};
	private int[] name_id = new int[]{R.string.pm25_title,R.string.co2_title,R.string.light_title,R.string.air_title,R.string.soil_title,R.string.air_tmper_title};
	
	//判断是否为查看历史数据，是则不更新，否则实时更新数据
	private boolean isHistory = false;
	//true：spinner自身选择，false：tab00跳转
	private boolean ways = true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = (MainActivity)getActivity();
		app = (ClientApp) activity.getApplication();
		
		//初始化render
		inital_Render();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.tab01_fragment, container,false);
		linearlayout = (LinearLayout)layout.findViewById(R.id.chart);
		sensor_type_spinner = (Spinner) layout.findViewById(R.id.sensor_type_spinner);
		time_spinner = (Spinner) layout.findViewById(R.id.time_spinner);
		Button check_button = (Button) layout.findViewById(R.id.check_button);
		//初始化spinner
		type_spinner_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, 
				new String[]{getString(R.string.pm25_title),getString(R.string.co2_title),
			getString(R.string.light_title),getString(R.string.air_title),getString(R.string.soil_title),
			getString(R.string.air_tmper_title)});
		sensor_type_spinner.setAdapter(type_spinner_adapter);
		time_spinner.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, new String[]{"实时数据","1小时","2小时"}));
		sensor_type_spinner.setSelection(choice_type,true);
		System.out.println("setChoice:" + choice_type);
		time_spinner.setSelection(choice_time,true);
		//刷新曲线图表
		updataChartView(0);
		sensor_type_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("1:onitemclick:choice" + choice_type + ",position:" + position);
				//如果自身更新，choice向position看齐，若相反，则相反
				if (ways){
					choice_type = position;
				}else{
					position = choice_type;
					ways = true;
				}
				//记住选项，下次进入tab选项不变
				//choice_type = position;
				isHistory = false;
				
				System.out.println("2:onitemclick:choice" + choice_type + ",position:" + position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		time_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				isHistory = false;
				//记住选项，下次进入tab选项不变
				choice_time = position;
				
				switch (position){
				case 1:
					minus = 10000;//相当于  01小时:00分:00秒
					break;
				case 2:
					minus = 20000;//相当于  02小时:00分:00秒
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//设置点击事件，只有点击并且时间选项不为“实时数据”时，才不进行实时更新
		check_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (choice_time != 0){
					isHistory = true;
					//从数据库查询数据
					MySQLiteOpenHelper helper = new MySQLiteOpenHelper(activity);
					ArrayList<Double> data = helper.chart_query(name[choice_type], minus);
					updatahistory(data);
				}else{
					isHistory = false;					
				}				
				
			}
		});
		return layout;
	}
	
	
	//刷新曲线图表
	public void updataChartView(int xMin)
	{
		rendererMajor.setWarningMinValue(app.getSensorMax(choice_type));//设置告警最小值
		rendererMajor.setWarningMaxValue(app.getSensorMin(choice_type));//设置告警最大值
		//yAxisMin = Math.round(min[choice]*100)/100.00 - 1;//Y轴的最小值
		//yAxisMax = Math.round(max[choice]*100)/100.00 + 1;//Y轴的最大值

		//图表数据集合
		XYMultipleSeriesDataset mDataset;
		
		//图表view
		GraphicalView mChartView;
		
		
		
		//创建图表数据集合
		mDataset = new XYMultipleSeriesDataset();

	    //填充曲线数据
		ArrayList<Double> data = activity.getchart_data(choice_type);
	    XYSeries seriesMajor = new XYSeries(getString(name_id[choice_type]));
	    int i=1;
	    //将数据添加到数据子集中，并动态修改最大值和最小值
	    double yAxisMin = 0;//Y轴的最小值
	    double yAxisMax = 0;//Y轴的最大值
	    for(double value : data)
	    {
			seriesMajor.add(i, value);
			if(value<yAxisMin){
				yAxisMin = value;
			}
			if(value>yAxisMax){
				yAxisMax = value;
			}
			i++;
	    }
	    //将数据子集添加到图表数据集合中
	  	mDataset.addSeries(seriesMajor);
	  	
	  	//设置图表XY轴的最大值和最小值,为了看得清楚，最大值和最小值放大50%
	  	mRenderer.setYAxisMin(yAxisMin - yAxisMin/2);
	  	mRenderer.setYAxisMax(yAxisMax + yAxisMax/2);
	  	
	  	for (int j = 1;j < Config.CHART_MAX_POINT+1;j++){
	  		mRenderer.addXTextLabel(j, String.valueOf(xMin));
	  		xMin--;
	  		if (xMin<0){
	  			xMin = 0;
	  		}
	  	}

		linearlayout.removeAllViews();
	    //创建图表view
		mChartView = ChartFactory.getLineChartView(getActivity(), mDataset, mRenderer);
		
		
		mRenderer.setApplyBackgroundColor(true);//开启背景颜色
		mRenderer.setBackgroundColor(Color.TRANSPARENT);//设置背景透明
		mRenderer.setMarginsColor(Color.TRANSPARENT);//设置边缘透明
		mRenderer.setSelectableBuffer(10);
		//将图表view添加图表容器中
		linearlayout.addView(mChartView);
		//图表重会
		mChartView.repaint();
	}
	
	/**
	 * 查看历史数据
	 * @param data
	 */
	private void updatahistory(ArrayList<Double> data){
		
		XYMultipleSeriesRenderer mRenderer1;
		ColoursXYSeriesRenderer rendererMajor1;
		mRenderer1 = new XYMultipleSeriesRenderer();
		mRenderer1.setApplyBackgroundColor(true);//开启背景颜色
	    mRenderer1.setAxisTitleTextSize(25);//设置坐标文字尺寸大小
	    mRenderer1.setAxesColor(Color.BLACK);///设置坐标文字颜色
	    mRenderer1.setChartTitleTextSize(20);//设置图表标题文字尺寸大小
	    mRenderer1.setLabelsTextSize(18);//设置刻度显示文字的大小(XY轴都会被设置)
	    mRenderer1.setLabelsColor(Color.BLACK);//设置XY轴线的颜色
	    mRenderer1.setLegendTextSize(25);//图例文字大小 
	    mRenderer1.setMargins(new int[] { 20, 20, 0, 20 });//设置图表的外边框(上/左/下/右)
	    mRenderer1.setZoomButtonsVisible(true);//是否显示放大缩小按钮  
	    mRenderer1.setPointSize(1);//设置点的大小(图上显示的点的大小和图例中点的大小都会被设置)  	    
	    mRenderer1.setXLabelsColor(Color.WHITE);//设置X轴标签文本的颜色
	    mRenderer1.setYLabelsColor(0, Color.WHITE);//设置Y轴标签文本的颜色
	    mRenderer1.setXAxisMin(1);
	    mRenderer1.setXAxisMax(data.size());	    
	    mRenderer1.setZoomEnabled(true, true);
	    mRenderer1.setZoomRate(2);
	    //创建一个曲线渲染对象
		rendererMajor1 = new ColoursXYSeriesRenderer();
		rendererMajor1.setPointStyle(PointStyle.CIRCLE);//设置为曲线图
		rendererMajor1.setFillPoints(true);//数据点被填充
		rendererMajor1.setDisplayChartValues(false);//在图表中显示点的值
		rendererMajor1.setDisplayChartValuesDistance(1);//在X轴上，设置两点之间的距离
		mRenderer1.addXTextLabel(1, "最远");
		rendererMajor1.setColor(Color.BLUE);//设置点的颜色
		rendererMajor1.setChartValuesTextSize(25);//设置点值文本的尺寸大小
		rendererMajor1.setChartValuesSpacing(15f);//实时数据文本到中心点的间距
		rendererMajor1.setUseColor(true);//设置当前为双色曲线图
		rendererMajor1.setPointColor(Color.GREEN);//设置点的颜色
		rendererMajor1.setChartValueTextColor(Color.WHITE);//设置数值文本的颜色
		rendererMajor1.setWarningMinValue(app.getSensorMax(choice_type));//设置告警最小值
		rendererMajor1.setWarningMaxValue(app.getSensorMin(choice_type));//设置告警最大值
		//将曲线渲染对象添加到图表渲染器中
		mRenderer1.addSeriesRenderer(rendererMajor1);
	
		//图表数据集合
		XYMultipleSeriesDataset mDataset;
		
		//图表view
		GraphicalView mChartView;
	    
		//创建图表数据集合
		mDataset = new XYMultipleSeriesDataset();

		// 创建一个数据子集
		XYSeries seriesMajor = new XYSeries(getString(name_id[choice_type]));
		int i = 1;
		// 将数据添加到数据子集中，并动态修改最大值和最小值
		double yAxisMin = 0;// Y轴的最小值
		double yAxisMax = 0;// Y轴的最大值
		for (double value : data) {
			seriesMajor.add(i, value);
			if (value < yAxisMin) {
				yAxisMin = value;
			}
			if (value > yAxisMax) {
				yAxisMax = value;
			}
			i++;
		}
		// 设置图表XY轴的最大值和最小值，放大50%
		mRenderer1.setYAxisMin(yAxisMin - yAxisMin/2);
		mRenderer1.setYAxisMax(yAxisMax + yAxisMax/2);
		// 将数据子集添加到图表数据集合中
		mDataset.addSeries(seriesMajor);

		linearlayout.removeAllViews();
	    //创建图表view
		mChartView = ChartFactory.getLineChartView(getActivity(), mDataset, mRenderer1);

		mRenderer1.setApplyBackgroundColor(true);//开启背景颜色
		mRenderer1.setBackgroundColor(Color.TRANSPARENT);//设置背景透明
		mRenderer1.setMarginsColor(Color.TRANSPARENT);//设置边缘透明
		mRenderer1.setSelectableBuffer(10);
		//将图表view添加图表容器中
		linearlayout.addView(mChartView);
		//图表重会
		mChartView.repaint();
	}

	private void inital_Render() {
		// TODO Auto-generated method stub
		// 创建图表数据渲染器
		mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.setApplyBackgroundColor(true);// 开启背景颜色
		mRenderer.setAxisTitleTextSize(25);// 设置坐标文字尺寸大小
		mRenderer.setAxesColor(Color.BLACK);// /设置坐标文字颜色
		mRenderer.setChartTitleTextSize(20);// 设置图表标题文字尺寸大小
		mRenderer.setLabelsTextSize(18);// 设置刻度显示文字的大小(XY轴都会被设置)
		mRenderer.setLabelsColor(Color.BLACK);// 设置XY轴线的颜色
		mRenderer.setLegendTextSize(25);// 图例文字大小
		mRenderer.setMargins(new int[] { 20, 20, 0, 20 });// 设置图表的外边框(上/左/下/右)
		mRenderer.setZoomButtonsVisible(true);// 是否显示放大缩小按钮
		mRenderer.setPointSize(10);// 设置点的大小(图上显示的点的大小和图例中点的大小都会被设置)
		mRenderer.setXLabels(0);
		mRenderer.setXLabelsColor(Color.WHITE);// 设置X轴标签文本的颜色
		mRenderer.setYLabelsColor(0, Color.WHITE);// 设置Y轴标签文本的颜色
		mRenderer.setXAxisMin(1);
		mRenderer.setXAxisMax(Config.CHART_MAX_POINT);
		// 创建一个曲线渲染对象
		rendererMajor = new ColoursXYSeriesRenderer();
		rendererMajor.setPointStyle(PointStyle.CIRCLE);// 设置为曲线图
		rendererMajor.setFillPoints(true);// 数据点被填充
		rendererMajor.setDisplayChartValues(true);// 在图表中显示点的值
		rendererMajor.setDisplayChartValuesDistance(1);// 在X轴上，设置两点之间的距离
		rendererMajor.setColor(Color.BLUE);// 设置点的颜色
		rendererMajor.setChartValuesTextSize(25);// 设置点值文本的尺寸大小
		rendererMajor.setChartValuesSpacing(15f);// 实时数据文本到中心点的间距
		rendererMajor.setUseColor(true);// 设置当前为双色曲线图
		rendererMajor.setPointColor(Color.GREEN);// 设置点的颜色
		rendererMajor.setChartValueTextColor(Color.WHITE);// 设置数值文本的颜色

		// 将曲线渲染对象添加到图表渲染器中
		mRenderer.addSeriesRenderer(rendererMajor);
		mRenderer.setZoomEnabled(true, true);
		mRenderer.setZoomRate(2);
	}
	
	/**
	 * 从tab00进入tab01时，更新spinner选项
	 * @param i
	 */
	public void setChoice(int i){
		this.choice_type = i;
		this.ways = false;
	}
	
	
	/**
	 * 判断此时是否在查看历史数据，是则不实时更新
	 * @return
	 */
	public boolean isHistory(){
		return isHistory;
	}
	
}
