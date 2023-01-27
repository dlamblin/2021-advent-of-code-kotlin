"""
# [Day 2 Advent of Code 2022][1] on 2023-01-21 with GitHub Codespaces.
Lets see if 60h free every month is enough.

1: https://adventofcode.com/2022/day/2
"""


def cleanup_line(line: str) -> str:
    """
    chomp trailing newlines
    """
    return line.rstrip('\n')


def run(lines: list[str], second: int = 1) -> int:
    """
    Day 2: Rock Paper Scissors
    Part 2: ?
    """
    score = 0
    scores = {
      "A Y": 8,  // Rock Paper(2) win(6)
      "A X": 4,  // Rock Rock(1) draw(3)
      "A Z": 3,  // Rock Scissors(3) loss(0)
      "B X": 5,  // Paper Paper(2) draw(3)
      "B Y": 1,  // Paper Rock(1) loss(0)
      "B Z": 9,  // Paper Scissors(3) win(6)
      "C X": 2,  // Scissors Paper(2) loss(0)
      "C Y": 7,  // Scissors Rock(1) win(6)
      "C Z": 6,  // Scissors Scissors(3) draw(3)
    }
    for play in lines:
        play = cleanup_line(play)
        score += scores[play]

    return score

EXAMPLE = """A Y
B X
C Z
"""

if __name__ == "__main__":
    print(f"Example: \t{run(EXAMPLE.splitlines())}")
    print(f"Example 2nd: \t{run(EXAMPLE.splitlines(), 3)}")
    with open(__file__.replace('py', 'in.txt'), "r", encoding="utf-8") as f:
        input_list = list(f)
        print(f"Input:  \t{run(input_list)}")
        print(f"Input 2nd: \t{run(input_list, 3)}")
 
