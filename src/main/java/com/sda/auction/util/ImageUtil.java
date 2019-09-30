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
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	public static byte[] getByteArray(Blob image) throws SQLException {
		int blobLength = (int) image.length();
		return image.getBytes(1, blobLength);
	}

	public static Blob toBlob(MultipartFile file) {
		if (!file.isEmpty()) {
			byte[] imageByteArray = new byte[0];
			try {
				imageByteArray = file.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Blob image = BlobProxy.generateProxy(imageByteArray);
			return image;
		}
		return null;
	}

}
