import numpy as np
import json
import pandas as pd
import requests


df = pd.read_csv("../user.csv")
df.score = df.score.astype("int")
df.userId = df.userId.astype("int")
prefix = "http://localhost:8080/user/"

def delete(id):
    requests.delete(f"{prefix}delete/{id}")


def deleteAll():
    r = requests.get(f"{prefix}findAll")
    datas = json.loads(r.text)
    for data in datas:
        delete(data.get("id"))

def add(data):
    data["score"] = int(data["score"])
    data["userId"] = int(data["userId"])
    requests.post(f"{prefix}add", data=json.dumps(data), headers={'Content-Type': 'application/json'})

def addAll():
    indexes = df.index.to_list()
    for index in indexes:
        print(index)
        add(df.iloc[index].to_dict())

addAll()