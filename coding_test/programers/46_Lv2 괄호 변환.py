def divide(p):
    left, right = 0,0
    for i in range(0, len(p)):
        if(p[i] == ')'):
            right += 1
        else:
            left += 1
            
        if(right == left):
            return i
    
    return len(p) - 1
    
def isRight(p):
    if(p[0] == ')'):
        return False
    
    arr = [p[0]]
    for i in range(1,len(p)):
        if(p[i] == '('):
            arr.append(p[i])
        else:
            if(len(arr) != 0 and arr[-1] == '('):
                arr.pop()
            else:
                arr.append(p[i])

    if(len(arr) == 0):
        return True

    return False

def solution(p):
    if(p == ""):
        return ""

    tmp = divide(p)
    u, v = p[0:tmp + 1], p[tmp + 1:]
    
    if(isRight(u)):
        return u + solution(v)
    else:
        w = "(" + solution(v) + ")"
        tmp1 = u[1:-1]
        tmp2 = ""
        for i in range(0, len(tmp1)):
            if(tmp1[i] == ')'):
                tmp2 += "("
            else:
                tmp2 += ")"
        return w + tmp2
