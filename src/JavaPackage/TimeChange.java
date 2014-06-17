package JavaPackage;

public class TimeChange {

	public static String getCorrectDate(String time) {
		String result = "";
		int count = 0;
		String first = "";
		String second = "";
		String third = "";
		for (int i = 0; i < time.length(); i++) {
			if (time.charAt(i) == '-') {
				count++;
			} else if (count == 0) {
				first += time.charAt(i);
			} else if (count == 1) {
				second += time.charAt(i);
			} else if (count == 2) {
				third += time.charAt(i);
			}
		}
		result = third + "-" + second + "-" + first;
		return result;
	}

}
