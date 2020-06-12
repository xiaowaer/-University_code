
package com.task2;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.XMLmine.XMLFile;
import com.admin.User;

import com.task3.ResumeBasicinfo;



public class ApplicantDAO {
	public static int a=1;
	
	/*
	 * 验证邮箱是否已被注册
	 */
	@SuppressWarnings("null")
	public boolean isExistApplicantname(String applicantname) {
		//XML实现
		//等价于连接上了对应的表
		XMLFile xmlFile=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/applicants.xml");
//		Connection conn = DBUtil.getConnection();
//		System.out.println("已经链接sql");
		
		 List e=xmlFile.getDocument().selectNodes("//"+"applicantname");
		 if(e.size()==0){
			 return false;
		 }
		 if (e.size()!=0){
		 for( int i=0;i<e.size();i++){
			 //当前项
			 Element element=(Element)e.get(i);
			if(element.getText().equals(applicantname)==true){
				return true;
			}
			 }
		 }
		 return false;
		 }
		
		

	
	/*
	 * 保存注册信息
	 */
	public void save(String applicantname, String password) {
		
	XMLFile xmlFile=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/applicants.xml");
	
	
	 //获取xml文件
	 if(xmlFile.getXmlFile().exists()){
	 xmlFile.getXml4j(xmlFile.getXmlFilepath());
	 }else {
		System.out.println("请勿在不存在的xml文件中添加节点。");
	 }
	 
	 
	//创建根节点
			Element newapplicant=DocumentHelper.createElement("applicant");
			Element newapplicantname=DocumentHelper.createElement("applicantname");
			Element newapplicantid=DocumentHelper.createElement("applicantid");
			newapplicantid.setText(String.valueOf(a+applicantname));
			newapplicantname.setText(applicantname);
			Element newpassword=DocumentHelper.createElement("password");
			newpassword.setText(password);
			newapplicant.add(newapplicantname); 
			newapplicant.add(newpassword); 
			newapplicant.add(newapplicantid); 
			
				//添加层次关系,到内存中，添加根节点
				xmlFile.getDocument().getRootElement().add(newapplicant);
	 
	 
		
	//保存文件
	try {
		xmlFile.saveXmlFile(xmlFile);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
		
		
//		// TODO Auto-generated method stub
//		Connection conn = DBUtil.getConnection();
//		PreparedStatement pstmt = null;
//		String sql = "insert into tb_applicant(applicant_email, applicant_pwd)"
//				+ "values(?,?)";
//		try{
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//			
//			pstmt.executeUpdate();
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally{
//			DBUtil.closeJDBC(conn, pstmt, null);
//		}

	}


	public int login(String applicantname, String password) {
	//login的xml实现	
	
		
		
		// TODO Auto-generated method stub
		int applicantID = 0;
		XMLFile xmlFile=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/applicants.xml");
		
		List<Element> e=xmlFile.getDocument().selectNodes("//applicantname");
		
		System.out.println(e.size());
		
		for(int i=0;i<e.size();i++){
			System.out.println(e.get(i).getText()+"  "+applicantname);
		if(e.get(i).getText().equals(applicantname)){
			System.out.println("用户:"+applicantname+"登录成功");	
			//applicantID= Integer.parseInt(e.get(i).elementIterator().next().("applicantid").getText());
		//	System.out.println(e.get(1).selectSingleNode("//applicantname//following-sibling::password").getText());
			Element okapplicantname=e.get(i);
			List<Element> esonpwd=okapplicantname.selectNodes("//applicantname//following-sibling::password");
			List<Element> esonid=okapplicantname.selectNodes("//applicantname//following-sibling::applicantid");
			if(esonpwd.get(i).getText().equals(password)){
				System.out.println(esonpwd.get(i).getText()+"=?"+password);

				 applicantID=Integer.parseInt(esonid .get(i).getText());
			return 	 applicantID;
			}
			
			System.out.println(applicantID);
			}
			System.out.println(e.get(i).getText());	
		
		
		}
		//用户不存在或密码错
		return applicantID;
		
		
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "select applicant_id from tb_applicant where applicant_email = ? and applicant_pwd = ?";
//		//看邮箱和密码所对应的id是什么？
//		try{
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//
//			rs = pstmt.executeQuery();
//			if(rs.next()){
//				applicantID = rs.getInt("applicant_id");
//				System.out.println("用户:"+email+"登录成功");
//			}
//				
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally{
//			DBUtil.closeJDBC(conn, pstmt, rs);
//		}
//		
//		if(applicantID == 0){
//		//用户存在但是密码输入错误
//		Connection conn1 = DBUtil.getConnection();
//		PreparedStatement pstmt1 = null;
//		ResultSet rs1 = null;
//		String sql1 = "select applicant_id from tb_applicant where applicant_email = ?";
//		try {
//			pstmt1 = conn1.prepareStatement(sql1);
//			pstmt1.setString(1, email);
//			rs1 = pstmt1.executeQuery();
//			
//			if(rs1.next()){
//				applicantID = -1;
//				System.out.println("密码输入错误");
//			}
//		} catch (SQLException e) {
//			// TODO: handle exception
//		}finally {
//			DBUtil.closeJDBC(conn1, pstmt1, rs1);
//		}
//		}
//		//用户不存在 返回0 提示注册
//		return applicantID;
	}
	
	
	
	public List<User> selectAll(){
		
		List<User> list = new ArrayList<User>();
		
	XMLFile xmlFile_r=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/resumebasicinfo.xml");

		 List<Element> e_r=xmlFile_r.getDocument().selectNodes("//"+"basicinfoid");

			for(int i=0;i<e_r.size();i++){
				User user=new User();

				Element okbasicinfoid=e_r.get(i);
				List<Element> ebroname=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::name");

				int length = e_r.get(i).getText().length();
				user.setApplicantname(e_r.get(i).getText().substring(length-3,length));
				user.setName(ebroname.get(i).getText());
			
				list.add(user);
	}
		return list;
			
//		Connection conn = DBUtil.getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql="select * "
//				+ "from tb_applicant,tb_resume_basicinfo where tb_resume_basicinfo.applicant_id = tb_applicant.applicant_id";
//		try{
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			while(rs.next()){
//				User user = new User();
//				user.setApplicantId(rs.getInt(1));
//				user.setApplicantEmail(rs.getString(2));
//				user.setName(rs.getString("name"));
//				list.add(user);
//			}
//			return list;
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally{
//			DBUtil.closeJDBC(conn, pstmt, rs);
//		}
	}

	public List<User> selectById(String applicantname) {
		
		List<User> list = new ArrayList<User>();
		
	XMLFile xmlFile_r=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/resumebasicinfo.xml");

		 List<Element> e_r=xmlFile_r.getDocument().selectNodes("//"+"basicinfoid");

			for(int i=0;i<e_r.size();i++){
				if(e_r.get(i).getText().equals("1"+applicantname)){
				User user=new User();

				Element okbasicinfoid=e_r.get(i);
				List<Element> ebroname=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::name");

				int length = e_r.get(i).getText().length();
				user.setApplicantname(e_r.get(i).getText().substring(length-3,length));
				user.setName(ebroname.get(i).getText());
			
				list.add(user);
				}
				
	}
		return list;
	}
	
	
	
	//这个要把账号表和个人信息表一起删除了
	
	public int delete(String id){
//		1.获取文件
		XMLFile xmlFile=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/applicants.xml");

		//2.删除指定的元素

		 List<Element> e=xmlFile.getDocument().selectNodes("//"+"applicantname");

		 System.out.println(e.size());
			
			for(int i=0;i<e.size();i++){
				System.out.println( e.get(i).getText()+"=? "+id);
			if(e.get(i).getText().equals(id)){
				System.out.println("basicinfoid");		
				
				e.get(i).getParent().getParent().remove(e.get(i).getParent());
				
			}
		
	}
	
		//3.保存文件
		try {
			xmlFile.saveXmlFile(xmlFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 1;


	}

}
