from django import template

register = template.Library()

@register.filter(is_safe=True)
def getid(obj):
    return obj._id

@register.filter(is_safe=True)
def getname(obj):
    return obj.name

@register.filter(is_safe=True)
def getcoordinates(obj):
    return obj.location.coordinates

@register.filter(is_safe=True)
def gettype(obj):
    return obj.location.type