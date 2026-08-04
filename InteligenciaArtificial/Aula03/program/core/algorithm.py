class Algorithm:
    def __init__(self, termination, problem, verbose = False):
        self.termination = termination # Critério de parada
        self.problem = problem # Problema proposto
        self.verbose = verbose # Imprimir saída nesta execução
        self.n_iteration = 0 # Número de iterações
        self.start_time = None

    
    def run(self):
        self.initialize()

        while not self.termination.has_terminated():
            self.advance()
            self.termination.update_progress(self)

        self.finalize


    def initialize(self):
        pass


    def advance(self):
        pass


    def finalize(self):
        pass

    