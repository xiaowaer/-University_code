# -*- coding: utf-8 -*-

# Scrapy settings for Newscontent project
#
# For simplicity, this file contains only settings considered important or
# commonly used. You can find more settings consulting the documentation:
#
#     https://docs.scrapy.org/en/latest/topics/settings.html
#     https://docs.scrapy.org/en/latest/topics/downloader-middleware.html
#     https://docs.scrapy.org/en/latest/topics/spider-middleware.html

BOT_NAME = 'Newscontent'

SPIDER_MODULES = ['Newscontent.spiders']
NEWSPIDER_MODULE = 'Newscontent.spiders'


#用于设置图片存储路径
IMAGES_STORE=r'D:\Hobby\Anime\CG'

#IMAGES_EXPIRES用于设置失效期限
#这里是90天，避免管道重复下载最近已经下载过的
IMAGES_EXPIRES=90


#数据库配置
MYSQL_HOST = "127.0.0.1"
MYSQL_DBNAME = "bysj"
MYSQL_USER = "root"
MYSQL_PASSWORD = ""
MYSQL_PORT = 3306      #数据库端口，在dbhelper中使用

#redis设置

#启用Redis调度存储请求队列
#SCHEDULER = "scrapy_redis.scheduler.Scheduler"
#确保所有的爬虫通过Redis去重
#DUPEFILTER_CLASS = "scrapy_redis.dupefilter.RFPDupeFilter"
# 调度状态持久化，不清理redis缓存，允许暂停/启动爬虫
#SCHEDULER_PERSIST = True
#使用优先级调度请求队列 （默认使用）
#SCHEDULER_QUEUE_CLASS = 'scrapy_redis.queue.PriorityQueue'
#可选用的其它队列
#SCHEDULER_QUEUE_CLASS = 'scrapy_redis.queue.FifoQueue'
#SCHEDULER_QUEUE_CLASS = 'scrapy_redis.queue.LifoQueue'


#指定连接到redis时使用的端口和地址（可选）
#REDIS_HOST = '127.0.0.1'
#REDIS_PORT = 6379

# Crawl responsibly by identifying yourself (and your website) on the user-agent
#USER_AGENT = 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Mobile Safari/537.36'
USER_AGENT = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Mobile Safari/537.36'

# Obey robots.txt rules
ROBOTSTXT_OBEY = False
#LOG_LEVEL = "WARNING"

# Configure maximum concurrent requests performed by Scrapy (default: 16)
#CONCURRENT_REQUESTS = 32

# Configure a delay for requests for the same website (default: 0)
# See https://docs.scrapy.org/en/latest/topics/settings.html#download-delay
# See also autothrottle settings and docs
DOWNLOAD_DELAY = 1
# The download delay setting will honor only one of:
#CONCURRENT_REQUESTS_PER_DOMAIN = 16
#CONCURRENT_REQUESTS_PER_IP = 16

# Disable cookies (enabled by default)
#COOKIES_ENABLED = False

# Disable Telnet Console (enabled by default)
#TELNETCONSOLE_ENABLED = False

# Override the default request headers:
#DEFAULT_REQUEST_HEADERS = {
#   'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
#   'Accept-Language': 'en',
#}

# Enable or disable spider middlewares
# See https://docs.scrapy.org/en/latest/topics/spider-middleware.html
#SPIDER_MIDDLEWARES = {
#    'mynewsspider.middlewares.MynewsspiderSpiderMiddleware': 543,
#}

# Enable or disable downloader middlewares
# See https://docs.scrapy.org/en/latest/topics/downloader-middleware.html
#DOWNLOADER_MIDDLEWARES = {
#    'mynewsspider.middlewares.MynewsspiderDownloaderMiddleware': 543,
#}

# Enable or disable extensions
# See https://docs.scrapy.org/en/latest/topics/extensions.html
#EXTENSIONS = {
#    'scrapy.extensions.telnet.TelnetConsole': None,
#}

# Configure item pipelines
# See https://docs.scrapy.org/en/latest/topics/item-pipeline.html
FILES_STORE=r'D:\pics\video'
ITEM_PIPELINES = {
    'Newscontent.pipelines.MysqlTwistedPipeline': 300,
    'Newscontent.pipelines.MysqlupdatecontentPipeline': 300,
    # 'scrapy.pipelines.files.FilesPipeline': 300,
    #'mynewsspider.pipelines.CGpicScrapyPipeline':300,
     #'mynewsspider.pipelines.CGpicforoneScrapyPipeline':300,
    # 'Newscontent.pipelines.VideoPipeline': 300,



}
FILES_EXPIRES = 90
# Enable and configure the AutoThrottle extension (disabled by default)
# See https://docs.scrapy.org/en/latest/topics/autothrottle.html
#AUTOTHROTTLE_ENABLED = True
# The initial download delay
#AUTOTHROTTLE_START_DELAY = 5
# The maximum download delay to be set in case of high latencies
#AUTOTHROTTLE_MAX_DELAY = 60
# The average number of requests Scrapy should be sending in parallel to
# each remote server
#AUTOTHROTTLE_TARGET_CONCURRENCY = 1.0
# Enable showing throttling stats for every response received:
#AUTOTHROTTLE_DEBUG = False

# Enable and configure HTTP caching (disabled by default)
# See https://docs.scrapy.org/en/latest/topics/downloader-middleware.html#httpcache-middleware-settings
#HTTPCACHE_ENABLED = True
#HTTPCACHE_EXPIRATION_SECS = 0
#HTTPCACHE_DIR = 'httpcache'
#HTTPCACHE_IGNORE_HTTP_CODES = []
#HTTPCACHE_STORAGE = 'scrapy.extensions.httpcache.FilesystemCacheStorage'
