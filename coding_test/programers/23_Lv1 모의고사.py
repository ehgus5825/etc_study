import operator

def solution(answers):
    supo = [[], [], []]
    sum_answer = {}
    answer = []
    for i in range(0, 3):
        sum = 0
        if(i == 0):
            for j in range(0, 2000):
                supo[i].extend([1, 2, 3, 4, 5])
        elif(i == 1):
            for j in range(0, 1250):
                supo[i].extend([2, 1, 2, 3, 2, 4, 2, 5])
        else:
            for j in range(0, 1000):
                supo[i].extend([3, 3, 1, 1, 2, 2, 4, 4, 5, 5])

        for j in range(0, len(answers)):
            if(answers[j] == supo[i][j]):
                sum += 1

                
        sum_answer[i + 1] = sum
            
    sum_answer = sorted(sum_answer.items(), key=operator.itemgetter(1), reverse=True )
    
    _max = sum_answer[0][1]
    
    for i in range(0, 3):
        if(_max == sum_answer[i][1]):
            answer.append(sum_answer[i][0])
    
    answer = sorted(answer)
    
    print(sum_answer)
            
    return answer