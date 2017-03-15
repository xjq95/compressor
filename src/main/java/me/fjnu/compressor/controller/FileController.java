package me.fjnu.compressor.controller;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;
import me.fjnu.compressor.service.FileCompressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.15.
 * todo
 */
@Controller
public class FileController {
	
	@Autowired
	private FileCompressService fcs;
	
	@PostMapping(value = "/compressor")
	public ModelAndView compressor(CompressInfo compressInfo, HttpServletRequest request) throws
			IOException, InvalidParamsException, DataFormatException, InvalidInputEncodedDataFileException {
		//判断是否存在文件夹
		File fp = new File(compressInfo.getAbsolutePath());
		// 目录已存在创建文件夹
		if (!fp.exists()) {
			fp.mkdir();// 目录不存在的情况下，会抛出异常
		}
		
		String originalFilename = compressInfo.getMultipartFile().getOriginalFilename();
		String suffix = null;
		String newName = null;
		//判断解压还是压缩
		if (compressInfo.getAction() == 1) { //压缩
			//设置新路径
			suffix = compressInfo.getCompressProcess().split("Process")[0];
			newName = originalFilename + "." + suffix;
			compressInfo.setAbsolutePath(compressInfo.getAbsolutePath() + "\\" + newName);
			return new ModelAndView("index").addObject("compressInfo", fcs.compress(compressInfo))
					.addObject("processList", fcs.getProcessList());
		} else {
			suffix = compressInfo.getCompressProcess().split("Process")[0];
			newName = originalFilename.split(suffix)[0];
			compressInfo.setAbsolutePath(compressInfo.getAbsolutePath() + "\\" + newName);
			return new ModelAndView("index").addObject("compressInfo", fcs.unCompress(compressInfo))
					.addObject("processList", fcs.getProcessList());
		}
	}
	
	/**
	 * 请求主页
	 *
	 * @return 带了所有算法信息的主页面
	 */
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		List<String> processList = fcs.getProcessList();
		mav.addObject("processList", processList).setViewName("index");
		return mav;
//		return new ModelAndView("index").addObject("processList", );
	}
}
