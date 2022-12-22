def solution(participant, completion):
    answer = ''
    participant = sorted(participant)
    completion = sorted(completion)
    for l in range(len(participant)):
        if(l == len(participant) - 1):
            answer = participant[l]
            break
        if(participant[l] != completion[l]):
            answer = participant[l]
            break
    return answer