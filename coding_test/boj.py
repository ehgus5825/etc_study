import sys

n, m = map(int, sys.stdin.readline().split())
n_li = [int(sys.stdin.readline().strip()) for i in range(n)]

bgn, end = 1, max(n_li)

while(bgn <= end):
    mid = (bgn + end) // 2
    sum_ = 1
    tmp = mid

    for i in n_li:
        if(tmp > i):
            tmp -= i
        elif(tmp < i):
            tmp
        else:

    if(sum_ <= m):
        bgn = mid + 1
        mid = (bgn + end) // 2
    else:
        end = mid - 1
        mid = (bgn + end) // 2


print(end)
