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
    {'href':'/maps/','icon':'fa-map-marker ','caption':'Maps'},
    #{'href':'/forms','icon':'','caption':'Forms'},
]

class Location(models.Model):
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

class RestaurantsManager(models.Manager):
    def create_restaurant(self, name, long, lati):
        location = Location([float(long),float(lati)],'Point')
        restaurant = self.create(name=name, location=location)
        return restaurant

class Restaurants(models.Model):
    _id = models.ObjectIdField()
    location = models.EmbeddedModelField(model_container=Location)
    name = models.CharField(max_length=50)

    # objects = models.DjongoManager()
    objects = RestaurantsManager()

    def __str__(self):
        restaurant_string = {'_id':self._id,'location':{'coordinates':self.location.coordinates,'type':self.location.type},'name':self.name}
        return str(restaurant_string)

class CookType(models.Model):
    TYPES = (
        ('Point', 'Point'),
        ('Polygon', 'Polygon'),
    )
    _id = models.ObjectIdField()
    allergens = models.CharField(max_length=20, choices=TYPES)
    deno = models.CharField(max_length=200)

    def __str__(self):
        cook_string = {'_id':self._id}
        return str(cook_string)

class Plato(models.Model):
    _id = models.ObjectIdField()
    name = models.CharField(max_length=200, unique=True)
    cooktype = models.CharField(max_length=200)
    price = models.PositiveIntegerField()
	
    def __str__(self):
        plate_string = {'_id':{'coordinates':self.location.coordinates,'type':self.location.type}}
        return str(plate_string)