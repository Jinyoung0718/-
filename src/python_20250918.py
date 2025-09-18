def parse_sets(s):
    s = s[2:-2]
    parts = s.split("},{")
    result = [list(map(int, p.split(","))) for p in parts]
    return result


def solution(s):
    sets = parse_sets(s)
    sets.sort(key=len)

    answer = []
    for subset in sets:
        for num in subset:
            if num not in answer:
                answer.append(num)

    return answer