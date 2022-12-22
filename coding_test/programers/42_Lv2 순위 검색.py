import bisect

def solution(info, query):
    dic1 = {"-": '0', "cpp": '1', "java": '2', "python": '3'}
    dic2 = {"-": '0', "backend": '1', "frontend": '2'}
    dic3 = {"-": '0', "junior": '1', "senior": '2'}
    dic4 = {"-": '0', "chicken": '1', "pizza": '2'}

    info_list = {}
    query_list = {}

    for i in info:
        str = i.split(' ')
        if dic1[str[0]] + dic2[str[1]] + dic3[str[2]] + dic4[str[3]] not in info_list:
            info_list[dic1[str[0]] + dic2[str[1]] + dic3[str[2]] + dic4[str[3]]] = [ int(str[4]) ]
        else:
            info_list[dic1[str[0]] + dic2[str[1]] + dic3[str[2]] + dic4[str[3]]].append(int(str[4]))

    for i in range(0, len(query)):
        str = query[i].replace('and ', '').split(' ')
        if dic1[str[0]] + dic2[str[1]] + dic3[str[2]] + dic4[str[3]] not in query_list:
            query_list[dic1[str[0]] + dic2[str[1]] + dic3[str[2]] + dic4[str[3]]] = [ [int(str[4]), i] ]
        else:
            query_list[dic1[str[0]] + dic2[str[1]] + dic3[str[2]] + dic4[str[3]]].append([int(str[4]), i])

    for key in info_list:
        info_list[key].sort()

    for key in query_list:
        query_list[key].sort()
    
    answer = [0 for i in range(len(query))]

    for key, value in query_list.items():
        for key1, value1 in info_list.items():
            check = 0
            if(key[0] == '0' and key[1] == '0' and key[2] == '0' and key[3] == '0'):
                check = 4
            elif(key[0] == '0' and key[1] == '0' and key[2] == '0'):
                check = 3
                if(key[3] == key1[3]):
                    check += 1
            elif(key[0] == '0' and key[1] == '0' and key[3] == '0'):
                check = 3
                if(key[2] == key1[2]):
                    check += 1
            elif(key[0] == '0' and key[2] == '0' and key[3] == '0'):
                check = 3
                if(key[1] == key1[1]):
                    check += 1
            elif(key[1] == '0' and key[2] == '0' and key[3] == '0'):
                check = 3
                if(key[0] == key1[0]):
                    check += 1
            elif(key[0] == '0' and key[1] == '0'):
                check = 2
                if(key[2] == key1[2]):
                    check += 1
                if(key[3] == key1[3]):
                    check += 1
            elif(key[0] == '0' and key[2] == '0'):
                check = 2
                if(key[1] == key1[1]):
                    check += 1
                if(key[3] == key1[3]):
                    check += 1
            elif(key[0] == '0' and key[3] == '0'):
                check = 2
                if(key[1] == key1[1]):
                    check += 1
                if(key[2] == key1[2]):
                    check += 1
            elif(key[1] == '0' and key[2] == '0'):
                check = 2
                if(key[0] == key1[0]):
                    check += 1
                if(key[3] == key1[3]):
                    check += 1
            elif(key[1] == '0' and key[3] == '0'):
                check = 2
                if(key[0] == key1[0]):
                    check += 1
                if(key[2] == key1[2]):
                    check += 1
            elif(key[2] == '0' and key[3] == '0'):
                check = 2
                if(key[0] == key1[0]):
                    check += 1
                if(key[1] == key1[1]):
                    check += 1
            elif(key[0] == '0'):
                check = 1
                if(key[1] == key1[1]):
                    check += 1
                if(key[2] == key1[2]):
                    check += 1
                if(key[3] == key1[3]):
                    check += 1
            elif(key[1] == '0'):
                check = 1
                if(key[0] == key1[0]):
                    check += 1
                if(key[2] == key1[2]):
                    check += 1
                if(key[3] == key1[3]):
                    check += 1
            elif(key[2] == '0'):
                check = 1
                if(key[0] == key1[0]):
                    check += 1
                if(key[1] == key1[1]):
                    check += 1
                if(key[3] == key1[3]):
                    check += 1
            elif(key[3] == '0'):
                check = 1
                if(key[0] == key1[0]):
                    check += 1
                if(key[1] == key1[1]):
                    check += 1
                if(key[2] == key1[2]):
                    check += 1
            else:
                check = 0
                if(key[0] == key1[0]):
                    check += 1
                if(key[1] == key1[1]):
                    check += 1
                if(key[2] == key1[2]):
                    check += 1
                if(key[3] == key1[3]):
                    check += 1
                    
            if(check == 4):
                for i in range(0, len(value)):
                    answer[value[i][1]] += len(value1) - bisect.bisect_left(value1, value[i][0])
                                   
    return answer