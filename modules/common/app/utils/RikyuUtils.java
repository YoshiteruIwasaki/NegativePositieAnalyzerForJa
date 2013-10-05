package utils;

import rikyu.Rikyu;
import rikyu.model.Sentence;

public class RikyuUtils {

	public static Sentence analyze(String string) {
		return Rikyu.analyze(string);
	}
}
