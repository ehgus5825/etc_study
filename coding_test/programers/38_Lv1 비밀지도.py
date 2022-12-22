def solution(n, arr1, arr2):
    answer = []
    map_ = [[0 for col in range(n)] for row in range(n)]
    
    for i in range(0, n):
        dit = ""
        k = arr1[i]
        for j in range(n-1, -1, -1):
            map_[i][j]= k % 2
            k //= 2
        print(map_[i])
        
    for i in range(0, n):
        dit = ""
        k = arr2[i]
        for j in range(n-1, -1, -1):
            if(map_[i][j] == 0):
                map_[i][j]= k % 2
            k //= 2
        print(map_[i])
    
    for i in range(0, n):
        tmp = ""
        for j in range(0, n):
            if(map_[i][j] == 1):
                tmp += '#'
            else:
                tmp += ' '
        answer.append(tmp)
    return answer