package util;

import java.util.Random;

public class HelpUtil {
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt(max-min);
	    return randomNum;
	}
}
