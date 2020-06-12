import json
import scrapy
from scrapy.spiders import Spider
from scrapy.selector import Selector

from Newscontent.data.bldbhelper import BLDBHelper
from Newscontent.items import newscontentItem


class NewscontentSpider(Spider):
    # 爬虫名称
    p=""
    name = 'newscontent'
    # 起始url地址
    start_urls = ['https://new.qq.com/omn/20200325/20200325A0LQ9J00.html']

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
        a = self.db.selectnewsurl()
        # print(a[1][0])
        # print(type(a[1][0]))
        j = 0
        for i in a:
            contentid = a[j][0]
            date=contentid[0:8]
            j = j + 1
            contenturl = "https://new.qq.com/omn/" +date+"/"+contentid+".html"
            # print(contentid)
            # print(date)
            # print(contenturl)
            yield scrapy.Request(url=contenturl, callback=self.parse_content_url)

            #yield scrapy.Request(url='https://new.qq.com/omn/20200325/20200325A0LQ9J00.html', callback=self.parse_content_url)

    def parse_content_url(self, response):
       # print(response.text)
       body = response.body.decode('GBK')
       newcontentp= Selector(text=body).xpath('//div[@class="content-article"]/p').extract()
       newcontenth1= Selector(text=body).xpath('//div[@class="LEFT"]/h1[1]/text()').extract()
       # print(newcontentp)
       # print(type(newcontentp))


       temh1=self.p.join(item for item in newcontenth1)
       temp=self.p.join(item for item in newcontentp)
       # print(temp)
       temp1=temp.replace("//","https://")
       #print(temp1)
       # temp1=temp1.join(temp)
       # content=temp1+temp
       # for item in newcontentp:
       #       print(item)
       #       self.p= item.join(self.p)
       #       print(type(item))
       # print(content)
       #
       # for item in newcontenth1:
       #       print(item)
       #       print(type(item))

       item = newscontentItem()
       url= response.url
       urltemp=url.split('/')[-1]
       item["newsurl"] =urltemp.split('.')[0]
       print(item["newsurl"])
       print(type(temp1))
       item["newscontent"] = temp1
       # item["newstitle"]=temh1
       yield item






