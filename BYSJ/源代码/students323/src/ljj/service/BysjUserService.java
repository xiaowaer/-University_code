package ljj.service;

import java.util.List;
import ljj.pojo.BysjUser;


public interface BysjUserService{

    public List<BysjUser> findBysjUser(String nick, String password); 
    public List<BysjUser> findAllreader(); 
	public void insertReader(String nick, String password,String head, String usertype);
	public List<BysjUser> findBysjUserByNick(String nick);
	public List<BysjUser> findAdministrator(String nick, String password);
	public int insertreaderheaderimg(String nick, String headerimgPath);
	public BysjUser finduserBynick(String nick);
	public int deleteUser(String nick);

	
}

