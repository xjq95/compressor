package me.fjnu.compressor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by xujiaqi on 17.4.3.
 * todo
 */
public class Test {
	
	public static final String BASEPATH = "d:\\";
	
	public static void main(String[] args) {
		String fromFilePath1 = BASEPATH + "test.mp4";
		doConvert(fromFilePath1, "mp4");
	}
	
	/**
	 * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	 *
	 * @param oldfilepath
	 * @param goalType
	 * @return
	 */
	private static boolean doConvert(String oldfilepath, String goalType) {
		String fileName = UUID.randomUUID() + "." + goalType;
		
		// 文件命名
		Calendar c = Calendar.getInstance();
		
		//转换格式命令
		List<String> commend = new ArrayList<String>();
		commend.add(BASEPATH + "ffmpeg");
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-vf");
		commend.add("scale=352:288");
		commend.add("-vcodec");
		commend.add("h261");
//		commend.add("-copy");
		commend.add("-crf");
		commend.add("28");
		commend.add(BASEPATH + fileName);
		
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
