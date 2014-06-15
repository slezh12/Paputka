package JavaPackage;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;


public class ParseInfo {
	protected BasicDataSource source;
	
	public ParseInfo(BasicDataSource source) {
		this.source = source;
	}

}
