# Vinicius Guedes Pinheiro - 24159

from controller import Robot

robot = Robot()

timestep = int(robot.getBasicTimeStep())

motorE = robot.getDevice('motorE')
motorD = robot.getDevice('motorD')
motorE.setPosition(float('inf'))
motorD.setPosition(float('inf'))
motorE.setVelocity(0.0)
motorD.setVelocity(0.0)

gsL = robot.getDevice('GS_L')
gsR = robot.getDevice('GS_R')
gsL.enable(timestep)
gsR.enable(timestep)


print("Iniciando...")

while robot.step(timestep) != -1:
    valorL = gsL.getValue()
    valorR = gsR.getValue()

    print(f"GS_L={valorL:.1f}  GS_R={valorR:.1f}")

    pretoL = valorL > 800
    pretoR = valorR > 800

    if pretoL and pretoR:
        motorE.setVelocity(1)
        motorD.setVelocity(1)

    elif pretoL:
        motorE.setVelocity(0)
        motorD.setVelocity(1)

    elif pretoR:
        motorE.setVelocity(1)
        motorD.setVelocity(0)

