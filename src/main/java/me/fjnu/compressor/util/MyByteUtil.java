package me.fjnu.compressor.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by xujiaqi on 17.3.31.
 * todo
 */
public class MyByteUtil {
	/**
	 * 字节数组转buff'''''
	 * @param imageData
	 * @return
	 */
	public static BufferedImage createImageFromBytes(byte[] imageData) {
		ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
		try {
			return ImageIO.read(bais);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将{@link BufferedImage}生成formatName指定格式的图像数据
	 * @param source
	 * @param formatName 图像格式名，图像格式名错误则抛出异常
	 * @return
	 */
	public static byte[] writeBytes(BufferedImage source,String formatName){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		Graphics2D g = null;
		try {
			for(BufferedImage s=source;!ImageIO.write(s, formatName, output);){
				if(null!=g)
					throw new IllegalArgumentException(String.format("not found writer for '%s'",formatName));
				s = new BufferedImage(source.getWidth(),
						source.getHeight(), BufferedImage.TYPE_INT_RGB);
				g = s.createGraphics();
				g.drawImage(source, 0, 0,null);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (null != g)
				g.dispose();
		}
		return output.toByteArray();
	}
}
