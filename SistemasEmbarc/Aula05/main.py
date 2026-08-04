from time import sleep
from umqtt import MQTTClient
import ubinascii
import machine
import network

# --- CONFIGURAÇÕES ---
WIFI_SSID = "D-Link_DIR-615"
WIFI_PASS = ""
MQTT_SERVER = "4166df98726c4a0cb6bcbd279ddc281a.s1.eu.hivemq.cloud"
MQTT_PORT = 8883
MQTT_USER = b"cotuca"
MQTT_PWD = b"cotuca"
TOPICO_PUB = b"vini"

# --- CONEXÃO WIFI ---
print("Conectando à rede Wi-Fi...")
rede = network.WLAN(network.STA_IF)
rede.active(True)
rede.connect(WIFI_SSID, WIFI_PASS)

while not rede.isconnected():
    print(".", end="")
    sleep(0.5)
print("\nConectado em", rede.ifconfig()[0])

# --- FUNÇÃO DE RECEBIMENTO (CALLBACK) ---
def cbTrataMsg(topic, msg):
    print(f"\n[MSG RECEBIDA] Tópico: {topic.decode()} | Mensagem: {msg.decode()}")

# --- CONFIGURAÇÃO MQTT ---
client_id = ubinascii.hexlify(machine.unique_id())

print("Conectando ao Broker HiveMQ...")
try:
    client = MQTTClient(
        client_id, 
        MQTT_SERVER,
        port=MQTT_PORT,
        user=MQTT_USER,
        password=MQTT_PWD,
        ssl=True,
        ssl_params={'server_hostname': MQTT_SERVER} 
    )
    client.set_callback(cbTrataMsg)
    client.connect()
    client.subscribe(TOPICO_PUB)
    print("Conectado com sucesso!")
except OSError as e:
    print("Erro ao conectar:", e)
    sleep(5)
    machine.reset()

# --- LOOP PRINCIPAL ---
while True:
    try:
        # 1. Verifica se há mensagens para ler
        client.check_msg()
        
        # 2. Envia a mensagem "hello world"
        msg_envio = "hello world"
        print(f"Enviando: {msg_envio} para o tópico {TOPICO_PUB.decode()}")
        client.publish(TOPICO_PUB, msg_envio)
        
        # Aguarda 10 segundos antes de enviar de novo para não floodar o servidor
        sleep(10)
        
    except OSError as e:
        print("Erro durante a execução:", e)
        sleep(2)
        machine.reset()
    except KeyboardInterrupt:
        print("\nDesconectando...")
        client.disconnect()
        break