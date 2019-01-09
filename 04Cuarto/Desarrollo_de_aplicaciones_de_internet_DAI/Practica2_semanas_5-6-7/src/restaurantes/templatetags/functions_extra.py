from django import template

register = template.Library()

@register.filter(name='getid')
def getid(d):
    return d.getid()