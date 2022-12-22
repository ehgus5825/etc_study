import math

def func(numbers, target, idx, _sum, sign):
    if(idx == len(numbers)):
        if(target == _sum):
            return 1
        return 0

    if(sign == 0):
        _sum += numbers[idx]
    if(sign == 1):
        _sum -= numbers[idx]

    tmp1 = func(numbers, target, idx + 1, _sum, 0)
    tmp2 = func(numbers, target, idx + 1, _sum, 1)
    return tmp1 + tmp2


def solution(numbers, target):
    tmp1 = func(numbers, target, 0, 0, 0)
    tmp2 = func(numbers, target, 0, 0, 1)

    return math.floor((tmp1 + tmp2) / 2)