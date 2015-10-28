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
	private String[] name = new String[]{"��ȩ","pm2.5","co2","����","�¶�","����"};
	private int[] name_id = new int[]{R.string.pm25_title,R.string.co2_title,R.string.light_title,R.string.air_title,R.string.soil_title,R.string.air_tmper_title};
	
	//�ж��Ƿ�Ϊ�鿴��ʷ���ݣ����򲻸��£�����ʵʱ��������
	private boolean isHistory = false;
	//true��spinner����ѡ��false��tab00��ת
	private boolean ways = true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = (MainActivity)getActivity();
		app = (ClientApp) activity.getApplication();
		
		//��ʼ��render
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
		//��ʼ��spinner
		type_spinner_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, 
				new String[]{getString(R.string.pm25_title),getString(R.string.co2_title),
			getString(R.string.light_title),getString(R.string.air_title),getString(R.string.soil_title),
			getString(R.string.air_tmper_title)});
		sensor_type_spinner.setAdapter(type_spinner_adapter);
		time_spinner.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, new String[]{"ʵʱ����","1Сʱ","2Сʱ"}));
		sensor_type_spinner.setSelection(choice_type,true);
		System.out.println("setChoice:" + choice_type);
		time_spinner.setSelection(choice_time,true);
		//ˢ������ͼ��
		updataChartView(0);
		sensor_type_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("1:onitemclick:choice" + choice_type + ",position:" + position);
				//���������£�choice��position���룬���෴�����෴
				if (ways){
					choice_type = position;
				}else{
					position = choice_type;
					ways = true;
				}
				//��סѡ��´ν���tabѡ���
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
				//��סѡ��´ν���tabѡ���
				choice_time = position;
				
				switch (position){
				case 1:
					minus = 10000;//�൱��  01Сʱ:00��:00��
					break;
				case 2:
					minus = 20000;//�൱��  02Сʱ:00��:00��
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//���õ���¼���ֻ�е������ʱ��ѡ�Ϊ��ʵʱ���ݡ�ʱ���Ų�����ʵʱ����
		check_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (choice_time != 0){
					isHistory = true;
					//�����ݿ��ѯ����
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
	
	
	//ˢ������ͼ��
	public void updataChartView(int xMin)
	{
		rendererMajor.setWarningMinValue(app.getSensorMax(choice_type));//���ø澯��Сֵ
		rendererMajor.setWarningMaxValue(app.getSensorMin(choice_type));//���ø澯���ֵ
		//yAxisMin = Math.round(min[choice]*100)/100.00 - 1;//Y�����Сֵ
		//yAxisMax = Math.round(max[choice]*100)/100.00 + 1;//Y������ֵ

		//ͼ�����ݼ���
		XYMultipleSeriesDataset mDataset;
		
		//ͼ��view
		GraphicalView mChartView;
		
		
		
		//����ͼ�����ݼ���
		mDataset = new XYMultipleSeriesDataset();

	    //�����������
		ArrayList<Double> data = activity.getchart_data(choice_type);
	    XYSeries seriesMajor = new XYSeries(getString(name_id[choice_type]));
	    int i=1;
	    //��������ӵ������Ӽ��У�����̬�޸����ֵ����Сֵ
	    double yAxisMin = 0;//Y�����Сֵ
	    double yAxisMax = 0;//Y������ֵ
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
	    //�������Ӽ���ӵ�ͼ�����ݼ�����
	  	mDataset.addSeries(seriesMajor);
	  	
	  	//����ͼ��XY������ֵ����Сֵ,Ϊ�˿�����������ֵ����Сֵ�Ŵ�50%
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
	    //����ͼ��view
		mChartView = ChartFactory.getLineChartView(getActivity(), mDataset, mRenderer);
		
		
		mRenderer.setApplyBackgroundColor(true);//����������ɫ
		mRenderer.setBackgroundColor(Color.TRANSPARENT);//���ñ���͸��
		mRenderer.setMarginsColor(Color.TRANSPARENT);//���ñ�Ե͸��
		mRenderer.setSelectableBuffer(10);
		//��ͼ��view���ͼ��������
		linearlayout.addView(mChartView);
		//ͼ���ػ�
		mChartView.repaint();
	}
	
	/**
	 * �鿴��ʷ����
	 * @param data
	 */
	private void updatahistory(ArrayList<Double> data){
		
		XYMultipleSeriesRenderer mRenderer1;
		ColoursXYSeriesRenderer rendererMajor1;
		mRenderer1 = new XYMultipleSeriesRenderer();
		mRenderer1.setApplyBackgroundColor(true);//����������ɫ
	    mRenderer1.setAxisTitleTextSize(25);//�����������ֳߴ��С
	    mRenderer1.setAxesColor(Color.BLACK);///��������������ɫ
	    mRenderer1.setChartTitleTextSize(20);//����ͼ��������ֳߴ��С
	    mRenderer1.setLabelsTextSize(18);//���ÿ̶���ʾ���ֵĴ�С(XY�ᶼ�ᱻ����)
	    mRenderer1.setLabelsColor(Color.BLACK);//����XY���ߵ���ɫ
	    mRenderer1.setLegendTextSize(25);//ͼ�����ִ�С 
	    mRenderer1.setMargins(new int[] { 20, 20, 0, 20 });//����ͼ�����߿�(��/��/��/��)
	    mRenderer1.setZoomButtonsVisible(true);//�Ƿ���ʾ�Ŵ���С��ť  
	    mRenderer1.setPointSize(1);//���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)  	    
	    mRenderer1.setXLabelsColor(Color.WHITE);//����X���ǩ�ı�����ɫ
	    mRenderer1.setYLabelsColor(0, Color.WHITE);//����Y���ǩ�ı�����ɫ
	    mRenderer1.setXAxisMin(1);
	    mRenderer1.setXAxisMax(data.size());	    
	    mRenderer1.setZoomEnabled(true, true);
	    mRenderer1.setZoomRate(2);
	    //����һ��������Ⱦ����
		rendererMajor1 = new ColoursXYSeriesRenderer();
		rendererMajor1.setPointStyle(PointStyle.CIRCLE);//����Ϊ����ͼ
		rendererMajor1.setFillPoints(true);//���ݵ㱻���
		rendererMajor1.setDisplayChartValues(false);//��ͼ������ʾ���ֵ
		rendererMajor1.setDisplayChartValuesDistance(1);//��X���ϣ���������֮��ľ���
		mRenderer1.addXTextLabel(1, "��Զ");
		rendererMajor1.setColor(Color.BLUE);//���õ����ɫ
		rendererMajor1.setChartValuesTextSize(25);//���õ�ֵ�ı��ĳߴ��С
		rendererMajor1.setChartValuesSpacing(15f);//ʵʱ�����ı������ĵ�ļ��
		rendererMajor1.setUseColor(true);//���õ�ǰΪ˫ɫ����ͼ
		rendererMajor1.setPointColor(Color.GREEN);//���õ����ɫ
		rendererMajor1.setChartValueTextColor(Color.WHITE);//������ֵ�ı�����ɫ
		rendererMajor1.setWarningMinValue(app.getSensorMax(choice_type));//���ø澯��Сֵ
		rendererMajor1.setWarningMaxValue(app.getSensorMin(choice_type));//���ø澯���ֵ
		//��������Ⱦ������ӵ�ͼ����Ⱦ����
		mRenderer1.addSeriesRenderer(rendererMajor1);
	
		//ͼ�����ݼ���
		XYMultipleSeriesDataset mDataset;
		
		//ͼ��view
		GraphicalView mChartView;
	    
		//����ͼ�����ݼ���
		mDataset = new XYMultipleSeriesDataset();

		// ����һ�������Ӽ�
		XYSeries seriesMajor = new XYSeries(getString(name_id[choice_type]));
		int i = 1;
		// ��������ӵ������Ӽ��У�����̬�޸����ֵ����Сֵ
		double yAxisMin = 0;// Y�����Сֵ
		double yAxisMax = 0;// Y������ֵ
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
		// ����ͼ��XY������ֵ����Сֵ���Ŵ�50%
		mRenderer1.setYAxisMin(yAxisMin - yAxisMin/2);
		mRenderer1.setYAxisMax(yAxisMax + yAxisMax/2);
		// �������Ӽ���ӵ�ͼ�����ݼ�����
		mDataset.addSeries(seriesMajor);

		linearlayout.removeAllViews();
	    //����ͼ��view
		mChartView = ChartFactory.getLineChartView(getActivity(), mDataset, mRenderer1);

		mRenderer1.setApplyBackgroundColor(true);//����������ɫ
		mRenderer1.setBackgroundColor(Color.TRANSPARENT);//���ñ���͸��
		mRenderer1.setMarginsColor(Color.TRANSPARENT);//���ñ�Ե͸��
		mRenderer1.setSelectableBuffer(10);
		//��ͼ��view���ͼ��������
		linearlayout.addView(mChartView);
		//ͼ���ػ�
		mChartView.repaint();
	}

	private void inital_Render() {
		// TODO Auto-generated method stub
		// ����ͼ��������Ⱦ��
		mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.setApplyBackgroundColor(true);// ����������ɫ
		mRenderer.setAxisTitleTextSize(25);// �����������ֳߴ��С
		mRenderer.setAxesColor(Color.BLACK);// /��������������ɫ
		mRenderer.setChartTitleTextSize(20);// ����ͼ��������ֳߴ��С
		mRenderer.setLabelsTextSize(18);// ���ÿ̶���ʾ���ֵĴ�С(XY�ᶼ�ᱻ����)
		mRenderer.setLabelsColor(Color.BLACK);// ����XY���ߵ���ɫ
		mRenderer.setLegendTextSize(25);// ͼ�����ִ�С
		mRenderer.setMargins(new int[] { 20, 20, 0, 20 });// ����ͼ�����߿�(��/��/��/��)
		mRenderer.setZoomButtonsVisible(true);// �Ƿ���ʾ�Ŵ���С��ť
		mRenderer.setPointSize(10);// ���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)
		mRenderer.setXLabels(0);
		mRenderer.setXLabelsColor(Color.WHITE);// ����X���ǩ�ı�����ɫ
		mRenderer.setYLabelsColor(0, Color.WHITE);// ����Y���ǩ�ı�����ɫ
		mRenderer.setXAxisMin(1);
		mRenderer.setXAxisMax(Config.CHART_MAX_POINT);
		// ����һ��������Ⱦ����
		rendererMajor = new ColoursXYSeriesRenderer();
		rendererMajor.setPointStyle(PointStyle.CIRCLE);// ����Ϊ����ͼ
		rendererMajor.setFillPoints(true);// ���ݵ㱻���
		rendererMajor.setDisplayChartValues(true);// ��ͼ������ʾ���ֵ
		rendererMajor.setDisplayChartValuesDistance(1);// ��X���ϣ���������֮��ľ���
		rendererMajor.setColor(Color.BLUE);// ���õ����ɫ
		rendererMajor.setChartValuesTextSize(25);// ���õ�ֵ�ı��ĳߴ��С
		rendererMajor.setChartValuesSpacing(15f);// ʵʱ�����ı������ĵ�ļ��
		rendererMajor.setUseColor(true);// ���õ�ǰΪ˫ɫ����ͼ
		rendererMajor.setPointColor(Color.GREEN);// ���õ����ɫ
		rendererMajor.setChartValueTextColor(Color.WHITE);// ������ֵ�ı�����ɫ

		// ��������Ⱦ������ӵ�ͼ����Ⱦ����
		mRenderer.addSeriesRenderer(rendererMajor);
		mRenderer.setZoomEnabled(true, true);
		mRenderer.setZoomRate(2);
	}
	
	/**
	 * ��tab00����tab01ʱ������spinnerѡ��
	 * @param i
	 */
	public void setChoice(int i){
		this.choice_type = i;
		this.ways = false;
	}
	
	
	/**
	 * �жϴ�ʱ�Ƿ��ڲ鿴��ʷ���ݣ�����ʵʱ����
	 * @return
	 */
	public boolean isHistory(){
		return isHistory;
	}
	
}
