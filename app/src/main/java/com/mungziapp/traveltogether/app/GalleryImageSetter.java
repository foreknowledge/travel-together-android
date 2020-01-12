package com.mungziapp.traveltogether.app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class GalleryImageSetter {

	public Bitmap getBitmapFromIntent(@NotNull Intent data, Context context) {
		Uri photoUri = data.getData();
		File tempFile = null;
		Cursor cursor = null;
		try {
			String[] proj = { MediaStore.Images.Media.DATA };

			if (photoUri != null)
				cursor = context.getContentResolver().query(photoUri, proj, null, null, null);

			if (cursor != null) {
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

				cursor.moveToFirst();

				tempFile = new File(cursor.getString(column_index));
			}
		}
		finally {
			if (cursor != null)
				cursor.close();
		}

		if (tempFile != null)
			return BitmapFactory.decodeFile(tempFile.getAbsolutePath(), new BitmapFactory.Options());

		return null;
	}
}
