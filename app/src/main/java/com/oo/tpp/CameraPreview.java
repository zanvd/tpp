package com.oo.tpp;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	private final String TAG = "tpp";

	private SurfaceHolder holder;
	private Camera camera;

	public CameraPreview (Context context, Camera camera) {
		super(context);
		this.camera = camera;

		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		this.holder = getHolder();
		this.holder.addCallback(this);
	}

	@Override
	public void surfaceCreated (SurfaceHolder holder) {
		// The Surface has been created, now tell the camera where to draw the preview.
		try {
			this.camera.setPreviewDisplay(holder);
			this.camera.setDisplayOrientation(90);
		} catch (Exception e) {
			Log.d(TAG, "Error setting camera preview: " + e.getMessage());
		}
	}

	@Override
	public void surfaceChanged (SurfaceHolder holder, int format, int width, int height) {
		try {
			this.camera.setPreviewDisplay(holder);
			this.camera.startPreview();
		} catch (Exception e) {
			Log.d(TAG, "Error setting camera preview: " + e.getMessage());
		}
	}

	@Override
	public void surfaceDestroyed (SurfaceHolder holder) {

	}
}
