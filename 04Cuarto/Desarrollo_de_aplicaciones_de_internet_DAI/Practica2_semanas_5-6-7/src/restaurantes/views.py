from django.shortcuts import render, redirect
from django.http import HttpResponse
from django.views.generic import TemplateView
from .models import menu_items
from .forms import LoginForm

def Index(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'index.html', context)
    return result

def Blank(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'blank.html', context)
    return result

def Grid(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'grid.html', context)
    return result

def Icons(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'icons.html', context)
    return result

def Typography(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'typography.html', context)
    return result

def Notifications(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'notifications.html', context)
    return result

def Buttons(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'buttons.html', context)
    return result

def Panels_Wells(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'panels-wells.html', context)
    return result

def Forms(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'forms.html', context)
    return result

def Tables(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'tables.html', context)
    return result

def Morris(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'morris.html', context)
    return result

def Delete(request):
    context = {
        "rid": request.session.get('rid'),
        "navigation": menu_items,
    }
    result = render(request, 'delete.html', context)
    return result

def Edit(request):
    context = {
        "rid": request.session.get('rid'),
        "navigation": menu_items,
    }
    result = render(request, 'flot.html', context)
    return result

def Update(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'update.html', context)
    return result

def New(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'new.html', context)
    return result

def Search(request):
    iterador = restaurantes.find().limit(10)
    context = {
        "navigation": menu_items,
        "lista": list(iterador)
    } # Aquí van las variables para la plantilla
    if request.session.get('auth'):
        result = render(request, 'index.html', context)
    else:
        result = render(request, 'login.html', context)
    return result

def Doc(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'doc.html', context)
    return result

def User(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'user.html', context)
    return result

def Settings(request):
    context = {
        "navigation": menu_items,
    }
    result = render(request, 'settings.html', context)
    return result

def Login(request):
    response = redirect('/login/')
    return response