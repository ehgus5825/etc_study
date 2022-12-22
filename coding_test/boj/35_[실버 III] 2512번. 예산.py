import sys

n = int(sys.stdin.readline())
n_li = list(map(int, sys.stdin.readline().split()))
n_li = sorted(n_li)

m = int(sys.stdin.readline())

bgn, end = 0, max(n_li)

mid = (bgn + end) // 2
while(bgn <= end):
    sum_ = 0

    for i in n_li:
        if(mid < i):
            sum_ += mid
        else:
            sum_ += i

    if(sum_ <= m):
        bgn = mid + 1
        mid = (bgn + end) // 2
    else:
        end = mid - 1
        mid = (bgn + end) // 2

print(end)