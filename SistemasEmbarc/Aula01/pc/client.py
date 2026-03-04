import socket 

# Projeto cliente HTTP em python 
HOST = 'www.slmm.com.br' 
HOST = 'www.sergio.dev.br'
HOST = '177.220.18.79'
PORT = 80 

# Tenta conectar via socket com o servidor
try:
    # Cria o socket 
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((HOST, PORT))
    print('Conectando...\n')

    # Monta a requisição HTTP para o site 
    request = f'GET / HTTP/1.1\r\nHost: {HOST}\r\n\r\n'
    sock.sendall(request.encode())
    print('Enviando requisição...\n')
    data = sock.recv(4096)
    print(data.decode())

except OSError as e:
    print('Socket error:', e)

finally:
    if sock:
        sock.close()
        print('Requisição finalizada')
