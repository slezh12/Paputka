package JavaPackage;

public class TimeChange {

	/**
	 * Returns String of Time
	 *
	 *            
	 * @param time
	 * 			time represented as string
	 * 
	 * @return string
	 * 			parsed time
	 */
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
	

	/**
	 * Returns String of Time
	 *
	 *            
	 * @param time
	 * 			time represented as string
	 * 
	 * @return string
	 * 			parsed time into string
	 */
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
