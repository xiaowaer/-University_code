
import pymysql
from twisted.enterprise import adbapi
from scrapy.utils.project import get_project_settings  #导入seetings配置
from twisted.internet import defer

class DBHelper():
    '''这个类也是读取settings中的配置，自行修改代码进行操作'''
    def __init__(self):
        settings = get_project_settings()  # 获取settings配置，设置需要的信息
        dbparams = dict(
            host=settings['MYSQL_HOST'],  # 读取settings中的配置
            db=settings['MYSQL_DBNAME'],
            user=settings['MYSQL_USER'],
            passwd=settings['MYSQL_PASSWORD'],
            charset='utf8',  # 编码要加上，否则可能出现中文乱码问题
            cursorclass=pymysql.cursors.DictCursor,
            use_unicode=True,
        )
        # **表示将字典扩展为关键字参数,相当于host=xxx,db=yyy....
        dbpool = adbapi.ConnectionPool('pymysql', **dbparams)
        self.dbpool = dbpool

    def connect(self):
        return self.dbpool
        # 创建数据库


    def insert(self,item,spider):
        query = self.dbpool.runInteraction(self.do_insert, item)
        query.addErrback(self._handle_error,item,spider)  # 调用异常处理方法
        return item


    def do_insert(self,cursor,item):
        # 执行具体的插入
        # 根据不同的item 构建不同的sql语句并插入到mysql中
        insert_sql,params = item.get_insert_sql()
        cursor.execute(insert_sql, params)
        # 错误处理方法

    def update(self,item,spider):
        query = self.dbpool.runInteraction(self.do_update, item)
        query.addErrback(self._handle_error,item,spider)  # 调用异常处理方法
        return item

    def do_update(self, cursor, item):
        # 执行具体的插入
        # 根据不同的item 构建不同的sql语句并插入到mysql中
        update_sql, params = item.get_update_sql()
        cursor.execute(update_sql, params)
        # 错误处理方法


    def _handle_error(self, failue, item, spider):
        print('--------------database operation exception!!-----------------')
        print(failue)

