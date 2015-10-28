package com.vgaw.gawtools_android.location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.List;

/**
 * Created by Administrator on 2015/8/5.
 */
public abstract class Locate {
    private LocationClient locationClient;

    private BDLocation mbdLocation;

    /**
     *
     * @param context    尽量用applicationContext
     */
    public Locate(Context context){
        locationClient = new LocationClient(context);
        initLocation();
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                mbdLocation = bdLocation;
                parseBack();
            }
        });
    }

    public void locate(){
        if (!locationClient.isStarted()){
            //定位SDK start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
            locationClient.start();
        }else {
            locationClient.requestLocation();
        }
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        //Battery_Saving    低功耗模式   不会使用GPS，只会使用网络定位（Wi-Fi和基站定位）
        //Device_Sensors    仅设备(Gps)模式  不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位
        //Hight_Accuracy    高精度模式   会同时使用网络定位和GPS定位，优先返回最高精度的定位结果
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式
        //"gcj02"：国家测绘局标准
        //"bd09ll"：百度经纬度标准
        //"bd09"：百度墨卡托标准
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(false);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationClient.setLocOption(option);
    }

    public void stop(){
        locationClient.stop();
    }

    private String parseBack(){
        StringBuffer sb = new StringBuffer(256);
        /*sb.append("time : ");
        sb.append(mbdLocation.getTime());
        sb.append("\nerror code : ");
        sb.append(mbdLocation.getLocType());
        sb.append("\nlatitude : ");
        sb.append(mbdLocation.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(mbdLocation.getLongitude());
        sb.append("\nradius : ");
        sb.append(mbdLocation.getRadius());*/
        if (mbdLocation.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
            /*sb.append("\nspeed : ");
            sb.append(mbdLocation.getSpeed());// 单位：公里每小时
            sb.append("\nsatellite : ");
            sb.append(mbdLocation.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(mbdLocation.getAltitude());// 单位：米
            sb.append("\ndirection : ");
            sb.append(mbdLocation.getDirection());
            sb.append("\naddr : ");*/
            sb.append(mbdLocation.getAddrStr());
            onReceive(sb.toString(), true);
            /*sb.append("\ndescribe : ");
            sb.append("gps定位成功");*/

        } else if (mbdLocation.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
            //sb.append("\naddr : ");
            sb.append(mbdLocation.getAddrStr());
            onReceive(sb.toString(), true);
            //运营商信息
            /*sb.append("\noperationers : ");
            sb.append(mbdLocation.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");*/
        } else if (mbdLocation.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            onReceive(sb.toString(), true);
            /*sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");*/
        } else if (mbdLocation.getLocType() == BDLocation.TypeServerError) {
            //sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            onReceive(sb.toString(), false);
        } else if (mbdLocation.getLocType() == BDLocation.TypeNetWorkException) {
            //sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
            onReceive(sb.toString(), false);
        } else if (mbdLocation.getLocType() == BDLocation.TypeCriteriaException) {
            //sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            onReceive(sb.toString(), false);
        }
        /*sb.append("\nlocationdescribe : ");// 位置语义化信息
        sb.append(mbdLocation.getLocationDescribe());
        List<Poi> list = mbdLocation.getPoiList();// POI信息
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }*/
        return sb.toString();
    }
    public abstract void onReceive(String location, boolean isSuccess);

}
