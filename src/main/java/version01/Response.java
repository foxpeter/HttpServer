package version01;

import java.io.OutputStream;

public class Response {

	public static final int BUFFERED_SIZE = 1024;
	private Request request;
	private OutputStream out;
	
	public Response(OutputStream out) {
		this.out = out;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() {
		
	}

}
