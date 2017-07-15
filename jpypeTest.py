#!/usr/bin/env python  
# coding : utf-8  
  
# import newspaper

# sina_paper = newspaper.build('http://www.sina.com.cn/', language='zh')

# for category in sina_paper.category_urls():
#     print category

from newspaper import Article

url = "http://www.jianshu.com/p/b7f41df6202d"
a = Article(url, language='zh')
a.download()
a.parse()
print a.text