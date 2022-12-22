import math

def solution(progresses, speeds):
    answer = []
    tmp = []
    for i in range(0, len(progresses)):
        tmp.append(math.ceil((100 - progresses[i]) / speeds[i]))

    num = 1
    max_num = tmp[0]
    for i in range(1, len(tmp)):
        if(max_num >= tmp[i]):
            num += 1
        else:
            answer.append(num)
            num = 1
            max_num = tmp[i]
    answer.append(num)
    return answer