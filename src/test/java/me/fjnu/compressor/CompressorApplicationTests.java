package me.fjnu.compressor;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;
import me.fjnu.compressor.service.FileCompressService;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CompressorApplicationTests {
	
//	@Autowired
	FileCompressService fileCompressService;
	
//	@Test
	public void testEncode() throws IOException, InvalidParamsException, DataFormatException, InvalidInputEncodedDataFileException {
		Path path = Paths.get("D:\\log_config.dat");
		MockMultipartFile mockMultipartFile = new MockMultipartFile("log_config.dat", Files.readAllBytes(path));
		String packageName = "me.fjnu.compressor.process";
		String[] strings = {"bzip2Process", "deflaterProcess", "gzipProcess", "lz4Process", "lzoProcess",
				"snappyProcess", "lzfProcess", "quickLZProcess", "lzmaProcess", "zstdProcess", "huffmanProcess",
				"lzwProcess"};
		for (String string : strings) {
			String pathto = "D:\\" + string;
			Path path2 = Paths.get(pathto);
			CompressInfo compressInfo = new CompressInfo();
			compressInfo.setAbsolutePath(pathto);
			compressInfo.setMultipartFile(mockMultipartFile);
			compressInfo.setCompressProcess(string);
			fileCompressService.compress(compressInfo);
			System.out.println(compressInfo);


			//解压测试
			MockMultipartFile mockMultipartFile2 = new MockMultipartFile(string, Files.readAllBytes(path2));
			compressInfo.setAbsolutePath(pathto + "un");
			compressInfo.setMultipartFile(mockMultipartFile2);
			fileCompressService.unCompress(compressInfo);
			System.out.println(compressInfo);
		}
	}
	
}
