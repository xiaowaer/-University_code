package ljj.service;

import java.util.Date;
import java.util.List;

import ljj.pojo.Notice;
import ljj.pojo.PageInfo;

public interface NoticeService {

	int addNotice(Notice notice);

	PageInfo<Notice> findPageInfo(String publisher, String noticetitle, Integer pageIndex, Integer pageSize);

	List<Notice> findAllnotices();

}
