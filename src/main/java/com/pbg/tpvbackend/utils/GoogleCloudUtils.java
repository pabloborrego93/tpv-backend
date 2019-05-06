package com.pbg.tpvbackend.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GoogleCloudUtils {

	private GoogleCloudUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static byte[] toByteArray(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[8192];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}
		return baos.toByteArray();
	}

}
