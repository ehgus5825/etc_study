import math

def isPrime(n):
    if(n <= 1): 
        return 0
    elif(n == 2): 
        return 1
    for i in range(2, int(math.sqrt(n)) +1):
       if(n % i == 0) :
           return 0
    return 1

def solution(n, k):
    answer = 0
    str_tmp = ''
    while(n >= k):
        tmp = n % k
        n = math.floor(n / k)
        str_tmp = str(tmp) + str_tmp
    str_tmp = str(n) + str_tmp
    primes = str_tmp.split('0')
    
    for prime in primes:
        if(prime == '' or prime == '1'):
            continue
        p = int(prime)
        if(isPrime(p)):
            answer += 1
    return answer