import math

def solution(numbers, hand):
    L = [3, 0]
    R = [3, 2]
    C = [-1, -1]
    answer = ''

    for number in numbers:
        if(number == 1 or number == 4 or number == 7):
            L[0] = math.floor((number - 1) / 3)
            L[1] = (number - 1) % 3
            answer += 'L'
        elif(number == 3 or number == 6 or number == 9):
            R[0] = math.floor((number - 1) / 3)
            R[1] = (number - 1) % 3
            answer += 'R'
        else:
            if(number == 0):
                C[0] = 3
                C[1] = 1
            else:
                C[0] = math.floor((number - 1) / 3)
                C[1] = (number - 1) % 3
            sumL = abs(C[0] - L[0]) + abs(C[1] - L[1])
            sumR = abs(C[0] - R[0]) + abs(C[1] - R[1])
            if(sumL > sumR):
                R[0] = C[0]
                R[1] = C[1]
                answer += 'R'
            elif(sumL < sumR):
                L[0] = C[0]
                L[1] = C[1]
                answer += 'L'
            else:
                if(hand == 'right'):
                    R[0] = C[0]
                    R[1] = C[1]
                    answer += 'R'
                else:
                    L[0] = C[0]
                    L[1] = C[1]
                    answer += 'L'
    return answer