package com.oo.tpp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;

public class CameraHelper {
	private static final String TAG = "tpp";
	private static int cameraId;

	/**
	 * Reatrieve a camera back facing instance.
	 * @return
	 */
	public static Camera getCameraInstance () {
		Camera camera = null;
		try {
			// Iterate over all available cameras.
			cameraId = -1;
			int numOfCamera = camera.getNumberOfCameras();
			CameraInfo info = new CameraInfo();

			for (int i = 0; i < numOfCamera; i++) {
				camera.getCameraInfo(i, info);
				// Check if we found a back facing camera.
				if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
					cameraId = i;
					break;
				}
			}

			camera = Camera.open(cameraId);
			
		} catch (Exception e) {
			Log.d(TAG, "Error opening camera: " + e.getMessage());
		}

		return camera;
	}

	/**
	 * Check if this device has a camera.
	 *
	 * @param context
	 * @return boolean
	 */
	public static boolean checkCameraHardware (Context context) {
		return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}

	/**
	 * Get camera id.
	 *
	 * @return int
	 */
	public static int getCameraId () {
		return cameraId;
	}
}
