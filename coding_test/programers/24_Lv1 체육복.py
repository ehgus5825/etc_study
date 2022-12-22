def solution(n, lost, reserve):
    answer = 0
    j = 0
    k = 0
    stud = []
    lost = sorted(lost)
    reserve = sorted(reserve)
    for i in range(0, n):
        stud.append(1)
        if(j < len(lost) and lost[j] - 1 == i):
            stud[i] -= 1
            j += 1
        if(k < len(reserve) and reserve[k]-1 == i):
            stud[i] += 1
            k += 1

    for i in range(0, n):
        if(i == 0):
            if(stud[i] == 2 and stud[i + 1] == 0):
                stud[i] -= 1
                stud[i+1] += 1
        elif(i == n - 1):
            if(stud[i] == 2 and stud[i - 1] == 0):
                stud[i] -= 1
                stud[i-1] += 1
        else:
            if(stud[i] == 2 and stud[i-1] == 0):
                stud[i] -= 1
                stud[i-1] += 1
            if(stud[i] == 2 and stud[i+1] == 0):
                stud[i] -= 1
                stud[i+1] += 1

    for i in range(0, n):
        if(stud[i] >= 1):
            answer += 1

    return answer