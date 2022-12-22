def solution(lottos, win_nums):
    answer = []
    sum1 = 0
    sum2 = 0
    for i in lottos:
        for j in win_nums:
            if(i == j):
                sum2 = sum2 + 1
    sum1 = sum2
    for i in lottos:
        if(i == 0):
            sum1 = sum1 + 1
    answer.append(sum1)
    answer.append(sum2)
    for i in range(2):
        if(answer[i] == 0): 
            answer[i] = 6
        elif(answer[i] == 1):
            answer[i] = 6
        elif(answer[i] == 2):
            answer[i] = 5
        elif(answer[i] == 3):
            answer[i] = 4
        elif(answer[i] == 4):
            answer[i] = 3
        elif(answer[i] == 5):
            answer[i] = 2
        else:
            answer[i] = 1
    return answer