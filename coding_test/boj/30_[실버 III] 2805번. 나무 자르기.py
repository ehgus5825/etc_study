def func(bgn, mid, end, m, n_li):
    if(bgn > end):
        return mid

    _sum = 0
    for i in n_li:
        if(i - mid < 0):
            continue
        _sum += i - mid

    if(_sum < m):
        end = mid - 1
        mid = (bgn + end) // 2
        return func(bgn, mid, end, m, n_li)
    elif(_sum >= m):
        bgn = mid + 1
        mid = (bgn + end) // 2
        return func(bgn, mid, end, m, n_li)


n, m = map(int, input().split())
n_li = list(map(int, input().split()))

n_li = sorted(n_li)
std = n_li[-1]
mid = (0 + std) // 2

print(func(0, mid, std, m, n_li))