import network
import gc
import esp
from time import sleep
try:
    import usocket as socket
except:
    import socket
    
esp.osdebug(None)
gc.collect()

# Configuração 
# Station -> conectando à alguém
#STA_SSID = 'iPhone de Vinicius'
#STA_PWD = 'guepo123'
STA_SSID = 'D-Link_DIR-615'
STA_PWD = ''

# Acess Point -> outros podem conectar na ESP
AP_SSID = 'Guepo'
AP_PWD = 'guepo123'


def conectar_rede(modo):
    sta_if = network.WLAN(network.STA_IF)
    ap_if = network.WLAN(network.AP_IF)

    # Ativa as duas interfaces
    sta_if.active(True)
    ap_if.active(True)

    # Configurar o ACESS POINT
    ap_if.config(essid=AP_SSID, password=AP_PWD)
    print(f"Acess Point '{AP_SSID}' create with IP: {ap_if.ifconfig()[0]}")

    # Conectando com STATION
    sta_if.connect(STA_SSID, STA_PWD)
    print(f"Conectando ao {STA_SSID}...")
    time_out = 0
    while not sta_if.isconnected():
        if time_out > 20:
            print("Time out nõa foi possivel conectar!")
            break
        print(".", end="")
        sleep(1)
        time_out += 1

    if sta_if.isconnected():
        print("Conectado com sucesso!")
    else:
        print("Erro ao conectar dispositivo!")

    return sta_if, ap_if


def root_page(ip):
    root = f"""
    <!DOCTYPE html>
    <html>
        <head> 
            <title>ESP32 Teste</title> 
            <meta charset="utf-8">
        </head>
        <body>
            <h1>Controle da ESP</h1>
            <p>Conectado em: <strong>"""+ ip +"""</strong></p>
            <p>24145, 24151, 24159</p>
        </body>
    </html>
    """
    return root


if __name__ == "__main__":
    sta, ap = conectar_rede("modo")
    
    print(sta.ifconfig())
    print(ap.ifconfig())
    
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    ip = sta.ifconfig()[0]
    s.bind(("", 80))
    
    s.listen(5)
    print("Servidor Online! Aguardando conexões...")
    
    while True:
        conn, addr = s.accept()
        print("\nConectado de %s" % str(addr))
        request = conn.recv(1024)
        print("Conteudo do request = %s" % str(request))
        response = root_page(ip)
        conn.send('HTTP/1.1 200 OK\r\n')
        conn.send('Content-Type: text/html\r\n')
        conn.send('Connection: close\r\n\r\n')
        conn.sendall(response)
        conn.close()
