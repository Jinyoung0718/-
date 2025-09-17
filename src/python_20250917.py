from collections import deque

def solution(bridge_length, weight, truck_weights):
    answer = 0
    bridge = deque([0] * bridge_length)
    truck = deque(truck_weights)
    cur_weight = 0

    while bridge:
        answer += 1
        left = bridge.popleft()
        cur_weight -= left

        if truck:
            if cur_weight + truck[0] <= weight:
                truck_weight = truck.popleft()
                bridge.append(truck_weight)
                cur_weight += truck_weight
            else:
                bridge.append(0)

    return answer