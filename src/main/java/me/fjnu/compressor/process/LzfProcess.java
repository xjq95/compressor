package me.fjnu.compressor.process;

import com.ning.compress.lzf.LZFDecoder;
import com.ning.compress.lzf.LZFEncoder;
import me.fjnu.compressor.domain.CompressInfo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.13.
 * https://github.com/ning/compress
 * LZF压缩算法的开源版本
 */
@Component
public class LzfProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException {
		return LZFEncoder.encode(srcBytes);
	}
	
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException {
		return LZFDecoder.decode(bytes);
	}
}
