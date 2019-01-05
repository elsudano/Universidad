# Información útil
# http://notesbyanerd.com/2015/12/19/joint-login-and-signup-django-allauth-view/
from django.shortcuts import render, redirect
from django.http import HttpResponse
from .models import menu_items
from .forms import MyLoginForm, MySignupForm, NewRestaurant

def Index(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'index.html', context)
    else:
        result = redirect('/login/')
    return result

def Blank(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'blank.html', context)
    else:
        result = redirect('/login/')
    return result

def Grid(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'grid.html', context)
    else:
        result = redirect('/login/')
    return result

def Icons(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'icons.html', context)
    else:
        result = redirect('/login/')
    return result

def Typography(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'typography.html', context)
    else:
        result = redirect('/login/')
    return result

def Notifications(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'notifications.html', context)
    else:
        result = redirect('/login/')
    return result

def Buttons(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'buttons.html', context)
    else:
        result = redirect('/login/')
    return result

def Panels_Wells(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'panels-wells.html', context)
    else:
        result = redirect('/login/')
    return result

def Forms(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'forms.html', context)
    else:
        result = redirect('/login/')
    return result

def Tables(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'tables.html', context)
    else:
        result = redirect('/login/')
    return result

def Morris(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'morris.html', context)
    else:
        result = redirect('/login/')
    return result

def Delete(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'delete.html', context)
    else:
        result = redirect('/login/')
    return result

def Edit(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'edit.html', context)
    else:
        result = redirect('/login/')
    return result

def Update(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'update.html', context)
    else:
        result = redirect('/login/')
    return result

def New(request):
    if request.user.is_authenticated:
        context = {
            "newform": NewRestaurant(),
            "navigation": menu_items,
        }
        result = render(request, 'new.html', context)
    else:
        result = redirect('/login/')
    return result

def Search(request):
    iterador = restaurantes.find().limit(10)
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
            "lista": list(iterador)
        } # Aquí van las variables para la plantilla
        result = render(request, 'search.html', context)
    else:
        result = redirect('/login/')
    return result

def Doc(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'doc.html', context)
    else:
        result = redirect('/login/')
    return result

def User(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'user.html', context)
    else:
        result = redirect('/login/')
    return result

def Settings(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'settings.html', context)
    else:
        result = redirect('/login/')
    return result
