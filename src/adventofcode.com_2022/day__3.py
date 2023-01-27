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
    """
    Gives the last common item between the strings
    Assume you only get one character in common.
    """
    return set(pockets[0]).intersection(*pockets[1:]).pop()


def priority(item: str) -> int:
    if item.islower():
        return ord(item) - 96
    return ord(item) - 38


def run(lines: list[str], second: int = 0) -> int:
    """
    Day 3: Rucksack Reorganization
    Part 2: badges in groups of 3
    """
    cleaned = [cleanup_line(l) for l in lines]
    pri_for_common_items = [
        priority(common_char(pack_lr(l)))  # the priorities of the left-right
        for l in cleaned] if second == 0 else [
        priority(common_char(tripple))  # the priorities of the common btw 3
        for tripple in zip(*[iter(cleaned)]*3)]  # groups by 3

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
 
