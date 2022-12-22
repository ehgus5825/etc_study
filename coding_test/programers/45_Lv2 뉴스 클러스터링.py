import re
import math

def solution(str1, str2):
    answer = 0
    str1 = str1.upper()
    str2 = str2.upper()
    dic1 = {}
    dic2 = {}
    str1_list = []
    str2_list = []
    p = re.compile('[^A-Z]')
    for i in range(0, len(str1)):
        tmp = str1[i:i + 2]
        if(len(tmp) < 2):
            continue
        if(p.findall(tmp)):
            pass
        else:
            if tmp in dic1:
                dic1[tmp] += 1
            else:
                dic1[tmp] = 1
                str1_list.append(tmp)
            
    for i in range(0, len(str2)):
        tmp2 = str2[i:i + 2]
        if(len(tmp2) < 2):
            continue
        if(p.findall(tmp2)):
            pass
        else:
            if tmp2 in dic2:
                dic2[tmp2] += 1
            else:
                dic2[tmp2] = 1
                str2_list.append(tmp2)
    
    if(len(str2_list) == 0 and len(str1_list) == 0):
        return 65536
    
    str1_set = set(str1_list)
    str2_set = set(str2_list)
    intersection_ = list(str1_set & str2_set)
    union_ = list(str1_set | str2_set)
    intsec = 0
    uni = 0
    for i in range(0, len(intersection_)):
        if(dic1[intersection_[i]] < dic2[intersection_[i]]):
            intsec +=  dic1[intersection_[i]]
        else:
            intsec += dic2[intersection_[i]]
            
    for i in range(0, len(union_)):
        if(union_[i] in dic1 and union_[i] in dic2):
            if(dic1[union_[i]] > dic2[union_[i]]):
                uni +=  dic1[union_[i]]
            else:
                uni += dic2[union_[i]]
        elif(union_[i] in dic1):
            uni +=  dic1[union_[i]]
        else:
            uni += dic2[union_[i]]
            
    answer = math.floor((intsec / uni) * 65536)
    
    return answer


print(solution("FRANCE", "french"))