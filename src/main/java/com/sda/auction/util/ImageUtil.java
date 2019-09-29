package com.sda.auction.util;

import com.sda.auction.model.Item;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import org.hibernate.engine.jdbc.BlobProxy;

public class ImageUtil {

	public static byte[] getImage(String path) {
		File file = new File(path);
		if (file.exists()) {
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "png", byteOutStream);
				return byteOutStream.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static byte[] getByteArray(Blob image) throws SQLException {
		int blobLength = (int) image.length();
		return image.getBytes(1, blobLength);
	}

	public static Blob toBlob(String imagePath) {
		byte[] imageByteArray = ImageUtil.getImage(imagePath);
		Blob image = BlobProxy.generateProxy(imageByteArray);
		return image;
	}
}
