from time import sleep
import network
import socket

# --- Conectar ao wifi
rede = network.WLAN(network.STA_IF) # modelo de rede (STATION ou ACCESS point)
rede.active(True)

redes_disponiveis = rede.scan()
print(redes_disponiveis)

rede.connect('Wokwi-GUEST', '') # (SSID, PWD) 
while not rede.isconnected():
    print('.', end='')
    sleep(1)
print('\nEndereços: ',rede.ifconfig())

# --- 
HOST = 'www.sergio.dev.br'
PORT = 80

try:
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((HOST, PORT))
    print(f'Conectando à "{HOST}/{PORT}"')
    request = f'GET / HTTP/1.1\r\nHOST: {HOST}\r\n\r\n'
    sock.sendall(request.encode())
    print('Requisição enviada:', request.encode())
    data = sock.recv(4096)
    print('\nData:\n',data.decode())
except OSError as e:
    print(f'Erro de soquete: {e}')
finally:
    if sock:
        sock.close()