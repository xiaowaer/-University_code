# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy



class ExamplesItem(scrapy.Item):
    file_urls = scrapy.Field()  # 指定文件下载的连接
    name = scrapy.Field()      #文件下载完成后会往里面写相关的信息

class readertypeItem(scrapy.Item):
    readerid = scrapy.Field()
    readertype = scrapy.Field()
    level = scrapy.Field()
    priority = scrapy.Field()

    def get_insert_sql(self):
        insert_sql="insert into readertype(readerid,readertype,level,priority) VALUES (%s,%s,%s,%s)"
        params = (
            self['readerid'], self['readertype'], self['level'], self['priority']
        )
        return insert_sql, params

class userItem(scrapy.Item):
    nick = scrapy.Field()
    password = scrapy.Field()
    head = scrapy.Field()
    usertype = scrapy.Field()

    def get_insert_sql(self):
        insert_sql="insert into user(nick,password,head,usertype) VALUES (%s,%s,%s,%s)"
        params = (
            self['nick'], self['password'], self['head'], self['usertype']
        )
        return insert_sql, params

class readerItem(scrapy.Item):
    nick = scrapy.Field()
    birthday = scrapy.Field()
    telephone = scrapy.Field()
    work = scrapy.Field()
    sex= scrapy.Field()
    likeread = scrapy.Field()
    city = scrapy.Field()
    intro = scrapy.Field()

    def get_insert_sql(self):
        insert_sql="insert into reader(nick,birthday,telephone,likeread,sex,work,city,intro) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)"
        params = (
            self['nick'], self['birthday'], self['telephone'], self['likeread'],self['sex'],self['work'],  self['city'],self['intro']
        )
        return insert_sql, params


class picscontentItem(scrapy.Item):
    picsurl = scrapy.Field()
    picscontent = scrapy.Field()
    def get_update_sql(self):
        update_sql = "update pictures set picscontent = %s where picsoriginurl=%s"
        params = (
            self['picscontent'], self['picsurl']
        )
        return update_sql, params

class newscontentItem(scrapy.Item):
    newsurl = scrapy.Field()
    newscontent=scrapy.Field()
    def get_update_sql(self):
        update_sql="update news set newscontent = %s where newsoriginurl=%s"
        params = (
            self['newscontent'], self['newsurl']
        )
        return  update_sql, params





class VideoItem(scrapy.Item):
    # 视频标题
    videoname = scrapy.Field()
    # 视频来源url
    videooriginurl = scrapy.Field()
    # 视频来源
    videoorigin = scrapy.Field()
    # 视频关键字
    videokeyword = scrapy.Field()
    #频道
    cid= scrapy.Field()
    #视频首图
    videoheadurl = scrapy.Field()
    #视频爬取时间
    videoctime = scrapy.Field()

    def get_insert_sql(self):
        insert_sql = "insert into video(videoname,cid,videoorigin,videokeyword,videoheadurl,videooriginurl,videoctime) VALUES (%s,%s,%s,%s,%s,%s,%s)"
        params = (
            self['videoname'], self['cid'], self['videoorigin'], self['videokeyword'], self['videoheadurl'], self['videooriginurl'],self['videoctime']
        )
        return insert_sql, params

class PicsItem(scrapy.Item):
    # 页面标题
    picsname = scrapy.Field()
    # 分类id
    cid = scrapy.Field()
    # 页面来源，可以分成多个来源爬取
    picsorigin = scrapy.Field()
    # 关键词
    picskeyword = scrapy.Field()
    picsheadurl = scrapy.Field()
    picsoriginurl = scrapy.Field()
    picsctime= scrapy.Field()

    def get_insert_sql(self):
        insert_sql = "insert into pictures(picsname,cid,picsorigin,picskeyword,picsheadurl,picsoriginurl,picsctime) VALUES (%s,%s,%s,%s,%s,%s,%s)"
        params = (
            self['picsname'], self['cid'], self['picsorigin'], self['picskeyword'], self['picsheadurl'], self['picsoriginurl'],
            self['picsctime']
        )
        return insert_sql, params

class NewsItem(scrapy.Item):
    # 页面标题
    newstitle = scrapy.Field()
    # 分类id
    cid = scrapy.Field()
    # 页面来源，可以分成多个来源爬取
    newsorigin = scrapy.Field()
    # 关键词
    newskeyword = scrapy.Field()
    newsheadurl = scrapy.Field()
    newsoriginurl = scrapy.Field()
    newsctime = scrapy.Field()

    def get_insert_sql(self):
        insert_sql = "insert into news(newstitle,cid,newsorigin,newskeyword,newsheadurl,newsoriginurl,newsctime) VALUES (%s,%s,%s,%s,%s,%s,%s)"
        params = (
            self['newstitle'], self['cid'], self['newsorigin'], self['newskeyword'], self['newsheadurl'],
            self['newsoriginurl'],
            self['newsctime']
        )
        return insert_sql, params



