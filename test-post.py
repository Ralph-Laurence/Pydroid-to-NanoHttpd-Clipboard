import requests

url = "http://localhost:8080" 
payload = {
    "data": 'test'
}

response = requests.post(url, json=payload)
print(response.text)