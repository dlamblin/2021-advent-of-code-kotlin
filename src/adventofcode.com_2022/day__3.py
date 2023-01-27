"""
# [Day 3 Advent of Code 2022][1] on 2023-01-21 with GitHub Codespaces.
Lets see if 60h free every month is enough.

1: https://adventofcode.com/2022/day/3
"""


def cleanup_line(line: str) -> str:
    """
    chomp trailing newlines
    """
    return line.rstrip('\n')


def pack_lr(pack: str) -> tuple[str, str]:
    halfway = int(len(pack)/2)
    return (pack[:halfway], pack[halfway:])


def common_char(pockets: tuple[str, str]) -> str:
    left = set(pockets[0])
    right = set(pockets[1])
    return left.intersection(right).pop()


def priority(item: str) -> int:
    if item.islower():
        return ord(item) - 96
    else:
        return ord(item) - 64


def run(lines: list[str], second: int = 0) -> int:
    """
    Day 3: Rucksack Reorganization
    Part 2: ?
    """
    pri_for_common_items = [priority(common_char(pack_lr(chomp(l)))) for l in list]

    return sum(pri_for_common_items)

EXAMPLE = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw
"""

if __name__ == "__main__":
    print(f"Example: \t{run(EXAMPLE.splitlines())}")
    print(f"Example 2nd: \t{run(EXAMPLE.splitlines(), 1)}")
    with open(__file__.replace('py', 'in.txt'), "r", encoding="utf-8") as f:
        input_list = list(f)
        print(f"Input:  \t{run(input_list)}")
        print(f"Input 2nd: \t{run(input_list, 1)}")
 
