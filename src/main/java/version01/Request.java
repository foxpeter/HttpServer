package version01;

import java.io.IOException;
import java.io.InputStream;

public class Request {
	private InputStream inputStream;
	private String uri;
	public Request(InputStream in) {
		inputStream = in;
	}

	public void parse() {
		StringBuilder sb = new StringBuilder(2048);
		byte[] bytes = new byte[2048];
		int i = -1;
		try {
			i = inputStream.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int j = 0; j < i; j++){
			sb.append((char)bytes[j]);
		}
		uri = parseURI(sb.toString());
	}
	private String parseURI(String reqStr){
		int index1 = reqStr.indexOf(" ");
		if(index1 > 0){
			int index2 = reqStr.indexOf(" ", index1+1);
			if(index2 > index1){
				return reqStr.substring(index1+1,index2);
			}
		}
		return null;
	}
	public String getURI() {
		return uri;
	}

}
