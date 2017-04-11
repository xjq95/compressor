package me.fjnu.compressor.process;

import com.github.jaiimageio.jpeg2000.J2KImageWriteParam;
import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.util.MyByteUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.30.
 * jpeg2000压缩
 */
@Component
public class Jp2Process implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException {
		ImageWriter writer = ImageIO.getImageWritersByFormatName("JPEG2000").next();
		System.out.println(writer.getClass().getName());
		J2KImageWriteParam j2kParams = (J2KImageWriteParam) writer.getDefaultWriteParam();
		j2kParams.setSOP(true);
		j2kParams.setWriteCodeStreamOnly(true);
		j2kParams.setProgressionType("layer");
		j2kParams.setLossless(false);
		j2kParams.setCompressionMode(J2KImageWriteParam.MODE_EXPLICIT);
		j2kParams.setCompressionType("JPEG2000");
		j2kParams.setCompressionQuality(0.1f);
		j2kParams.setEncodingRate(1.01);
		j2kParams.setFilter(J2KImageWriteParam.FILTER_97);
		ByteArrayInputStream bais = new ByteArrayInputStream(srcBytes);
		BufferedImage image = ImageIO.read(bais);
		bais.close();
		
		//压缩
		File f = File.createTempFile("test-jpeg", ".jpeg");
		f.deleteOnExit();
		ImageOutputStream ios = ImageIO.createImageOutputStream(f);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(image, null, null), j2kParams);
		writer.dispose();
		return FileUtils.readFileToByteArray(f);
	}
	
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException {
		
		BufferedImage bufferedImage = MyByteUtil.createImageFromBytes(bytes);
		String[] s = compressInfo.getMultipartFile().getOriginalFilename().split(Pattern.quote("."));
		String suffix = s[s.length - 2];
		return MyByteUtil.writeBytes(bufferedImage, suffix);
	}
}
