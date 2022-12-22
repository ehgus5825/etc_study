import operator


def solution(N, stages):
    answer = []
    dic = {}
    dic2 = {}
    stages = sorted(stages)
    for i in range(0, len(stages)):
        if stages[i] in dic:
            dic[stages[i]] += 1
        else:
            if(N+1 == stages[i]):
                if stages[i] - 1 in dic2:
                    pass
                else:
                    dic2[stages[i] - 1] = len(stages) - i
                    dic[stages[i] - 1] = 0
            else:
                dic[stages[i]] = 1
                dic2[stages[i]] = len(stages) - i

    dic3 = {}

    for i in range(0, N):
        if i+1 in dic:
            dic3[i+1] = dic[i+1] / dic2[i+1]
        else:
            dic3[i+1] = 0

    tmp = sorted(dic3.items(), key=operator.itemgetter(1), reverse=True)

    for i in range(0, N):
        answer.append(tmp[i][0])

    return answer