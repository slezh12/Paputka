package JavaPackage;

public class TimeChange {

	public static String getCorrectDate(String time) {
		String result = "";
		int count = 0;
		String day = "";
		String month = "";
		String year = "";
		for (int i = 0; i < time.length(); i++) {
			if (time.charAt(i) == '-') {
				count++;
			} else if (count == 0) {
				day += time.charAt(i);
			} else if (count == 1) {
				month += time.charAt(i);
			} else if (count == 2) {
				year += time.charAt(i);
			}
		}
		result = year + "-" + month + "-" + day;
		return result;
	}
	public static String getCorrectDateForFacebook(String time) {
		String result = "";
		int count = 0;
		String day = "";
		String month = "";
		String year = "";
		for (int i = 0; i < time.length(); i++) {
			if (time.charAt(i) == '/') {
				count++;
			} else if (count == 0) {
				month += time.charAt(i);
			} else if (count == 1) {
				day += time.charAt(i);
			} else if (count == 2) {
				year += time.charAt(i);
			}
		}
		result = year + "-" + month + "-" + day;
		return result;
	}

}
