package Utilities;

import java.io.File;
import java.io.IOException;

public class ResourceUtil {

	public static String loadPathResource(String path) {
		File directory = new File(".");
		String finalPath = "";
		try {
			finalPath = directory.getCanonicalPath() + "\\resource" + path;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return finalPath;
	}
	
	public static String loadStaticPath(String path) {
		return loadPathResource("\\static\\" + path);
	}

}
