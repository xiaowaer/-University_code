package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ljj.dao.BysjUserDao;
import ljj.pojo.BysjUser;

@Service("bysjuserService")
public class BysjUserServiceImpl implements BysjUserService {

	@Autowired
    private BysjUserDao  BysjUserDao;

	@Override
	public List<BysjUser> findBysjUser(String nick, String password) {
		return BysjUserDao.findBysjUser(nick,password);
	}

	
	public List<BysjUser> findAllreader() {
		return BysjUserDao.findAllreader();
	}


	@Override
	public void insertReader(String nick, String mima,String head,String usertype) {
		 BysjUserDao.insertReader(nick, mima,head,usertype);
		 
	}


	@Override
	public List<BysjUser> findBysjUserByNick(String nick) {
		// TODO Auto-generated method stub
		return BysjUserDao.findBysjUserByNick(nick);
	}


	@Override
	public List<BysjUser> findAdministrator(String nick, String password) {
		// TODO Auto-generated method stub
			return BysjUserDao.findAdministrator(nick,password);
	}


	@Override
	public int insertreaderheaderimg(String nick, String headerimgPath) {
		// TODO Auto-generated method stub
		
		return BysjUserDao.insertreaderheaderimg(nick,headerimgPath);
		
	}


	@Override
	public BysjUser finduserBynick(String nick) {
		// TODO Auto-generated method stub
		return BysjUserDao.finduserBynick(nick);
	}


	@Override
	public int deleteUser(String nick) {
		// TODO Auto-generated method stub
		return BysjUserDao.deleteUser(nick);
	}
	
}

