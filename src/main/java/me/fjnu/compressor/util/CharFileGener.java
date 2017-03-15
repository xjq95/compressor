package me.fjnu.compressor.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;

import java.io.*;

/**
 * Created by xujiaqi on 17.3.13.
 * todo
 */
public class CharFileGener {
	public static void main(String[] args) throws IOException {
		charGener();
	}
	
	private static void charGener() throws IOException {
		Writer writer = new BufferedWriter(new FileWriter(new File("S:\\IntelliJ projects\\compressor\\src\\main\\resources\\txt.txt")),
				1024 * 1024 * 5);
		int max = 1024 * 1024 * 10;
		String strs = "`1234567890-=~!@#$%^&*()_+qwertyuiop[]//QWERTYUIOP{}|asdfghjkl;'\":LKJHGFDSAzxcvbnm, ./?><MNBVCXZ";
		for (int i = 0; i < max; i++) {
			writer.write(RandomStringUtils.random(99, strs));
			writer.write("/n");
		}
		IOUtils.closeQuietly(writer);
	}
}
