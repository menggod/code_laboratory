package cn.mf.codelaboratory;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import cn.mf.codelaboratory.loaction.SpeedMonitor;

public class SpeedActivity extends AppCompatActivity implements LocationListener {
    private static final int PERIOD_SAMPLING = 1000;        // 采样周期[msec]
    private static final int ACCURACY_DEFAULT = 20;            // 精准度默认值[m]
    private static final String TAG = "SpeedActivity";

    //	private RepositoryWriter repositoryWriter;
    private LocationManager locationManager;

    private int accuracy;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

        mTextView = (TextView) findViewById(R.id.show_tv);
        SpeedMonitor.getInstance().reset();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // 許容精度を設定
        mTextView.setText("onCreate");
        final Bundle extras = getIntent().getExtras();
        accuracy = (extras != null) ? extras.getInt("ACCURACY") : ACCURACY_DEFAULT;
        if (locationManager != null) {
            try {
                Criteria criteria = new Criteria();
                criteria.setSpeedRequired(false);
                locationManager.requestLocationUpdates(
                        locationManager.getBestProvider(criteria, true),
                        PERIOD_SAMPLING,
                        0,
                        this);
            } catch (SecurityException e) {
                Log.e(TAG, e.getMessage());
            }
        } else {
            Log.e(TAG, "LocationManager is null");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // GPS取得処理を停止する
        if (locationManager != null) {
            try {
                locationManager.removeUpdates(this);
            } catch (SecurityException e) {
                Log.e(TAG + "OnPause", e.getMessage());
            }
        } else {
            Log.e(TAG + "onDestroy", "LocationManager is null");
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        mTextView.setText("onLocationChanged");
        Log.e(TAG, "SpeedActivity.onLocationChanged,");
        SpeedMonitor monitor = SpeedMonitor.getInstance();
        if (location.getAccuracy() <= accuracy) {    // 位置精度が有効範囲内か
            // 走行状況に有効な位置情報を通知する
            monitor.reportLocationChange(location);
            // 位置情報を記録する
//            repositoryWriter.reportLocationChange(location);
            mTextView.setText(monitor.getInfo().getCurrentSpeed() + "km");
            Toast.makeText(this, monitor.getInfo().getCurrentSpeed() + "km", Toast.LENGTH_SHORT).show();
        }

        // 走行状況に現在の位置精度を設定
        monitor.setAccuracy(location.getAccuracy());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle bundle) {
//        Log.v("onStatusChanged", provider);
        Log.e(TAG, "SpeedActivity.onStatusChanged,");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.e(TAG, "SpeedActivity.onProviderEnabled,");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.e(TAG, "SpeedActivity.onProviderDisabled,");
    }
}
