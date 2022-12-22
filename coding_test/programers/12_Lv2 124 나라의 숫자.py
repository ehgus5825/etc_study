import math

def solution(n):
    answer = ''
    k = 3
    i = 1
    while(n > k):
        n -= k
        k *= 3
        i += 1
    n = n - 1
    while(i != 0):
        m = 3 ** (i - 1)
        if(math.floor(n / m) == 0):
            answer += '1'
        if(math.floor(n / m) == 1):
            answer += '2'
        if(math.floor(n / m) == 2):
            answer += '4'
        n = n % m
        i = i - 1
    return answer