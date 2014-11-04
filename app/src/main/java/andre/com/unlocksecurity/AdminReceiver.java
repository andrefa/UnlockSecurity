package andre.com.unlocksecurity;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;

public class AdminReceiver extends DeviceAdminReceiver {
	
	private static final String TAG = "LockScreenPolicyAdminReceiver";

	@Override
	public void onPasswordFailed(Context context, Intent intent) {
		DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		int numFailedAttempts = dpm.getCurrentFailedPasswordAttempts();

        // TODO invoke after X tries
        CameraUtil.takePicture(System.currentTimeMillis() + ".jpg");
	}

}