package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.util.MyByteUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.27.
 * todo
 */
@Component
public class JpegProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException {
		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
		System.out.println(writer.getClass().getName());
		JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) writer.getDefaultWriteParam();
		jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegParams.setCompressionQuality(1.0f);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(srcBytes);
		BufferedImage image = ImageIO.read(bais);
		bais.close();
		
		//压缩
		File f = File.createTempFile("test-jpeg", ".jpeg");
		f.deleteOnExit();
		ImageOutputStream ios = ImageIO.createImageOutputStream(f);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(image, null, null), jpegParams);
		writer.dispose();
		return FileUtils.readFileToByteArray(f);
	}
	
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException {
		BufferedImage bufferedImage = MyByteUtil.createImageFromBytes(bytes);
		System.out.println(compressInfo.getMultipartFile().getOriginalFilename());
		String[] s = compressInfo.getMultipartFile().getOriginalFilename().split(Pattern.quote("."));
		String suffix = s[s.length - 2];
		return MyByteUtil.writeBytes(bufferedImage, suffix);
	}
}
