def solution(a, b):
    dic = {1: 31, 2 : 29, 3:31, 4:30, 5:31, 6:30, 7:31, 8:31,9:30, 10:31, 11:30, 12:31 }
    answer = ''
    num = 5
    for i in range(1, a + 1):
        for j in range(1, dic[i] + 1):
            if(num == 6):
                if(i == a and j == b):
                    answer = "SAT"
                num = 0
            else:
                if(i == a and j == b):
                    if(num == 0):
                        answer = "SUN"
                    elif(num == 1):
                        answer = "MON"
                    elif(num == 2):
                        answer = "TUE"
                    elif(num == 3):
                        answer = "WED"
                    elif(num == 4):
                        answer = "THU"
                    elif(num == 5):
                        answer = "FRI"
                    
                num += 1
    
    return answer