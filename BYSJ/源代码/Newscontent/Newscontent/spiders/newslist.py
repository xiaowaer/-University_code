import json
import scrapy
from scrapy.spiders import Spider
from Newscontent.data.bldbhelper import BLDBHelper
from Newscontent.items import NewsItem
import datetime

class NewsnbalistSpider(Spider):
    # 爬虫名称
    name = 'newslist'
    # 起始url地址
    start_urls = ['https://www.qq.com']
    # page_firsturl = 'https://pacaio.match.qq.com/vlike/category?cid=1&num=20&page=0'
    # page_baseurl = 'https://pacaio.match.qq.com/vlike/category?cid=1&num=20&page='
    page_num="0"
    token="49cbb2154853ef1a74ff4e53723372ce"

    custom_settings = {
        'CONCURRENT_REQUESTS': 64,
        'DOWNLOAD_DELAY': 0,
        'COOKIES_ENABLED': False,
        'RETRY_TIMES': 15,
        'DEFAULT_REQUEST_HEADERS': {
            'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
            'accept-encoding': 'gzip, deflate, br',
            'accept-language': 'zh-CN,zh;q=0.9,en;q=0.8',
            'cache-control': 'no-cache',
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36',

        },
    }

    # 处理 start_urls 对应的响应
    def parse(self, response):
        # print(response.text)
        self.db = BLDBHelper()
        a=self.db.selectnewsename()
        print(a[1][0])
        print(type(a[1][0]))
        j=0
        for i in a:
            self.tyasf= a[j][0]
            print( self.tyasf)
            # cid = self.db.selectcidforename(self.tyasf)
            # print(cid[0][0])
            # print(type(cid[0][0]))
            j=j+1
            self.page_firsturl = "https://pacaio.match.qq.com/irs/rcd?cid=146&token=" + self.token + "&ext=" + self.tyasf + "&page=0"
            print(self.page_firsturl)
            yield scrapy.Request(url=self.page_firsturl, callback=self.parse_page_url)

    def parse_page_url(self, response):
        # print(response.text)
        strename = response.url[87:]
        strename1 = strename[:-7]
        pagenum=strename[-1:]
        print(pagenum)
        # print(strename1)
        cid = self.db.selectcidforename(strename1)
        urllist_dict = json.loads(response.text)
        # print(cid)
        if urllist_dict.get("datanum") > 0:
            data = urllist_dict.get('data')
            for i in data:
                print("txxwpageurl:" + i["app_id"])
                print("pagetitle:" + i["title"])
                print("cid:" + i["category"])
                print("keywords:" + i["keywords"])
                print("keywords:" + i["img"])

                item = NewsItem()
                item["newsoriginurl"] = i["app_id"]
                item["newstitle"] = i["title"]
                item["cid"] = cid[0][0]
                item["newsorigin"] = "txxw"
                item["newskeyword"] = i["keywords"]
                item["newsheadurl"] = i["img"]
                item["newsctime"]=datetime.datetime.now()
                yield item

        for i in range(4,8):
            self.page_num=str(i)
            page_url = "https://pacaio.match.qq.com/irs/rcd?cid=146&token=" + self.token + "&ext=" + strename1 + "&page="+self.page_num
            print(page_url)
            yield scrapy.Request(url=page_url, callback=self.parse_page_url)