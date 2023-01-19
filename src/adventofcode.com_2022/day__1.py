"""
# Starting Advent of Code on 2023-01-18 with GitHub Codespaces.
Lets see if 60h free every month is enough.

"""

def cleanup_line(line: str) -> str:
    """
    chomp trailing newlines
    """
    return line.rstrip('\n')



def run(lines: list[str]) -> int:
    """
    Day 1: Calorie Counting
    """
    groups = [[]]
    for cal in lines:
        cal = cleanup_line(cal)
        if cal == '':
            groups.append([])
        else:
            groups[-1].append(int(cal))

    # for i, g in enumerate(groups):
    #     print(f"{i} has {len(g)} = {sum(g)}: {g}")

    # for i, g in enumerate(map(sum, groups)):
    #     print(f"{i} = {g}")

    return max(map(sum, groups))

EXAMPLE = """1000
2000
3000

4000

5000
6000

7000
8000
9000

10000
"""

if __name__ == "__main__":
    print(f"Example: {run(EXAMPLE.splitlines())}")
    with open(__file__.replace('py', 'in.txt'), "r", encoding="utf-8") as f:
        print(f"Input: {run(list(f))}")
    # run(second_part)
