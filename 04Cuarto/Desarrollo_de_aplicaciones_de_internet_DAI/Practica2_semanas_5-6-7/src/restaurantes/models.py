from django.db import models
from pymongo import MongoClient

client = MongoClient()
db = client.dai                 # base de datos
restaurantes = db.restaurants   # colección

# Esta variable es para generar el Menú de la página
menu_items = [
    {'href':'/','icon':'fa-dashboard','caption':'Dashboard'},
    {'href':'/tables/','icon':'fa-table','caption':'Tables'},
    {'href':'/forms/','icon':'fa-edit','caption':'Forms'},
    {'href':'/panels-wells/','icon':'fa-tasks','caption':'Panels and Wells'},
    {'href':'/buttons/','icon':'fa-play-circle','caption':'Buttons'},
    {'href':'/notifications/','icon':'fa-comments','caption':'Notifications'},
    {'href':'/typography/','icon':'fa-header','caption':'Typography'},
    {'href':'/icons/','icon':'fa-tags','caption':'Icons'},
    {'href':'/grid/','icon':'fa-wrench','caption':'Grid'},
    {'href':'/doc/','icon':'fa-book','caption':'Documentation'},
    #{'href':'/forms','icon':'','caption':'Forms'},
]
