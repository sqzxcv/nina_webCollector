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
                  callback='parse_page', follow=True),)

    def parsepage(self, response):
        print("-----------------page url:" + response.url)
        res = requests.get(
            "http://localhost:8082/presedocument?" + response.url)
        item = NewsSpiderItem()
        item["time"] = "jkf"
        item["title"] = "test"
        item["content"] = "test"
        item["url"] = response.url
        yield item
