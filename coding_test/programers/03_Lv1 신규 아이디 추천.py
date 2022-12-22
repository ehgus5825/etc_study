import re
import copy

def solution(new_id):
    answer = ''
    step = new_id.lower()
    
    step = re.sub(r"[^a-z0-9-._]", '', step)
    pre = ''
    while pre != step:
        pre = copy.deepcopy(step)
        step = step.replace('..', '.')
    if(step.startswith('.')):
        step = step[1:]
    if(step.endswith('.')):
        step = step[0:len(step) -1]
    if(step == ''):
        step += 'a'
    if(len(step) >= 16):
        step = step[0:15]
        if(step[14] == '.'):
            step = step[0:14]
    if(len(step) <= 2):
        step += step[-1]
        if(len(step) <= 2):
            step += step[-1]
    answer = step
    return answer