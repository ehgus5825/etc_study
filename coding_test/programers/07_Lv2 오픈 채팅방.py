def solution(record):
    dic = {}
    answer = []
    for i in record:
        r = i.split(' ')
        if(r[0] == 'Enter' or r[0] == 'Change'):
            dic[r[1]] = r[2]
            
    for i in record:
        r = i.split(' ')
        if(r[0] == 'Enter'):
            answer.append(dic[r[1]] + '님이 들어왔습니다.')
        elif(r[0] == 'Leave'):
            answer.append(dic[r[1]] + '님이 나갔습니다.')
        
    return answer