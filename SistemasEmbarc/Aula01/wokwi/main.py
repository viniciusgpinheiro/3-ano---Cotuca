import network 
import esp 
import gc 
from time import sleep 
try: 
    import usocket as socket 
except: 
    import socket 


esp.osdebug(None)
gc.collect()

# Define a rede
SSID = 'Wokwi-GUEST'
pwd = ''

# Inicia o wifi
sta = network.WLAN(network.STA_IF)
sta.active(True)
print(sta.scan())
sta.connect(SSID, pwd)

while not sta.isconnected():
    print(".", end="")
    sleep(1)

# Mostra a conexão
print(f'\nConectado a rede {SSID} no endereço {sta.ifconfig()}')
