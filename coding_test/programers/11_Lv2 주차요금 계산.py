import math

def solution(fees, records):
    dic = {}
    dic2 = {}
    answer = []
    for record in records:
        record = record.split(' ')
        dic2[record[1]] = 0
        if record[1] in dic:
            dic[record[1]].append([record[0], record[2]])
        else:
            dic[record[1]] = []
            dic[record[1]].append([record[0], record[2]])
    for i in dic:
        if(len(dic[i]) % 2 == 1):
            dic[i].append(['23:59', 'OUT'])        
    
    for i in dic:
        j = 0
        time = 0
        while(j < len(dic[i])):
            tmp1 = dic[i][j][0].split(':')
            tmp2 = dic[i][j + 1][0].split(':')
            time += (int(tmp2[0]) - int(tmp1[0])) * 60 + int(tmp2[1]) - int(tmp1[1])
            j += 2
        # fee[0] : 기본시간 / fee[1] : 기본요금 
        # fee[2] : 단위 시간 / fee[3] : 단위 요금
        if(time > fees[0]):
            
            dic2[i] = fees[1] + math.ceil(((time - fees[0]) / fees[2])) * fees[3]
        else:
            dic2[i] = fees[1]
    
    dic2 = sorted(dic2.items())
    
    for i in dic2:
        tmp = list(i)
        answer.append(tmp[1])
    return answer