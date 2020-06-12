package ljj.service;

import java.util.List;

import ljj.pojo.Notice;
import ljj.pojo.PageInfo;
import ljj.pojo.ReaderAction;

public interface ReaderActionService {

public PageInfo<ReaderAction>  findPageInfo(String actor, Integer pageIndex, Integer pageSize);

public int addCollects(String actor, String pageurl);

public List<ReaderAction> findAction(String actor, String pageurl);

public int updateCollects(String actor, String pageurl);

public int deleteCollects(String actor, String pageurl);

public int insertZan(String actor, String pageurl);

public int deleteZan(String actor, String pageurl);

public int updateZan(String actor, String pageurl);

public int deleteActionBytarget(String targeturl);

public int deleteActionByreader(String nick);





	

}
