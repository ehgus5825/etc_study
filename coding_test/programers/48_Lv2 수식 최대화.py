from collections import Counter
import itertools

def func(oper_sum, expression , i):
    exper = expression.split(oper_sum[i])
    if(i == -1):
        return int(exper[0])

    sum_ = func(oper_sum, exper[0], i - 1)
    for j in range(1, len(exper)):
        if(oper_sum[i] == '*'):
            sum_ = sum_ * func(oper_sum, exper[j], i - 1)
        elif(oper_sum[i] == '-'):
            sum_ = sum_ - func(oper_sum, exper[j], i - 1)
        else:
            sum_ = sum_ + func(oper_sum, exper[j], i - 1)

    return sum_


def solution(expression):
    oper = []
    if '*' in Counter(expression):
        oper.append('*') 
    if '-' in Counter(expression):
        oper.append('-') 
    if '+' in Counter(expression):
        oper.append('+') 

    max_ = 0
    sum_ = 0
    for oper_sum in itertools.permutations(oper, len(oper)):
        sum_ = abs(func(oper_sum, expression, len(oper_sum) - 1))

        if(sum_ > max_):
            max_ = sum_

    return max_