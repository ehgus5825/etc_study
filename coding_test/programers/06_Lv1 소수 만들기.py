def solution(nums):
    answer = 0
    nums = sorted(nums)
    for i in range(0,len(nums)):
        for j in range(i + 1, len(nums)):
            for k in range(j + 1, len(nums)):
                prime = nums[i] + nums[j] + nums[k]
                sum = 0
                for l in range(2, prime):
                    if(prime % l == 0):
                        sum += 1
                if(sum == 0):
                    answer += 1

    return answer