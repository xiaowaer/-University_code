import json
import scrapy
from scrapy.spiders import Spider
import datetime

from Newscontent.data.bldbhelper import BLDBHelper
from Newscontent.items import VideoItem


class HaokanspSpider(Spider):
    # 爬虫名称
    name = 'haokansp'
    # 起始url地址
    start_urls = ['https://baidu.com']


    custom_settings = {
        'CONCURRENT_REQUESTS': 64,
        'DOWNLOAD_DELAY': 0,
        'COOKIES_ENABLED': False,
        'RETRY_TIMES': 15,
        'DEFAULT_REQUEST_HEADERS': {
            'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
            'accept-encoding': 'gzip, deflate, br',
            'accept-language': 'zh-CN,zh;q=0.9,en;q=0.8',
            'cache-control': 'max-age=0',
        },
    }

    # 处理 start_urls 对应的响应
    def parse(self, response):
        self.db = BLDBHelper()
        a = self.db.selectenamebyvideo()
        print(a[1][0])
        print(type(a[1][0]))
        j = 0
        baseurl = "https://haokan.baidu.com/videoui/api/videorec?tab="
        baseurl2 = "&act=pcFeed&pd=pc&num=20&shuaxin_id=1587787443537"
        for i in a:
            tyasf = a[j][0]
            print(a[j][0])
            j = j + 1
            yield scrapy.Request(url=baseurl+str(a[j][0])+baseurl2, callback=self.parse_format2_url)
        # yield scrapy.Request(url=baseurl + "junshi" + baseurl2, callback=self.parse_format2_url)

    def parse_format2_url(self,response):
         # print(response.text)
         haokanspurllist_dict = json.loads(response.text)
         ename=response.url[50:-49]
         cid=self.db.selectcidforename(ename)

         if haokanspurllist_dict.get("errno") == 0:
             data = haokanspurllist_dict.get('data')
             print(data)
             for name, info in haokanspurllist_dict.items():
                 print(name)
                 if name =="data":
                     print("****一如果一级元素名为data*****")
                     for key, value in info.items():
                         print("****打印data下一级的键值对（二级）*****")
                         print(key ,':',value)
                         if key=="response":
                            for ikey, ivalue in value.items():
                                print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                                print(ikey, ':', ivalue)

                                item = VideoItem()
                                item["videoorigin"] = "hksp"
                                item["cid"] =str(cid[0][0])
                                item["videoctime"] = datetime.datetime.now()
                                for j in ivalue:
                                        for jkey, jvalue in j.items():
                                             print("****打印好看视频页面列表的视频项内容*****")
                                             print(jkey, ':', jvalue)
                                             if jkey == "title":
                                                 item["videoname"]=jvalue
                                             if jkey == "poster":
                                                 item["videoheadurl"]=jvalue
                                             if jkey == "url":
                                                 item["videooriginurl"]=jvalue+"&"
                                             if jkey == "source_name":
                                                 item["videokeyword"]=jvalue
                                        yield item
