import json
import scrapy
from scrapy.spiders import Spider

from Newscontent.data.bldbhelper import BLDBHelper
from Newscontent.items import VideoItem, ExamplesItem



class HaokanspSpider(Spider):
    # 爬虫名称
    name = 'videodownload'
    # 起始url地址
    start_urls = ['https://baidu.com']


    custom_settings = {
        'CONCURRENT_REQUESTS': 64,
        'DOWNLOAD_DELAY': 0,
        'COOKIES_ENABLED': False,
        'RETRY_TIMES': 15,
        'DEFAULT_REQUEST_HEADERS': {
            'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
            'accept-encoding': 'gzip, deflate, br',
            'accept-language': 'zh-CN,zh;q=0.9,en;q=0.8',
            'cache-control': 'max-age=0',
        },
    }


    def parse(self, response):
        self.db = BLDBHelper()
        a = self.db.selectenamebyvideo()
        print(a[1][0])
        print(type(a[1][0]))
        ename=a[7][0]
        cidt = self.db.selectcidforename(ename)
        cid=cidt[0]
        print(cid)
        b= self.db.selectVideo(cid)
        j = 0
        file_urls = []
        item = ExamplesItem()
        for i in b:
            url= b[j][0]
            print(b[j][0])
            file=b[j][0]
            j = j + 1
            file_urls.append(file)
        item["file_urls"] = file_urls
        item["name"] = ename
        yield item

    # def parse_video(self, response):
    #     file_urls=[]
    #     item = ExamplesItem()
    #     file= "https://vdept.bdstatic.com/524d41314c61664a425637586951446d/62445575336c4778/62394e34080d50efc31ea9c4aa99611cb99eb507da1747f22733d13c9a385890b31fb9eee6a8037fcf2f98a061336d9ee0df0f5dbd2dfce58abee056fa1abd56.mp4?auth_key=1587748988-0-0-26769edf20048b172ff955ad6b29fef8"
    #     file_urls.append(file)
    #     item["file_urls"]=file_urls
    #     item["name"]="hahaha"
    #     yield item

        # file_name = [i['video_title'], '.mp4']
            # file_name = '1.mp4'
            # base_dir = path.join(path.curdir, 'VideoDownload')
            # video_local_path = path.join(base_dir, file_name.replace('?', ''))
            # i['video_local_path'] = video_local_path
            #
            # if not os.path.exists(base_dir):
            #     os.mkdir(base_dir)
            #
            # with open(video_local_path, "wb") as f:
            #     f.write(response.body)
            #
            # yield i
