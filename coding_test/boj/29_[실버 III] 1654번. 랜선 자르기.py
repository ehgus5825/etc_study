def func(bgn, mid, end, n, k_li):
    if(bgn > end):
        return mid

    _sum = 0
    for i in k_li:
        _sum += i // mid

    if(_sum < n):
        end = mid - 1
        mid = (bgn + end) // 2
        return func(bgn, mid, end, n, k_li)
    elif(_sum >= n):
        bgn = mid + 1
        mid = (bgn + end) // 2
        return func(bgn, mid, end, n, k_li)


k, n = map(int, input().split())
k_li = []
for i in range(0, k):
    k_li.append(int(input()))

k_li = sorted(k_li)
std = k_li[-1]
mid = (1 + std) // 2

print(func(1, mid, std, n, k_li))