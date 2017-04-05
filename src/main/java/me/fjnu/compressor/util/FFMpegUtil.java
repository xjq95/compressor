package me.fjnu.compressor.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiaqi on 17.4.3.
 * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
 */
public class FFMpegUtil {
	static final String BASEPATH = "d:\\";
	
	public static void main(String[] args) {
		doH261Convert("d:\\赛车小视频_标清.mp4", "D:\\1.mov");
	}
	
	public static boolean doH264Convert(String oldfilepath, String toFile) {
		//转换格式命令
		List<String> commend = new ArrayList<String>();
		commend.add(BASEPATH + "ffmpeg");
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-vcodec");
		commend.add("libx264rgb");
		commend.add("-y");
		commend.add(toFile);
		return execComand(commend);
	}
	
	public static boolean doH261Convert(String oldfilepath, String toFile) {
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
	
	public static boolean doMpegConvert(String oldfilepath, String toFile) {
		//转换格式命令
		List<String> commend = new ArrayList<String>();
		commend.add(BASEPATH + "ffmpeg");
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-vcodec");
		commend.add("mpeg4");
		commend.add("-y");
		commend.add(toFile);
		return execComand(commend);
	}
	
	public static boolean execComand(List<String> commend) {
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
