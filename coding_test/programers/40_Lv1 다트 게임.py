def solution(dartResult):
    answer = 0
    tmp = -1
    sum_ = []
    for i in range(0, len(dartResult)):
        if(dartResult[i] == 'S' or dartResult[i] == 'D' or dartResult[i] == 'T'):
            num = int(dartResult[tmp+1:i])
            if(dartResult[i] == 'S'):
                sum_.append(num ** 1)
            elif(dartResult[i] == 'D'):
                sum_.append(num ** 2)
            else:    
                sum_.append(num ** 3)
            tmp = i
        if(dartResult[i] == '*'):
            sum_[-1] *= 2
            if(len(sum_) > 1):
                sum_[-2] *= 2
            tmp = i
        if(dartResult[i] == '#'):
            sum_[-1] *= -1
            tmp = i
    
    answer = sum(sum_)
    return answer