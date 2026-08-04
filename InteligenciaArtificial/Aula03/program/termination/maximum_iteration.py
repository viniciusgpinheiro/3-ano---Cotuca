from program.core.termination import Termination

class MaximumIteration(Termination):
    def __init__(self, n_maximum_iterations):
        super().__init__()
        self.n_maximum_iterations = n_maximum_iterations


    def compute_progress(self, algorithm):
        return algorithm.n_iteration / self.n_maximum_iterations 