package cn.mf.codelaboratory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class TestBaiDuActivity extends AppCompatActivity implements BDLocationListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bai_du);
        mTextView = (TextView) findViewById(R.id.center_tv);
        LocationClient locationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setOpenGps(true);// 设置打开gps
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(this);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        mTextView.setText(bdLocation.getSpeed()+"km/H");
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
