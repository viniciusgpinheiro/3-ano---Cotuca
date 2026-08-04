class Termination:
    def __init__(self):
        self.force_termination = False # Forçado a terminar quando verdadeiro
        self.percentage = 0.0 # Porcentagem ja concluida

    
    def update_progress(self, algorithm):
        if self.force_termination:
            self.percentage = 1.0
        else:
            self.percentage = self.compute_progress(algorithm)
            assert self.percentage >= 0.0, 'Invalid progress was set by the TerminationCriterion'

        return self.percentage
    

    def has_terminated(self):
        return self.percentage >= 1.0
    

    def terminate(self):
        self.force_termination = True


    def compute_progress(self, algorithm):
        pass