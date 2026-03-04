import network

rede = network.WLAN(network.STA_IF) # modelo de rede (STATION ou ACCESS point)
rede.active(True)

redes_disponiveis = rede.scan()
print(redes_disponiveis) 