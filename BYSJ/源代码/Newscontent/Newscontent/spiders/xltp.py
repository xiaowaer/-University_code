import json
import scrapy
from scrapy.spiders import Spider
import datetime

from Newscontent.data.bldbhelper import BLDBHelper
from Newscontent.items import PicsItem


class XltpSpider(Spider):
    # 爬虫名称
    name = 'xltp'
    # 起始url地址
    start_urls = ['http://slide.ent.sina.com.cn/']

    page_num="0"

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

    # 处理 start_urls 对应的响应
    def parse(self, response):
        # print(response.text)
     # 5 & sub_ch=mobile&id=SI_Cont&page=1&num=20
        self.db = BLDBHelper()
        baseurl="http://api.slide.news.sina.com.cn/interface/api_album.php?activity_size=198_132&size=img&ch_id="
        baseurl1="&id=SI_Cont&num=20&page=1"
        a = self.db.selectenameforxltp()
        # b=str(a[1][0]).split("&")[0]
        # c=str(a[1][0]).split("&")[1]
        # print(a[1][0])
        # print(type(a[1][0]))
        # contenturl = baseurl + b + "&sub_ch=" + c + baseurl1
        # print(contenturl)
        # print(b)
        # print(c)
        j = 0
        for i in a:
            ch_id= a[j][0]
            b = str(ch_id).split("&")[0]
            c = str(ch_id).split("&")[1]
            j = j + 1
            contenturl =baseurl+b+"&sub_ch="+c+baseurl1
            # print(contentid)
            # print(date)
            print(contenturl)
            yield scrapy.Request(url=contenturl, callback=self.parse_content_url)
        # cid = self.db.selectcidforchname("海军力量")
        # print(cid[0][0])
        # print(type(cid[0][0]))

        # yield scrapy.Request(url=contenturl, callback=self.parse_content_url)

    def parse_content_url(self, response):
       print(response.text)
       page=response.url[-1]
       # body = response.body.decode('GBK')
       xltp_dict = json.loads(response.text)
       if xltp_dict.get("count")=="20":
           data = xltp_dict.get('data')
           for i in data:
                print("!!!!!!!!!!!!!!!!!!!!!!!")
                print(i)
                item = PicsItem()
                item["picsorigin"] = "xltp"
                print(item["picsorigin"])
                item["picsctime"] = datetime.datetime.now()
                for jkey,jvalue in i.items():
                    print("&&&&&&&&&&&&&&&&")
                    if jkey == "name":
                        item["picsname"] = jvalue
                        print(item["picsname"])
                    if jkey == "img_url":
                        item["picsheadurl"] = jvalue
                        print(item["picsheadurl"])
                    if jkey == "url":
                        # item["picsurl"] = jvalue
                        strtemp=str(jvalue).split("/")[4]
                        picsurl="https://photo.sina.cn/"+strtemp.replace("slide","album")
                        # print(picsurl)
                        # print(str(jvalue).split("/")[4])
                        # print( item["picsurl"])
                        item["picsoriginurl"]=picsurl


                    if jkey == "short_name":
                        item["picskeyword"] = jvalue
                        print(item["picskeyword"])
                    if jkey=="sub_ch":
                        cid = self.db.selectcidforchname(str(jvalue))
                        item["cid"] = cid[0][0]
                    yield item




