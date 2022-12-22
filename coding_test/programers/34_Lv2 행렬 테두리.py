def solution(rows, columns, queries):
    answer = []
    grape = [[0 for col in range(columns)] for row in range(rows)]
    num = 1
    for i in range(0, rows):
        for j in range(0, columns):
            grape[i][j] = num
            num += 1

    for query in queries:
        x1 = query[0] - 1
        y1 = query[1] - 1
        x2 = query[2] - 1
        y2 = query[3] - 1
        sum_ = []

        tmp = grape[x1][y1]
        sum_.append(grape[x1 + 1][y1])
        for i in range(y1, y2):
            sum_.append(grape[x1][i])
            tmp1 = grape[x1][i + 1]
            grape[x1][i + 1] = tmp
            tmp = tmp1

        for i in range(x1, x2):
            sum_.append(grape[i][y2])
            tmp1 = grape[i + 1][y2]
            grape[i + 1][y2] = tmp
            tmp = tmp1

        for i in range(y2, y1, -1):
            sum_.append(grape[x2][i])
            tmp1 = grape[x2][i - 1]
            grape[x2][i - 1] = tmp
            tmp = tmp1

        for i in range(x2, x1, -1):
            sum_.append(grape[i][y1])
            tmp1 = grape[i - 1][y1]
            grape[i - 1][y1] = tmp
            tmp = tmp1

        answer.append(min(sum_))

    return answer