def solution(s):
    answer = []
    s_li = s[1:-1].split('\}')
    tup = []
    dic = {}
    for i in range(0, len(s_li)):
        if(i == 0):
            s_li[i] = s_li[i][1:]
        else:
            s_li[i] = s_li[i][2:]
        if(s_li[i] != ''):
            tup.append(s_li[i].split(','))
    
    for i in tup:
        for j in range(0, len(i)):
            if i[j] in dic:
                dic[i[j]] += 1
            else:
                dic[i[j]] = 1
                
    tmp = sorted(dic.items(), key=lambda x: x[1], reverse=True)
    
    for key, value in tmp:
        answer.append(int(key))
    
    return answer