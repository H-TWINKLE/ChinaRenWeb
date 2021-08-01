package com.jwj.init;

public interface InitString {

	public static final String RadomString = "abcdefghijklmnopqrstuvwxyz0123456789";

	public static final String emailTomcatUrl = "http://119.29.98.60:8080/ChinaRen/";

	public static final String emailLocalUrl = "http://localhost:8080/ChinaRen/";

	public static final String xscCodeUrl = "http://xsc.cuit.edu.cn/Sys/default3.html";

	public static final String tb_user = "user";

	public static final String getUser = "SELECT user.id,\r\n" + "       user.admin,\r\n" + "       user.name,\r\n"
			+ "       user.isadmin,\r\n" + "       user.point,\r\n" 
			+ "       user.nickname,\r\n" + "       user.sex,\r\n" + "       user.img,\r\n" + "       user.auto,\r\n"
			+ "       clazz.id 'c_id',\r\n" + "       clazz.clazzn,\r\n" + "       clazz.graden,\r\n"
			+ "       clazz.majorn,\r\n" + "       clazz.collegen,\r\n" + "       usermore.year,\r\n"
			+ "       usermore.tel,\r\n" + "       usermore.birth,\r\n" + "       usermore.live,\r\n"
			+ "       usermore.home\r\n" + "FROM user\r\n" + "JOIN clazz ON (user.clazz=clazz.id)\r\n"
			+ "JOIN usermore ON (user.usermore=usermore.id)\r\n" + "WHERE user.id=?;";

	public static final String Login = "SELECT user.id,\r\n" + "       user.admin,\r\n" + "       user.password,\r\n"
			+ "       user.isadmin,\r\n" + "       user.point,\r\n" 
			+ "       user.name,\r\n" + "       user.nickname,\r\n" + "       user.sex,\r\n" + "       user.dates,\r\n"
			+ "       user.img,\r\n" + "       user.auto,\r\n" + "       clazz.id 'c_id',\r\n"
			+ "       clazz.clazzn,\r\n" + "       clazz.graden,\r\n" + "       clazz.majorn,\r\n"
			+ "       clazz.collegen,\r\n" + "       usermore.year,\r\n" + "       usermore.tel,\r\n"
			+ "       usermore.birth,\r\n" + "       usermore.live,\r\n" + "       usermore.home\r\n" + "FROM USER\r\n"
			+ "JOIN clazz ON (USER.clazz=clazz.id)\r\n" + "JOIN usermore ON (USER.usermore=usermore.id)\r\n"
			+ "WHERE user.admin=?\r\n" + "  AND user.password=?;";

	public static final String ManageLogin = "SELECT * FROM manage WHERE admin=? AND password=?";
	
	public static final String findOneClazz = "SELECT * FROM clazz WHERE id=?";

	public static final String findClazzTable = "SELECT " + "       clazz.id 'c_id',\r\n" + "       clazz.clazzn,\r\n"
			+ "       clazz.graden,\r\n" + "       clazz.majorn,\r\n" + "       clazz.collegen,\r\n"+"       clazz.dates,\r\n"
			+"       clazz.auto\r\n"+ "FROM clazz\r\n"
			+ "WHERE clazz.clazzn=?\r\n" + "  AND clazz.graden=?\r\n" + "  AND clazz.majorn=?\r\n"
			+ "  AND clazz.collegen=?;";

	public static final String findUserTable = "SELECT user.id,\r\n" + "       user.name,\r\n"
			+ "       user.nickname,\r\n" + "       clazz.id 'c_id',\r\n" + "       clazz.clazzn,\r\n"
			+ "       clazz.graden,\r\n" + "       clazz.majorn,\r\n" + "       clazz.collegen\r\n" + "FROM user\r\n"
			+ "JOIN clazz ON (user.clazz=clazz.id)\r\n"
			+ "WHERE CONCAT(IFNULL(user.name,''),IFNULL(user.nickname,''),IFNULL(user.admin,''),"
			+ "IFNULL(clazz.clazzn,''),IFNULL(clazz.graden,''),IFNULL(clazz.majorn,''),"
			+ "IFNULL(clazz.collegen,'')) LIKE ?;";

	public static final String tb_register = "register";

	public static final String manageFindAll = "SELECT * FROM clazz";
	
	public static final String manageFindUserMess = "SELECT usermess.m_id,usermess.message,usermess.u_id,usermess.ru_id,u.name 'uname',"
			+ "ru.name 'runame',usermess.dates FROM usermess JOIN user u ON u.id=usermess.u_id JOIN user ru ON ru.id=usermess.ru_id"
			+ " WHERE u_id=ru_id";
	
	public static final String manageFindTip = "SELECT tip.id,tip.u_id,tip.content,tip.isread,tip.dates,user.name 'uname'"
			+ " FROM tip JOIN user  ON user.id=tip.u_id";
	
	public static final String manageOneTip = "SELECT tip.id,tip.u_id,tip.content,tip.isread,tip.dates,user.name 'uname'"
			+ " FROM tip JOIN user ON user.id=tip.u_id WHERE tip.u_id=? AND isread=0";

	public static final String Register = "INSERT INTO register(admin,code) VALUES(?,?)";
	
	public static final String getBestClazzAdmin = "SELECT id from user WHERE clazz=? ORDER BY point DESC";

	public static final String isAdminExist = "SELECT id from user WHERE clazz=? AND isadmin=1";
	
	public static final String upClazz = "UPDATE clazz SET clazzn=?,graden=?,majorn=?,collegen=?,dates=?,auto=? WHERE id=?";

	public static final String updateUserCode = "UPDATE user SET code=? WHERE admin=?";
	
	public static final String updateTips = "UPDATE tip SET isread=? WHERE id=?";

	public static final String findSome = "SELECT * FROM user WHERE admin=?";

	public static final String findRegister = "SELECT admin FROM register WHERE code=?";

	public static final String findUserCode = "SELECT * FROM user WHERE code=?";

	public static final String updatePass = "UPDATE user SET password=? WHERE admin=?";

	public static final String insertOneQues = "INSERT INTO userques(title,content,u_id,dates) VALUES(?,?,?,?)";
	
	public static final String tips = "INSERT INTO tip(u_id,content,isread,dates) VALUES(?,?,?,?)";
	
	public static final String insertOneQuesReplay = "INSERT INTO replayuq(ques_id,content,u_id,dates) VALUES(?,?,?,?)";

	public static final String RegisterUser = "INSERT INTO user(admin,password,dates,nickname,name) VALUES(?,?,?,?,?)";

	public static final String error_page = "chinaren-error.jsp";

	public static final String login_page = "chinaren-login.jsp";

	public static final String registerUser_page = "chinaren-registerinfo.jsp";

	public static final String updatePass_page = "chinaren-updatepass.jsp";

	public static final String UpdateUser = "UPDATE user ,usermore SET user.name =?,user.nickname=?,"
			+ "user.auto=?,usermore.tel=?,usermore.live=?,usermore.home=?\r\n"
			+ " WHERE user.usermore=usermore.id AND user.id=?;";

	public static final String UpdateUserRegist = "UPDATE user SET user.name =?,user.nickname=?,user.sex=?,"
			+ "user.clazz=?,user.usermore=?" + " WHERE user.admin=?;";

	public static final String UpdateImg = "UPDATE user SET img=? WHERE id=?";

	public static final String UploadClazzImg = "INSERT INTO clazzpic(clazz_id,pic,u_id,dates) VALUES(?,?,?,?)";

	public static final String uploadUserMess = "INSERT INTO usermess(message,u_id,ru_id,dates,pic) VALUES(?,?,?,?,?)";

	public static final String findAllOneQues = "SELECT * FROM userques WHERE u_id=? LIMIT ?,?";

	public static final String updateUserSC = "UPDATE user SET classid=?,school=? WHERE admin=?";

	public static final String findCollege = "SELECT * FROM clazz WHERE clazzn=? AND graden=? AND majorn=? AND collegen=?";

	public static final String setCollege = "INSERT INTO clazz(clazzn,graden,majorn,collegen) VALUES(?,?,?,?)";

	public static final String findUserMore = "SELECT * FROM usermore WHERE year=? AND tel=? AND birth=? AND live=? AND home=?";

	public static final String setUserMore = "INSERT INTO usermore(year,tel,birth,live,home) VALUES(?,?,?,?,?)";

	public static final String setUserFocus = "INSERT INTO userfocus(u_id,ru_id,dates) VALUES(?,?,?)";

	public static final String deleteUserFocus = "DELETE FROM userfocus WHERE id=?";

	public static final String JoinClazz = "UPDATE user SET clazz = ? WHERE id=?";

	public static final String fingAllM_C = "SELECT majorn,collegen FROM clazz";

	public static final String findCount = "SELECT count(*) 'count'  FROM user WHERE clazz=?\r\n" + "UNION ALL\r\n"
			+ "SELECT count(*) 'count'  FROM clazzpic WHERE clazz_id=?;";

	public static final String findClazzUser = "SELECT user.id,user.name,user.isadmin,user.nickname,usermore.tel FROM user "
			+ "JOIN usermore ON (USER.usermore=usermore.id) WHERE user.clazz=?";

	public static final String findAllQues = "SELECT user.id 'u_id',user.name,userques.q_id,userques.title,"
			+ "userques.content,user.dates FROM userques JOIN user ON (userques.u_id=user.id) LIMIT ?,?";

	public static final String deleteUserQues = "DELETE FROM userques WHERE q_id=?";

	public static final String findAllUserMess = "SELECT u.id 'u_id',u.name 'uname',ru.id 'ru_id',ru.name 'runame',"
			+ "usermess.m_id,usermess.message,"
			+ "usermess.dates FROM usermess JOIN user u ON (usermess.u_id=u.id) JOIN user ru ON (usermess.ru_id=ru.id)"
			+ " WHERE u_id!=ru_id";

	public static final String deleteUserMess = "DELETE FROM usermess WHERE m_id=?";

	public static final String setUserComplaint = "INSERT INTO usercomplaint(u_id,content,types,dates) VALUES(?,?,?,?)";

	public static final String findAllUserComplaint = "SELECT user.admin,usercomplaint.id,usercomplaint.u_id,usercomplaint.content,"
			+ "usercomplaint.types,usercomplaint.dates FROM usercomplaint JOIN user ON (user.id=usercomplaint.u_id)";

}
