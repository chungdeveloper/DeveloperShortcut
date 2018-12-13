package dc.soft.developer.shortcut;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        TextView btnDeveloper = findViewById(R.id.btnDeveloper);
        TextView btnLocation = findViewById(R.id.btnLocation);
        TextView btn1 = findViewById(R.id.btnWifiSetting);
        TextView btn2 = findViewById(R.id.btnApplication);
        TextView btn3 = findViewById(R.id.btnInfo);
        TextView version = findViewById(R.id.tvVersion);

        PackageInfo pInfo;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version.setText(String.format("Version %d%s", pInfo.versionCode, BuildConfig.BUILD_NUMBER));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        btnLocation.setOnClickListener(this);
        btnDeveloper.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String action = null;
        switch (v.getId()) {
            case R.id.btnDeveloper:
                action = Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS;
                break;
            case R.id.btnLocation:
                action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
                break;
            case R.id.btnWifiSetting:
                action = Settings.ACTION_WIFI_SETTINGS;
                break;
            case R.id.btnApplication:
                action = Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS;
                break;
            case R.id.btnInfo:
                action = Settings.ACTION_DEVICE_INFO_SETTINGS;
                break;
        }

        if (action == null) return;
        startActivityForResult(new Intent(action), 123);
    }
}
