package me.fjnu.compressor.process;

import com.github.luben.zstd.Zstd;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.13.
 * Zstandard（缩写为Zstd）是一种新的无损压缩算法，
 * 旨在提供快速压缩，并实现高压缩比。它既不像LZMA和ZPAQ那样追求尽可能高的压缩比，也不像LZ4那样追求极致的压缩速度。
 */
@Component
public class ZstdProcess implements CompressProcess {
	
	@Override
	public byte[] compress(byte[] srcBytes) throws IOException {
	 return 	Zstd.compress(srcBytes);
	}
	
	@Override
	public byte[] uncompress(byte[] bytes) throws DataFormatException, IOException {
		byte[] result = null;
		Zstd.decompress(bytes, result);
		return result;
	}
}
