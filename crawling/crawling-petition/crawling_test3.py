import requests
import re #추가
from bs4 import BeautifulSoup
from selenium import webdriver
import pandas as pd
from selenium.common.exceptions import NoSuchElementException
import time
# iframe 제거 후 blog.naver.com 붙이기
def delete_iframe(url):
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36"}
    res = requests.get(url, headers=headers)
    res.raise_for_status()  # 문제시 프로그램 종료
    soup = BeautifulSoup(res.text, "lxml")

    src_url = "https://blog.naver.com/" + soup.iframe["src"]

    return src_url

# 본문 스크래핑
def text_scraping(url):
    headers = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36"}
    res = requests.get(url, headers=headers)
    res.raise_for_status() # 문제시 프로그램 종료
    soup = BeautifulSoup(res.text, "lxml")

    if soup.find("div", attrs={"class":"se-main-container"}):
        text = soup.find("div", attrs={"class":"se-main-container"}).get_text()
        text = text.replace("\n","") #공백 제거
        return text
    else:
        return "확인불가"


file = pd.read_csv("/Users/xiu0327/연구실/부산지역축제/위키백과/link/부산지역축제_100up_목록.csv")
name = file['축제 명']
region = file['지역']
openDate = file['개최시기']

driver = webdriver.Chrome("/Users/xiu0327/PycharmProjects/team_project/chromedriver")
driver.implicitly_wait(3)

contents_title = []  # 제목
contents_region = []  # 지역
contents_festivalName = []  # 축제 명
contents_festivalOpenDate = []  # 개최시기
contents_date = []  # 작성날짜
contents_text = []  # 본문내용

for q in range(len(name)):
    print("현재 축제 : ", name[q])
    data = pd.read_csv("/Users/xiu0327/연구실/부산지역축제/위키백과/link/"+name[q]+".csv")
    title = data['제목']
    date = data['작성날짜']
    link = data['link']

    title_arr = []
    region_arr = []
    name_arr = []
    openDate_arr = []
    date_arr = []
    text_arr = []

    for i in range(len(link)):
        try:
            # 블로그 링크 하나씩 불러오기
            post_link = link[i]

            blog_p = re.compile("blog.naver.com")
            blog_m = blog_p.search(post_link)

            if blog_m:
                blog_text = text_scraping(delete_iframe(post_link))
                if blog_text != "확인불가":
                    contents_region.append(region[q])
                    contents_festivalName.append(name[q])
                    contents_festivalOpenDate.append(openDate[q])
                    contents_title.append(title[i])
                    contents_date.append(date[i])
                    contents_text.append(blog_text)

                    region_arr.append(region[q])
                    name_arr.append(name[q])
                    openDate_arr.append(openDate[q])
                    title_arr.append(title[i])
                    date_arr.append(date[i])
                    text_arr.append(blog_text)
                    print(str(i+1)+"번째 완료(bs4)")
                else:
                    # 블로그 링크 하나씩 불러오기
                    driver.get(link[i])
                    time.sleep(1)
                    # 블로그 안 본문이 있는 iframe에 접근하기
                    driver.switch_to.frame("mainFrame")
                    # 본문 내용 크롤링하기
                    #a = driver.find_element_by_css_selector('div.se-main-container').text
                    a = driver.find_element_by_css_selector('div#postViewArea').text
                    a = a.replace("\n", "")
                    contents_region.append(region[q])
                    contents_festivalName.append(name[q])
                    contents_festivalOpenDate.append(openDate[q])
                    contents_title.append(title[i])
                    contents_date.append(date[i])
                    contents_text.append(a)

                    region_arr.append(region[q])
                    name_arr.append(name[q])
                    openDate_arr.append(openDate[q])
                    title_arr.append(title[i])
                    date_arr.append(date[i])
                    text_arr.append(a)
                    print(str(i + 1) + "번째 완료(sel)")
            # NoSuchElement 오류시 예외처리(구버전 블로그에 적용)
        except NoSuchElementException:
            a_arr = driver.find_elements_by_css_selector('p.se_textarea')
            a = ''
            for item in a_arr:
                a = a+item.text
            a = a.replace("\n", "")
            contents_region.append(region[q])
            contents_festivalName.append(name[q])
            contents_festivalOpenDate.append(openDate[q])
            contents_title.append(title[i])
            contents_date.append(date[i])
            contents_text.append(a)

            region_arr.append(region[q])
            name_arr.append(name[q])
            openDate_arr.append(openDate[q])
            title_arr.append(title[i])
            date_arr.append(date[i])
            text_arr.append(a)
            print(str(i + 1) + "번째 완료(noSearch)")
        except Exception as e:
            print(e)
            print("실패")


    print("<<본문 크롤링이 완료되었습니다.>>")

    df = pd.DataFrame(
        {'지역': region_arr, '축제 명': name_arr, '개최시기': openDate_arr, '제목': title_arr, '작성날짜': date_arr,
         '내용': text_arr})
    df.to_csv("/Users/xiu0327/연구실/부산지역축제/위키백과/blog/"+name[q]+".csv", index=False)

df2 = pd.DataFrame(
    {'지역': contents_region, '축제 명': contents_festivalName, '개최시기': contents_festivalOpenDate, '제목': contents_title, '작성날짜': contents_date,
     '내용': contents_text})
df2.to_excel("/Users/xiu0327/연구실/부산지역축제/위키백과/blog/위키백과_corpus.xlsx",index=True)

