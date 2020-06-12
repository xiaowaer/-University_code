import pymysql
class BLDBHelper():
    def select(self):
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        sql = "select * from user"
        cursor.execute(sql)
        a=cursor.fetchall()
        print(a)
        cursor.close()
        db.close()

    def selectVideo(self,cid):
        sql = "select videooriginurl from video where cid='%s'" % (cid)
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a

    def selectVideobyurl(self,videooriginurl):
        sql = "select videoid from video where videooriginurl='%s'" % (videooriginurl)
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a

    def selectenameforxltp(self):
        sql = "select ename from categories where origin='xltp'"
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a



    def selectenamebyvideo(self):
        sql = "select ename from categories where origin='hksp'"
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a

    def selectnewsename(self):
        sql = "select ename from categories where origin='txnews'"
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a

    def selectcidforename(self,ename):
        sql = "select cid from categories where ename= '%s'" %(ename)
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a

    def selectcidforchname(self, chname):
        sql = "select cid from categories where chname= '%s'" % (chname)
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a


    def selectnewsurl(self):
        sql = "select newsoriginurl from news "
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a

    def selectpicsurl(self):
        sql = "select picsoriginurl from pictures where picsorigin= 'xltp'"
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a








    def inserthaokan1(self,seed,formatp,varlist):
        print(seed)
        print(formatp)
        print(varlist)
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        sql = "insert into seed(formatid,status,seedURL,varlist) values('%d','%s','%s','%s')" %(int(formatp),"0",seed,varlist)
        cursor = db.cursor()
        try:
            cursor.execute(sql)
            db.commit()  # 提交到数据库执行，一定要记提交哦
        except Exception:
            db.rollback()  # 发生错误时回滚
        cursor.close()
        db.close()

    def insertseed(self,seed,formatp):
        print(seed)
        print(formatp)
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        sql = "insert into seed(formatid,status,seedURL) values('%d','%s','%s')" %(int(formatp),"0",seed)
        cursor = db.cursor()
        try:
            cursor.execute(sql)
            db.commit()  # 提交到数据库执行，一定要记提交哦
        except Exception:
            db.rollback()  # 发生错误时回滚
        cursor.close()
        db.close()


    def selectreaderid(self):
        sql = "select readerid from reader "
        db = pymysql.connect(host='localhost', port=3306, user='root', passwd='lijunjie123', db='bysj', charset='utf8')
        cursor = db.cursor()
        cursor.execute(sql)
        a = cursor.fetchall()
        print("************************************")
        print(a)
        print(type(a))
        cursor.close()
        db.close()
        return a

