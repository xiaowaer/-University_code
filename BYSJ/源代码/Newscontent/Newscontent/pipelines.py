# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
from scrapy.pipelines.files import FilesPipeline
from twisted.enterprise import adbapi
from Newscontent.data.bldbhelper import BLDBHelper
#
from Newscontent.data.dbhelper import DBHelper
from scrapy.http import Request

class VideoPipeline(FilesPipeline):
    def get_media_requests(self, item, info):
        # 处理对象:每组item中的每张图片
        for video_url in item['file_urls']:
            yield Request(video_url, meta={'item': item})

    def file_path(self, request, response=None, info=None):
        image_name = request.meta['item']['name']
        videooriginurl =request.url
        self.db = BLDBHelper()
        videoidt = self.db.selectVideobyurl(videooriginurl)
        videoidI=videoidt[0][0]
        videoid= str(videoidI)
        print(videoidI)
        print(type(videoidI))
        mp4='.mp4'
        print(type(mp4))
        videoname=videoid+mp4
        print(videoname)
        path = image_name + '/'+videoname
        print(path)
        print("!!!!!!!!!!!!!!!!!!!!!!!!")
        return path


class MysqlTwistedPipeline(object):

    def __init__(self):
        self.db = DBHelper()

    def process_item(self, item, spider):
        # 插入数据库
        self.db.insert(item,spider)
        return item

class MysqlupdatecontentPipeline(object):

    def __init__(self):
        self.db = DBHelper()
    def process_item(self, item, spider):
        # 插入数据库
        self.db.update(item,spider)
        return item




