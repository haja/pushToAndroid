package haja.pta.android.activity;

import haja.pta.R;
import haja.pta.android.service.DesktopCommunicationService;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class PtaAndroidActivity extends Activity
{

    private static final String TAG = "PtaAndroidActivity";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startService(new Intent(this, DesktopCommunicationService.class));
    }
}
