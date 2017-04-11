package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.util.huffman.AdaptiveHuffmanCompress;
import me.fjnu.compressor.util.huffman.AdaptiveHuffmanDecompress;
import me.fjnu.compressor.util.huffman.BitInputStream;
import me.fjnu.compressor.util.huffman.BitOutputStream;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.13.
 * 霍夫曼压缩
 */
@Component
public class HuffmanProcess implements CompressProcess {
	
	@Override
	public byte[] compress(byte[] b, CompressInfo compressInfo) throws IOException {
		InputStream in = new ByteArrayInputStream(b);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BitOutputStream bitOut = new BitOutputStream(out);
		
		AdaptiveHuffmanCompress.compress(in, bitOut);
		bitOut.close();
		return out.toByteArray();
	}
	
	@Override
	public byte[] uncompress(byte[] b, CompressInfo compressInfo) throws DataFormatException, IOException {
		InputStream in = new ByteArrayInputStream(b);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AdaptiveHuffmanDecompress.decompress(new BitInputStream(in), out);
		return out.toByteArray();
	}
	
}
