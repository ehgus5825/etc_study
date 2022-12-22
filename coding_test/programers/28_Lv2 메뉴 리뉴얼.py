import itertools
import operator


def solution(orders, course):
    answer = []
    sum = []

    orders = sorted(orders, key=len)
    for i in course:
        dic = {}
        for j in orders:
            j = sorted(j)
            tmp = list(itertools.combinations(j, i))
            for k in tmp:
                tmp_str = ''.join(k)
                if tmp_str in dic:
                    dic[tmp_str] += 1
                else:
                    dic[tmp_str] = 1
        sum.append(dic)

    for i in sum:
        sort_tmp = sorted(i.items(), key=operator.itemgetter(1), reverse=True)
        if(sort_tmp != [] and sort_tmp[0][1] > 1):
            max = sort_tmp[0][1]
            for j in range(0, len(sort_tmp)):
                if(max == sort_tmp[j][1]):
                    answer.append(sort_tmp[j][0])

    answer = sorted(answer)

    return answer