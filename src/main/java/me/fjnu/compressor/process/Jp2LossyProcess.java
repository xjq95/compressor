package me.fjnu.compressor.process;

import com.github.jaiimageio.jpeg2000.J2KImageWriteParam;
import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;
import me.fjnu.compressor.util.MyByteUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.31.
 * todo
 */
@Component
public class Jp2LossyProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException, InvalidParamsException {
		J2KImageWriteParam iwp = new J2KImageWriteParam();
		ByteArrayInputStream bais = new ByteArrayInputStream(srcBytes);
		BufferedImage image = ImageIO.read(bais);
		bais.close();
		
		Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("jp2");
		ImageWriter writer = writers.next();
		J2KImageWriteParam writeParams = (J2KImageWriteParam) writer.getDefaultWriteParam();
		writeParams.setLossless(false);
		writeParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		writeParams.setCompressionType("JPEG2000");
		writeParams.setCompressionQuality(0.0f);
		writeParams.setEncodingRate(0.5f);
		
		//压缩
		File f = File.createTempFile("test-jpeg2000-lossless", ".jp2");
		f.deleteOnExit();
		ImageOutputStream ios = ImageIO.createImageOutputStream(f);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(image, null, null), writeParams);
		writer.dispose();
		
		return FileUtils.readFileToByteArray(f);
	}
	
	@Override
	public byte[] uncompress(
			byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException, InvalidParamsException, InvalidInputEncodedDataFileException {
		BufferedImage bufferedImage = MyByteUtil.createImageFromBytes(bytes);
		String[] s = compressInfo.getMultipartFile().getOriginalFilename().split(Pattern.quote("."));
		String suffix = s[s.length - 2];
		return MyByteUtil.writeBytes(bufferedImage, suffix);
	}
}
