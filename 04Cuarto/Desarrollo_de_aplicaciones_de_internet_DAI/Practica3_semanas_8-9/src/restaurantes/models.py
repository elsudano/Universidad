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

class modelRestaurants(models.Model):
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
        (1, 'Leche y derivados'),
        (2, 'Huevos'),
        (3, 'Frutos secos'),
        (4, 'Marisco y pescado'),
        (5, 'Soja'),
        (6, 'Trigo'),
    )

    _id = models.ObjectIdField()
    denomination = models.CharField(max_length=200)
    allergens = models.CharField(max_length=20, choices=TYPES)

    class Meta:
        abstract = True

    def __str__(self):
        cook_string = {'_id':self._id, 'denomination':self.denomination, 'allergens':self.allergens}
        return str(cook_string)

class PlatesManager(models.Manager):
    def create_plate(self, name, deno, allergens, price):
        cooktype = CookType(denomination=deno, allergens=allergens)
        plate = self.create(name=name, cooktype=cooktype, price=price)
        return plate

class modelPlates(models.Model):
    _id = models.ObjectIdField()
    name = models.CharField(max_length=200, unique=True)
    cooktype = models.EmbeddedModelField(model_container=CookType)
    price = models.PositiveIntegerField()
	
    objects = PlatesManager()

    def __str__(self):
        plate_string = {'_id':self._id, 'name':self.name, 'cooktype':{'denmination':self.cooktype.denomination, 'allergens':self.cooktype.allergens}, 'price':self.price}
        return str(plate_string)