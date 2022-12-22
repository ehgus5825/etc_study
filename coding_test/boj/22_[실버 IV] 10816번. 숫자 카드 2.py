import math

def func(n_li, fnd, start, mid, end):
    if(start > end):
        return 0

    if(fnd < n_li[mid]):
        end = mid - 1
        mid = math.floor((start + end) / 2)
        return func(n_li, fnd, start, mid, end)
    elif(fnd > n_li[mid]):
        start = mid + 1
        mid = math.floor((start + end) / 2)
        return func(n_li, fnd, start, mid, end)
    else:
        return 1

def main():
    n = int(input())
    n_li = list(map(int, input().split()))
    m = int(input())
    m_li = list(map(int, input().split()))

    dic = {}

    n_li = sorted(n_li)

    for i in n_li:
        if i in dic:
            dic[i] += 1
        else:
            dic[i] = 1

    answer = []

    for i in range(m):
        fnd = m_li[i]
        mid = math.floor(n / 2)
        answer.append(func(n_li, fnd, 0, mid, n-1))
        if(answer[i] == 1):
            print(dic[fnd])
        else:
            print(0)

main()