package Utilities;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

}
