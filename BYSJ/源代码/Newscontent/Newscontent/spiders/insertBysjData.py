
import scrapy
from scrapy.spiders import Spider
from Newscontent.data.bldbhelper import BLDBHelper
from Newscontent.items import readerItem
from Newscontent.items import userItem
from Newscontent.items import readertypeItem
class InsertBysjDataSpider(Spider):
    # 爬虫名称
    name = 'InsertBysjData'
    # 起始url地址
    start_urls = ['http://baidu.com/']

    custom_settings = {
        'CONCURRENT_REQUESTS': 16,
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

    # # 处理 start_urls 对应的响应
    # def parse(self, response):
    #     for i in range(60):
    #         item = readerItem()
    #         item1= userItem()
    #         item1["nick"]="匿名用户" + str(i)
    #         item1["password"]="123"
    #         item1["head"] = "https://xiaoliwaer.top:525/headerimg/temp.jpg"
    #         item1["usertype"] = "r"
    #         item["nick"] = "匿名用户" + str(i)
    #         item["birthday"] = "1998-01-25"
    #         item["telephone"] = "13656252033"
    #         item["sex"]= "男"
    #         item["work"]= "程序员"
    #         item["likeread"] = "默认"
    #         item["city"] = "广东东莞"
    #         item["intro"] = "无"
    #         print(str(i))
    #         yield item1
    #         yield item

    def parse(self, response):
        self.db = BLDBHelper()
        a = self.db.selectreaderid()
        print(a[1][0])
        j = 0
        for i in a:
            readerid = a[j][0]
            print(readerid)
            item = readertypeItem()
            item["readertype"] = "默认"
            item["readerid"]= int(readerid)
            item["level"] = "1"
            item["priority"] = "A"
            j = j + 1
            yield item



