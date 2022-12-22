import itertools
import collections

def solution(n, info):
    win_list = {}
    max_ = -1
    answer = []
    real_win_list = []
    answer_tmp = {}
    
    for i in itertools.combinations_with_replacement([0,1,2,3,4,5,6,7,8,9,10], n):
        lion_sum = 0
        apeach_sum = 0
        tmp = collections.Counter(i)
        for i in range(0, 11):
            if i in tmp and tmp[i] > info[i]:
                lion_sum += 10 - i
            else:
                if(info[i] > 0):
                    apeach_sum += 10 - i
        if(lion_sum > apeach_sum):
            if(lion_sum - apeach_sum > max_):
                max_ = lion_sum - apeach_sum
            if (lion_sum - apeach_sum) not in win_list:
                win_list[lion_sum - apeach_sum] = [tmp]
            else:
                win_list[lion_sum - apeach_sum].append(tmp)
        
    if(max_ == -1):
        return [-1]
    else:        
        for i in win_list[max_]:
            tmp1 = []
            for j in range(0, 11):
                if j in i:
                    tmp1.append(i[j])
                else:
                    tmp1.append(0)
            real_win_list.append(tmp1)

        i = 10
        while(i > -1):
            count = 0
            temp = []
            max_low = []
            for item in real_win_list:
                if(item[i] == 0):
                    count += 1
                else:
                    max_low.append(item[i])
                    temp.append(item)
            if(count != len(real_win_list)):
                temp2 = []
                for item in temp:                    
                    if(max(max_low) == item[i]):
                        temp2.append(item)
                real_win_list = temp2
            i -= 1
        
        return real_win_list[0]