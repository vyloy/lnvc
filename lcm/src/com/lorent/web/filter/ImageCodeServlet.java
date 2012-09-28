package com.lorent.web.filter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageCodeServlet extends HttpServlet {
	private int imageWidth = 20;
	private int imageHeight = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		设置响应输出
		response.setContentType("image/jpeg");
		response.setHeader("Pramgma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		BufferedImage image = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB);
//		获取图片上下文
		Graphics graphics = image.getGraphics();
		graphics.setColor(getRandColor(200, 255));
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setFont(new Font("Times New Roma",Font.PLAIN,18));
		graphics.setColor(getRandColor(160, 200));
		Random random = new Random();
//		随机产生160条线条
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(imageWidth);
			int y = random.nextInt(imageHeight);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, xl, yl);
		}
		String code = "";
//		随机产生4位验证码
		for(int i=0;i<4;i++){
			String rand = String.valueOf(random.nextInt(10)); 
			code += rand;
			graphics.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			graphics.drawString(rand, 13*i+6, 16);
		}
//		将验证码保存在session中
		session.setAttribute("code", code);
		graphics.dispose();
//		向页面输出图片
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(image, "JPEG", sos);
		sos.flush();
		sos.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		if(config.getInitParameter("imageWidth")!=null)
			imageWidth = Integer.parseInt(config.getInitParameter("imageWidth"));
		if(config.getInitParameter("imageHeight")!=null)
			imageHeight = Integer.parseInt(config.getInitParameter("imageHeight"));
	}
	
	private Color getRandColor(int low,int hight) {
		Random random = new Random();
		if(low>255)low = 255;
		if(hight>255)hight=255;
		int r = low+random.nextInt(hight-low);
		int g = low+random.nextInt(hight-low);
		int b = low+random.nextInt(hight-low);
		return new Color(r,g,b);
	}
}
