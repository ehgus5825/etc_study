def solution(places):
    answer = []
    for room in places:
        check = 0
        for i in range(5):
            for j in range(5):
                if(room[i][j] == 'P'):
                    if(i > 0 and j > 0):    # 좌상
                        if(room[i-1][j-1] == 'P'):
                            if(room[i][j-1] != 'X' or room[i-1][j] != 'X'):
                                check = 1
                                break
                    if(i > 0):              # 상
                        if(room[i-1][j] == 'P'):
                            check = 1
                            break
                        elif(room[i-1][j] == 'O'):
                            if(room[i-2][j] == 'P' and i > 1):
                                check = 1
                                break
                    if(i > 0 and j < 4):    # 우상
                        if(room[i-1][j+1] == 'P'):
                            if(room[i-1][j] != 'X' or room[i][j+1] != 'X'):
                                check = 1
                                break
                    if(j > 0):              # 좌
                        if(room[i][j-1] == 'P'):
                            check = 1
                            break
                        elif(room[i][j-1] == 'O'):
                            if(j > 1 and room[i][j-2] == 'P'):
                                check = 1
                                break
                    if(j < 4):              # 우
                        if(room[i][j+1] == 'P'):
                            check = 1
                            break
                        elif(room[i][j+1] == 'O'):
                            if(j < 3 and room[i][j+2] == 'P'):
                                check = 1
                                break
                    if(i < 4 and j > 0):    # 좌하
                        if(room[i+1][j-1] == 'P'):
                            if(room[i+1][j] != 'X' or room[i][j-1] != 'X'):
                                check = 1
                                break
                    if(i < 4):              # 하
                        if(room[i+1][j] == 'P'):
                            check = 1
                            break
                        elif(room[i+1][j] == 'O'):
                            if(i < 3 and room[i+2][j] == 'P'):
                                check = 1
                                break
                    if(i < 4 and j < 4):    # 우하
                        if(room[i+1][j+1] == 'P'):
                            if(room[i+1][j] != 'X' or room[i][j+1] != 'X'):
                                check = 1
                                break
            if(check == 1):
                break
        if(check == 1):
            answer.append(0)
        else:
            answer.append(1)
    return answer