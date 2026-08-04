from program.core.problem import Problem


class TravelingSalesman(Problem):
    def __init__(self, distances : int, n_cities : int):
        self.distances = distances
        self.n_cities = n_cities


    def evaluate(self, tour : list[int]) -> int:
        return sum(self.distances[tour[i % self.n_cities]][tour[(i + 1) % len(tour)]] for i in range(self.n_cities))
    
        soma = 0
        for i in range(self.n_cities):
            u = tour[i]
            v = tour[(i + 1) % self.n_cities]            
            soma += self.distances[u][v]
        return soma
    

    @property
    def size(self) -> int:
        return self.n_cities

