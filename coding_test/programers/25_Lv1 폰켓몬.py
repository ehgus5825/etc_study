def solution(nums):
    answer = 0
    if(len(nums) / 2 >= len(set(nums))):
        answer = len(set(nums))
    else:
        answer = len(nums) / 2
    return answer