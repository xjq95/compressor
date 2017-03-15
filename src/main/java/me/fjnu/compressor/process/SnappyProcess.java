package me.fjnu.compressor.process;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xerial.snappy.Snappy;

import java.io.IOException;

/**
 * Created by xujiaqi on 17.3.11.
 * todo
 */
@Component
@Scope("singleton")
public class SnappyProcess implements CompressProcess {
	@Override
	public byte[] compress(byte srcBytes[]) throws IOException {
		return Snappy.compress(srcBytes);
	}
	@Override
	public byte[] uncompress(byte[] bytes) throws IOException {
		return Snappy.uncompress(bytes);
	}
}
