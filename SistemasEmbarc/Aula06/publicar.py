from time import sleep
from umqtt import MQTTClient
import ubinascii
import machine
import network
import onewire, ds18x20 

# --- Configuração do Sensor DS18B20 ---
pino_sensor = machine.Pin(26)
ds_sensor = ds18x20.DS18X20(onewire.OneWire(pino_sensor))

def le_temperatura():
    try:
        roms = ds_sensor.scan() 
        if not roms:
            return None
        
        ds_sensor.convert_temp() 
        sleep(0.75) 
        
        temp = ds_sensor.read_temp(roms[0])
        return round(temp, 2) 
    except Exception as e:
        print("Erro na leitura do sensor:", e)
        return None

# --- Conexão com a Rede ---
rede = network.WLAN(network.STA_IF)
rede.active(True)
rede.connect("Wokwi-GUEST", "")
while not rede.isconnected():
  print(".", end="")
  sleep(0.5)
print("\nConectado em", rede.ifconfig()[0])

def cbTrataMsg(topic, msg):
    print(f'Msg recebida no tópico: {topic.decode("utf-8")}')
    print(msg.decode('utf-8'))

# --- Configuração MQTT ---
mqtt_server = "1638f261a5864ed5b1ec3b3c10376baa.s1.eu.hivemq.cloud"
port        = 8883
user        = b"Cotuca"
pwd         = b"Cotuca123"
topic_pub   = b"temperatura"

print("Inicializando conexão com o broker...")
client_id = ubinascii.hexlify(machine.unique_id())

try:
    client = MQTTClient(
        client_id, 
        mqtt_server,
        port=port,
        user=user,
        password=pwd,
        ssl=True,
        ssl_params={'server_hostname': mqtt_server} 
    )
    client.set_callback(cbTrataMsg)
    client.connect()
    client.subscribe(topic_pub)
    print("MQTT Conectado!")
except OSError as e:
    print("Erro ao conectar no MQTT:", e)
    sleep(2)
    machine.reset()

# --- Loop Principal ---
while True:
    try:
        client.check_msg()
        
        valor_temp = le_temperatura()
        
        if valor_temp is not None:
            msg_json = '{"temperatura": ' + str(valor_temp) + '}' # -> mensagem que o cliente vai publicar
            print(f"Publicando: {msg_json}")
            client.publish(topic_pub, msg_json)
        else:
            print("Sensor não encontrado!")

        sleep(5) #tempo entre cada publicação
        
    except OSError as e:
        print("Erro de conexão:", e)
        sleep(2)
        machine.reset()
    except KeyboardInterrupt:
        print("Desconectando...")
        client.disconnect()
        break