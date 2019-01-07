from djongo import models

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

class location(models.Model):
    TYPES = (
        ('Point', 'Point'),
        ('Polygon', 'Polygon'),
    )
    coordinates = models.ListField(default=[])
    type = models.CharField(max_length=7, choices=TYPES)
    
    class Meta:
        abstract = True

    def __str__(self):
        location_string = {'coordinates':self.coordinates,'type':self.type}
        return str(location_string)

class restaurants(models.Model):
    id = models.ObjectIdField()
    location = models.EmbeddedModelField(model_container=location)
    name = models.CharField(max_length=50)

    objects = models.DjongoManager()

    def __str__(self):
        restaurant_string = {'id':self.id,'location':{'coordinates':self.location.coordinates,'type':self.location.type},'name':self.name}
        return str(restaurant_string)
