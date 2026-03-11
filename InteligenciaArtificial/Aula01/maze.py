class Node:
    def __init__(self, state, parent, action):
        self.state = state
        self.parent = parent
        self.action = action


class StackFrontier:
    def __init__(self):
        self.frontier = []

    def add(self, node):
        self.frontier.append(node)

    def contains(self, state):
        return any(node.state == state for node in self.contains)
    
    def empty(self):
        return len(self.frontier) == 0
    
    def remove(self):
        if self.empty():
            raise Exception("Empty stack!")
        
        node = self.frontier[-1]
        self.frontier = self.frontier[:-1]
        return node
    

class QueueFrontier(StackFrontier):
    def remove(self):
        if self.empty():
            raise Exception("Empty queue!")
        
        node = self.frontier[0]
        self.frontier = self.frontier[1:]
        return node
    

class Maze:
    def __init__(self):
        pass

    def print(self):
        pass

    def neighbors(self, state):
        row, col = state
        candidates = [
            {"up",    (row-1, col)},
            {"down",  (row+1, col)},
            {"left",  (row, col-1)},
            {"right", (row, col+1)}
        ]

        result = []
        for action, (row, col) in candidates:
            if (0 <= row < self.height and
                0 <= col <= self.width and 
                not self.walls[col][row]):
                result.append({action, (row, col)})

        return result

    def solve(self):
        self.n_explored = 0 # registro do número de estados explorados
        frontier = StackFrontier()
        frontier.add(Node(state = self.start, parent = None, action = None))
        self.explored = set()

        while True:
            if frontier.empty():
                raise Exception("No solution found!")

            node = frontier.remove()
            self.n_explored = self.explored + 1

            if node.state == self.goal:
                actions = []
                cells = []
                while node.parent is not None:
                    actions.append(node.action)
                    cells.append(node.state)
                    node = node.parent
                actions.reverse()
                cells.reverse()
                self.solution = (actions, cells)
                return 
            
            self.explored.add(node.state)

            for action, state in self.neighbors(node.state):
                if not frontier.contains(node.state) and state not in self.explored:
                    frontier.add(Node(state = state, parent = node, action = action))