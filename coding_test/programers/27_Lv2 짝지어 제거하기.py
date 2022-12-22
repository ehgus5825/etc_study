def solution(s):
    answer = -1
    stack = []
    for i in range(0, len(s)):
        if(len(stack) == 0):
            stack.append(s[i])
        else:
            if(stack[-1] == s[i]):
                stack.pop()
            else:
                stack.append(s[i])
    
    if(len(stack) == 0):
        answer = 1
    else: 
        answer = 0
                
                
    return answer