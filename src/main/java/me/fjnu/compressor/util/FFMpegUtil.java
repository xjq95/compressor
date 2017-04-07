package me.fjnu.compressor.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiaqi on 17.4.3.
 * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
 */
@Component
public class FFMpegUtil {
	private String BASEPATH;
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.io.tmpdir"));
//		doH261Convert("d:\\Explosion.mov", "D:\\1.mov");
	}
	
	
	public FFMpegUtil() throws IOException {
		InputStream resourceAsStream = this.getClass().getResourceAsStream("/util/ffmpeg.exe");
		BASEPATH = System.getProperty("java.io.tmpdir");
		FileUtils.copyInputStreamToFile(resourceAsStream, new File(BASEPATH + "ffmpeg.exe"));
	}
	
	public boolean doH264Convert(String oldfilepath, String toFile) {
		//转换格式命令
		List<String> commend = new ArrayList<String>();
		commend.add(BASEPATH + "ffmpeg");
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-s");
		commend.add("352x288");
		commend.add("-vcodec");
		commend.add("libx264");
		commend.add("-y");
		commend.add(toFile);
		return execComand(commend);
	}
	
	public boolean doH261Convert(String oldfilepath, String toFile) {
		//转换格式命令
		List<String> commend = new ArrayList<String>();
		commend.add(BASEPATH + "ffmpeg");
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-s");
		commend.add("352x288");
		commend.add("-vcodec");
		commend.add("h261");
		commend.add("-y");
		commend.add(toFile);
		return execComand(commend);
	}
	
	public boolean doMpegConvert(String oldfilepath, String toFile) {
		//转换格式命令
		List<String> commend = new ArrayList<String>();
		commend.add(BASEPATH + "ffmpeg");
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-s");
		commend.add("352x288");
		commend.add("-vcodec");
		commend.add("mpeg4");
		commend.add("-y");
		commend.add(toFile);
		return execComand(commend);
	}
	
	public boolean execComand(List<String> commend) {
		try {
			//转换格式进程
			ProcessBuilder builder = new ProcessBuilder(commend);
			builder.command(commend);
			builder.redirectErrorStream(true);
			
			//进程信息输出到控制台
			Process p = builder.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			p.waitFor();//直到上面的命令执行完，才向下执行
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
