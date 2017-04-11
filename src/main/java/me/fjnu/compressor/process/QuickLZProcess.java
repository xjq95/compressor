package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.util.QuickLZ;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.13.
 * QuickLZ 是一个号称世界压缩速度最快的压缩库，并且也是个开源的压缩库，其遵守GPL 1, 2 或3协议
 */
@Component
public class QuickLZProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException {
		return QuickLZ.compress(srcBytes,3);
	}
	
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException {
		return QuickLZ.decompress(bytes);
	}
}
