import sys

class Node:

    def __init__(self, state, parent, action):
        
        self.state, self.parent, self.action = state, parent, action


class StackFrontier:

    def __init__(self):

        self.frontier = list()

    def add(self, node):

        self.frontier.append(node)

    def contains(self, state):

        return any(node.state == state for node in self.frontier)

    def empty(self):

        return len(self.frontier) == 0

    def remove(self):

        return self.frontier.pop()


class QueueFrontier(StackFrontier):

    def remove(self):
        
        return self.frontier.pop(0)

class Maze:

    def __init__(self, filename, frontier):
    
        self.frontier = frontier
        self.solution = list()
        self.walls = list()

        with open(filename) as file:
            contents = file.read()

        if contents.count('A') != 1:
            raise Exception('')
        
        if contents.count('B') != 1:
            raise Exception('')
        
        contents = contents.splitlines()
        self.height, self.width = len(contents), max(map(len, contents))

        for i in range(self.height):
            row = list()
            for j in range(self.width):
                match True:
                    case _ if contents[i][j] == 'A':
                        self.start = (i, j)
                        row.append(False)
                    case _ if contents[i][j] == 'B':
                        self.goal = (i, j)
                        row.append(False)
                    case _ if contents[i][j] == ' ':
                        row.append(False)
                    case _ :
                        row.append(True)
            self.walls.append(row)

    def print(self):

        print()

        for i, row in enumerate(self.walls):
            for j, col in enumerate(row):
                match True:
                    case _ if (i, j) == self.start:
                        print('A', end = '')
                    case _ if (i, j) == self.goal:
                        print('B', end = '')
                    case _ if (i, j) in self.solution:
                        print('*', end = '')
                    case _ if col:
                        print('█', end = '')
                    case _ :
                        print(' ', end = '')
            print()
        
        print()

    def neighbors(self, state):

        result = list()

        for action, new_state in [('up', (state[0] - 1, state[1])), ('down', (state[0] + 1, state[1])), ('left', (state[0], state[1] - 1)), ('right', (state[0], state[1] + 1))]:
            if 0 <= new_state[0] < self.height and 0 <= new_state[1] < self.width and not self.walls[new_state[0]][new_state[1]]:
                result.append((action, new_state))

        return result

    def solve(self):
        
        self.frontier.add(Node(state = self.start, parent = None, action = None))
        self.explored = set()

        while True:

            if self.frontier.empty():
                raise Exception('No solution found.')
            
            node = self.frontier.remove()

            if node.state == self.goal:
                while node.parent is not None:
                    self.solution.append(node.state)
                    node = node.parent
                self.solution.append(node.state)
                self.solution.reverse()
                break

            self.explored.add(node.state)

            for action, new_state in self.neighbors(node.state):
                if not self.frontier.contains(new_state) and new_state not in self.explored:
                    self.frontier.add(Node(state = new_state, parent = node, action = action))


                

maze = Maze(sys.argv[1], StackFrontier())
maze.print()
maze.solve()
maze.print()