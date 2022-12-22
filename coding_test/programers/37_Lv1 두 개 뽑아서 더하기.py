def solution(numbers):
    dic = {}
    for i in range(0, len(numbers)):
        for j in range(0, len(numbers)):
            if(i != j):
                dic[numbers[i]+numbers[j]] = 1
    
    answer = sorted(list(dic.keys()))
    
    return answer