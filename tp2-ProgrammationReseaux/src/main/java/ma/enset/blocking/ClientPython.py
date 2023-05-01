import socket

host = 'localhost'  # adresse IP ou nom d'hôte du serveur
port = 1235  # port d'écoute du serveur

# établir la connexion avec le serveur
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect((host, port))

# envoyer un message au serveur pour se présenter
client_socket.send('Hello from Python client!'.encode())

while True:
    # attendre une entrée utilisateur pour envoyer un message
    message = input('Enter message: ')

    # envoyer le message au serveur
    client_socket.send(message.encode())

    # attendre la réponse du serveur
    response = client_socket.recv(1024).decode()

    # afficher la réponse du serveur
    print('Server response: ' + response)

    # fermer la connexion si l'utilisateur a entré 'exit'
    if message == 'exit':
        client_socket.close()
        break

    # envoyer un message à un client spécifique
    if '=>' in message:
        message_parts = message.split('=>')
        recipient_id = message_parts[0].strip()
        message_body = message_parts[1].strip()
        client_socket.send(f'{recipient_id}=>{message_body}'.encode())