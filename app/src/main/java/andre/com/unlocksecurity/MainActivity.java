package andre.com.unlocksecurity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    private static final int ACTIVATION_REQUEST = 1;

    private DevicePolicyManager devicePolicyManager;
    private ComponentName deviceAdmin;
    private ToggleButton toggleButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.toggle_device_admin);
        toggleButton.setOnCheckedChangeListener(this);

        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        deviceAdmin = new ComponentName(MainActivity.this, AdminReceiver.class);
    }

    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        if (isChecked) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdmin);
            startActivityForResult(intent, ACTIVATION_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVATION_REQUEST:
                toggleButton.setChecked(resultCode == Activity.RESULT_OK);
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}