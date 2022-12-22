def solution(board, moves):
    answer = 0
    exit = []
    for move in moves:
        move -= 1
        for i in range(0, len(board[0])):
            if(board[i][move] != 0):
                if(len(exit) != 0 and exit[-1] == board[i][move]):
                    answer += 2
                    exit.pop()
                else:
                    exit.append(board[i][move])
                board[i][move] = 0
                break  
    print(exit)
    return answer