def solution(id_list, report, k):
    answer = []
    dic = {}
    dic2 = {}
    dic3 = {}
    for id in id_list:
        dic[id] = set()
        dic2[id] = 0
        dic3[id] = 0
    for page in report:
        [a, b]= page.split(' ')
        dic[a].add(b)
    
    for i in dic:
        for j in dic[i]:
            dic2[j] += 1
    for i in dic:
        for j in dic[i]:
            if(dic2[j] >= k):
                dic3[i] += 1
 
    for i in dic3:
        answer.append(dic3[i])
    return answer