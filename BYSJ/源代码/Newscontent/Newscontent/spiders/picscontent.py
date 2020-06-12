import json
import scrapy
from scrapy.spiders import Spider
from scrapy.selector import Selector

from Newscontent.data.bldbhelper import BLDBHelper
from Newscontent.items import picscontentItem


class NewscontentSpider(Spider):
    # 爬虫名称
    p=""
    name = 'picscontent'
    # 起始url地址
    start_urls = ['https://photo.sina.cn/']

    page_num="0"

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
        # print(response.text)
        self.db = BLDBHelper()
        a = self.db.selectpicsurl()
        print(a[1][0])
        print(type(a[1][0]))
        j = 0
        for i in a:
            contenturl = a[j][0]
            # date=contentid[0:8]
            j = j + 1

            # print(contentid)
            # print(date)
            # print(contenturl)
            yield scrapy.Request(url=contenturl+"?vt=4&hd=1", callback=self.parse_content_url)
        # yield scrapy.Request(url="https://photo.sina.cn/album_24_86328_133303.htm", callback=self.parse_content_url)


    def parse_content_url(self, response):
       print(response.text)
       # body = response.body.decode('GBK')
       newcontentp= Selector(text=response.text).xpath('//section[@class="section-item"]').extract()
       newcontenth1= Selector(text=response.text).xpath('//h1[1]/text()').extract()
       print(newcontentp)
       print(type(newcontentp))

       temh1=self.p.join(item for item in newcontenth1)
       temp=self.p.join(item for item in newcontentp)
       print(temp)
       print(type(temp))

       temp2=temp.replace('src="data:','id="')
       temp1 = temp2.replace("data-src", "src")


       print(temp2)
       item = picscontentItem()
       tempurl=response.url
       item["picsurl"] = tempurl.split('?')[0]
       item["picscontent"] = temp1
       yield item






