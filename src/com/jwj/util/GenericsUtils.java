package com.jwj.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenericsUtils {

	private static GenericsUtils instance = new GenericsUtils();

	private GenericsUtils() {
	}

	public static GenericsUtils getInstance() {
		return instance;
	}

	public Object autoBean(Class<?> clazz, ResultSet rs)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		ArrayList<Method> Methods = new ArrayList<Method>(); // ������ȡ�����ļ���
		Object obj = clazz.newInstance(); // ʵ��������
		try {
			Method[] method = clazz.getMethods();// �����е����з���
			for (Method m : method) {
				if (m.getName().substring(0, 3).contains("set"))// ��ȡǰ��3��Ӣ���ַ������Ƿ�Ϊset��������ݶ�Ϊset����
					Methods.add(m);// ��set�������뼯��
			}
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int columCount = rsmd.getColumnCount(); // ��ȡ������еĸ���
			// ѭ����Ŀ��Ϊ��ÿһ�е�ֵ����ȷ��set����
			for (int i = 1; i <= columCount; i++) {
				String columName = rsmd.getColumnName(i);// ��ȡ����
				String field = columName.replaceFirst(columName.substring(0, 1),
						columName.substring(0, 1).toUpperCase());// ��Ϊset&&get��������������ʽ��setXxxx&&getXxxx����˽�������һ����ĸ��д
				for (Method m1 : Methods) {
					if (m1.getName().equals("set" + field)) {// �жϣ����ͨ���жϼȿ�֪�����ö����ʲôset����
						Class<?> para[] = m1.getParameterTypes();// ��ȡ�����������ͣ��ж��Ƿ�ΪΪsetBoolean
						if (para[0].getName().equals("boolean")) {
							if (rs.getObject(columName).equals(0)) {// ��Ϊmysql��boolean����0,1���ڣ������������ж�
								m1.invoke(obj, false);
							} else {
								m1.invoke(obj, true);
							}
						} else {// �������setBoolean���֣���ִ��һ��
							// System.out.println(columName + ":" + rs.getObject(columName));
							m1.invoke(obj, rs.getObject(columName));// ͨ��������÷���
						}
					}
				} // ����set&&get��������
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;// ���ض���
	}

	/*
	 * @SuppressWarnings("rawtypes") public static List<String> getGenericPar(Class
	 * clazz) { List<String> list = new ArrayList<>();
	 * 
	 * Field[] field = clazz.getDeclaredFields(); for(Field fields:field) {
	 * list.add(fields.toString().substring(fields.toString().lastIndexOf(".")+1));
	 * }
	 * 
	 * return list; }
	 */

	@SuppressWarnings("rawtypes")
	public static String genGenericName(Class clazz) {
		return clazz.getSimpleName();
	}

}
