package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.util.MyByteUtil;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
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
		BufferedImage bufferedImage = MyByteUtil.createImageFromBytes(srcBytes);
		return MyByteUtil.writeBytes(bufferedImage, "jpeg");
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
