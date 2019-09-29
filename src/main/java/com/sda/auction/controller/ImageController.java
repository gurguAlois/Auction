package com.sda.auction.controller;

import com.sda.auction.model.Item;
import com.sda.auction.service.ItemService;
import com.sda.auction.util.ImageUtil;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageController {

	@Autowired
	private ItemService itemService;


	@RequestMapping(value = "/displayImage", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") Integer itemId, HttpServletResponse response) throws IOException, SQLException {
		Item item = itemService.findItemById(itemId.toString());
		byte[] byteArray =	ImageUtil.getByteArray(item.getImage());
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(byteArray);
		response.getOutputStream().close();
	}
}
