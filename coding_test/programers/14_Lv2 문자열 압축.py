import math

def solution(s):
    answer = 999999
    for i in range(1, math.floor(len(s)/2) + 1):
        str_li = []
        for j in range(0, len(s), i):
            str_li.append(s[j:j+i])

        result = ''
        num = 1
        j = 1
        while(j < len(str_li)):
            if(str_li[j-1] == str_li[j]):
                num += 1
                if(j == (len(str_li) - 1) or str_li[j] != str_li[j+1]):
                    result += str(num) + str_li[j]
                    j += 1
                    num = 1
            else:
                result += str_li[j-1]

            if(j == (len(str_li) - 1)):
                result += str_li[j]
            j += 1

        if(len(result) < answer):
            answer = len(result)
    if(answer == 999999):
        answer = len(s)

    return answer