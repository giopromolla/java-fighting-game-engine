package org.jfge.android.graphics;

import org.jfge.spi.graphics.Image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class AndroidImage implements Image {

	private Bitmap image;
	
	private Image flipped;
	
	public AndroidImage(Bitmap image) {
		this.image = image;
	}
	
	@Override
	public Image flip() {
		if(this.flipped == null) {
			this.flipped = 	new AndroidImage(Bitmap.createScaledBitmap(image, -image.getWidth(), image.getHeight(), false));
		}
		
		return flipped;
	}
	
	@Override
	public int getHeight() {
		return image.getHeight();
	}

	@Override
	public int getWidth() {
		return image.getWidth();
	}

	public Bitmap getBitmap() {
		return this.image;
	}

	@Override
	public Image rotate(int degree) {
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		return new AndroidImage(Bitmap.createBitmap(image, 0, 0,this.image.getWidth(), this.image.getHeight(), matrix, true)); 
	}
}
