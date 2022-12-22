import sys
import re

n, m = map(int, input().split())
n_li = [sys.stdin.readline().strip() for i in range(n)]
m_li = [sys.stdin.readline().strip() for i in range(m)]

dic1 = {}
dic2 = {}

answer = []

for i in range(1, n + 1):
    dic1[i] = n_li[i-1]
    dic2[n_li[i-1]] = i

for i in m_li:
    if (re.search('\d', i)):
        print(dic1[int(i)])
    else:
        print(dic2[i])