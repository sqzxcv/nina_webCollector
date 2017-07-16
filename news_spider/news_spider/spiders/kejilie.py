#!/usr/bin/env python
# encoding=utf-8

import requests
from scrapy.contrib.spiders import CrawlSpider, Rule
from scrapy.contrib.linkextractors.sgml import SgmlLinkExtractor
from news_spider.items import NewsSpiderItem
from scrapy.selector import Selector


class KejijieSpider(CrawlSpider):

    start_urls = [
        'http://www.kejilie.com']
    name = 'kejilie'
    allowed_domains = ['www.kejilie.com']
    rules = (Rule(SgmlLinkExtractor(allow=('http://www.kejilie.com/.*', )),
                  callback='parsepage', follow=True),)

    def parsepage(self, response):
        print("-----------------page url:" + response.url)
        urlparse = "http://localhost:8082/presedocument?url=" + response.url
        print("------urlparse:"+urlparse)
        res = requests.get(
            "http://localhost:8082/presedocument?url=" + response.url)
        dict = res.json()
        item = NewsSpiderItem()
        item["time"] = dict['news_times']
        item["title"] = dict["title"]
        item["content"] = dict["content"]
        item["url"] = response.url
        print("---------title===" + dict["title"] +"======")
        return item
