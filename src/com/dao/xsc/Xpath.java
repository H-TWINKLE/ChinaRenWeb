package com.dao.xsc;


public class Xpath {

	public static Xpath instance = new Xpath();

	private Xpath() {
	}

	public static Xpath getInstance() {
		return instance;
	}

	/*private URL uri = null;

	private StringBuffer getCode() throws Exception {

		StringBuffer resultBuffer = null;

		URL uri = new URL(InitString.xscCodeUrl);

		URLConnection conn = uri.openConnection();

		conn.setUseCaches(false);
		conn.setConnectTimeout(5000); // ����ʱʱ��

		conn.connect();

		// 4.2��ȡ��Ӧ����
		BufferedReader reader = null;
		resultBuffer = new StringBuffer();
		String tempLine = null;

		reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((tempLine = reader.readLine()) != null) {
			resultBuffer.append(tempLine);
		}

		reader.close();

		return resultBuffer;

	}
	*/
	
	
	

}
