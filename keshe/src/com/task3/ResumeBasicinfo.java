
package com.task3;


public class ResumeBasicinfo {
	
	@Override
	public String toString() {
		return "ResumeBasicinfo [basicinfoID=" + basicinfoID + ", name=" + name + ", phone=" + phone + ", email="
				+ email + ", address=" + address + ", idcard=" + idcard + ", bumen=" + bumen + ", zhicheng=" + zhicheng
				+ ", sex=" + sex + "]";
	}

	public int  basicinfoID;
	
	public String name;
	
	public String phone;
	
	public String email;
	
	public String address;
	
	public String idcard;
	
	public String bumen;
	
	public String zhicheng;
	
	public String sex;



	public ResumeBasicinfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ResumeBasicinfo(int basicinfoID, String name, String phone, String email, String address, String idcard,
			String bumen, String zhicheng, String sex) {
		super();
		this.basicinfoID = basicinfoID;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.idcard = idcard;
		this.bumen = bumen;
		this.zhicheng = zhicheng;
		this.sex = sex;
	}


	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}

	public String getZhicheng() {
		return zhicheng;
	}

	public void setZhicheng(String zhicheng) {
		this.zhicheng = zhicheng;
	}

	public String getSex() {
		return sex;
	}

	public int getBasicinfoID() {
		return basicinfoID;
	}


	public void setBasicinfoID(int basicinfoID) {
		this.basicinfoID = basicinfoID;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}





}
