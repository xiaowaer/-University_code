package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.BysjUser;

public interface BysjUserDao {
	public List<BysjUser> findBysjUser(@Param("nick") String username, @Param("password") String password);
    public List<BysjUser> findAllreader(); 
    public void insertReader(@Param("nick") String username, @Param("password") String password, @Param("head") String head, @Param("usertype")String usertype);
	public List<BysjUser> findBysjUserByNick(@Param("nick")String nick);
	public List<BysjUser> findAdministrator(@Param("nick")String nick,  @Param("password")String password);
	public int insertreaderheaderimg(@Param("nick")String nick, @Param("headerimgPath") String headerimgPath);
	public BysjUser finduserBynick(@Param("nick")String nick);
	public int deleteUser(@Param("nick")String nick);


}
