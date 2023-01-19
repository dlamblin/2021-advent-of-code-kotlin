"""
# Starting Advent of Code on 2023-01-18 with GitHub Codespaces.
Lets see if 60h free every month is enough.

Note: I tried _really_tied_ to use `from itertools import groupby`
but, well, the keyfunc just didn't want to play with a global variable
counting the number of groups thus far based on empty lines seen.
"""

def cleanup_line(line: str) -> str:
    """
    chomp trailing newlines
    """
    return line.rstrip('\n')



def run(lines: list[str], top_n: int = 1) -> int:
    """
    Day 1: Calorie Counting
    Part 2: Summing the top N
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

    sums = sorted(map(sum, groups), reverse=True)
    return sum(sums[:top_n])

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
    print(f"Example 2nd: {run(EXAMPLE.splitlines(), 3)}")
    with open(__file__.replace('py', 'in.txt'), "r", encoding="utf-8") as f:
        input_list = list(f)
        print(f"Input: {run(input_list)}")
        print(f"Input: {run(input_list, 3)}")
 