private static String calculateHashCode(String s) {
		String res = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(s.getBytes());
			byte[] mdbytes = md.digest();
			res = hexToString(mdbytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return res;
	}

public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}