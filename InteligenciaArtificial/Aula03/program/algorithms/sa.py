from program.core.algorithm import Algorithm
from program.core.solution import Solution
import random, math


class SimulatedAnnealing(Algorithm):
    def __init__(self, initial_temperature, termination, problem, verbose=False):
        super().__init__(termination, problem, verbose)
        self.initial_temperature = initial_temperature
        self.best_solution = None

    
    def initialize(self):
        self.initial_solution = self.__get_initial_solution()
        self.current_solution = self.initial_solution.copy()
        self.temperature = self.initial_temperature


    def advance(self):
        neighbour_solution = self.__get_neighbours(self.current_solution)
        delta = neighbour_solution.cost - self.current_solution.cost
        if delta < 0: 
            self.best_solution = self.current_solution = neighbour_solution
            if self.verbose:
                print('{} - {}'.format(self.n_iteration, self.best_solution.cost))
        elif random.uniform(0, 1) < math.exp(-delta / self.temperature):
            self.current_solution = neighbour_solution
        self.temperature = 0.97 * self.temperature
        self.n_iteration += 1


    def finalize(self):
        pass


    def __get_initial_solution(self): # Retorna uma solução aleatória
        initial_solution = Solution(
            value = random.sample(list(range(self.problem.size)), self.problem.size) 
        )
        initial_solution.cost = self.problem.evaluate(initial_solution.value) 
        return initial_solution
    

    def __get_neighbours(self, current_solution):
        i, j = random.sample(list(range(self.problem.size)), 2)
        neighbour_solution = current_solution.copy()
        neighbour_solution.value[i], neighbour_solution.value[j] = neighbour_solution.value[j], neighbour_solution.value[i]
        neighbour_solution.cost = self.problem.evaluate(neighbour_solution.value)
        return neighbour_solution