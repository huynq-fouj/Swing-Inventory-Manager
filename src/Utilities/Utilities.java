package Utilities;

import net.htmlparser.jericho.CharacterReference;

public class Utilities {

	public static String encode(String str_unicode) {
		return CharacterReference.encode(str_unicode);
	}
	
	public static String decode(String str_html) {
		return CharacterReference.decode(str_html);
	}
	
}
