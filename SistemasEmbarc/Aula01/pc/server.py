import socket
import struct
import sys
import _thread

# Cria a página a ser mostrado no cliente (HTML)
index = (
    'HTTP/1.1 200 OK\r\n',
    'Content-Type: text/html\r\n',
    'Connection: close\r\n',
    '\r\n',
    '\r\n'
    '<!DOCTYPE HTML>\r\n',
    '<html><head><title> TI328 </title></head>\r\n',
    '<body><h1>Bem vindo ao teste do Guepo</h1>',
    '<p>Vamos inciar no mundo da IoT</p></body>\r\n',
    '</html>\r\n'
)


def get_local_ip():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        sock.connect(('10.255.255.255', 1))
        IP = sock.getsockname()[0]
    except:
        IP = '127.0.0.1' # localhost
    finally:
        sock.close()
    return IP


def on_new_client(client_socket, addr):
    while True:
        msg, address = client_socket.recvfrom(1024)
        if msg:
            break
    response = msg.decode()
    print(response, addr, address)
    client_socket.send("".join(index).encode())
    client_socket.close()



HTTP = get_local_ip()
PORT = 80

sockHTTP = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    sockHTTP.bind((HTTP, PORT))
except socket.error as e:
    sockHTTP.bind(('', PORT))

sockHTTP.listen(5) # Executa até 5 conexões simultâneas 

print(f"Esperando conexão em http://{HTTP}")
while True:
    c, addr = sockHTTP.accept()
    _thread.start_new_thread(on_new_client, (c, addr))
