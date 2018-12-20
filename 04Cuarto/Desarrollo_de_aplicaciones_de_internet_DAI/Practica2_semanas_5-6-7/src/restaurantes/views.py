from django.shortcuts import render
from django.http import HttpResponse
from .models import restaurantes, menu_items
from .forms import LoginForm

def _default_response(request):
    context = {
        "login_form": LoginForm(request.POST)
    }
    return render(request, 'login.html', context)

def Index(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'index.html', context)
    return result

def Blank(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'blank.html', context)
    return result

def Grid(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'grid.html', context)
    return result

def Icons(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'icons.html', context)
    return result

def Typography(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'typography.html', context)
    return result

def Notifications(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'notifications.html', context)
    return result

def Buttons(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'buttons.html', context)
    return result

def Panels_Wells(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'panels-wells.html', context)
    return result

def Forms(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'forms.html', context)
    return result

def Tables(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'tables.html', context)
    return result

def Morris(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'morris.html', context)
    return result

def Delete(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "rid": request.session.get('rid'),
            "navigation": menu_items,
        }
        result = render(request, 'delete.html', context)
    return result

def Edit(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "rid": request.session.get('rid'),
            "navigation": menu_items,
        }
        result = render(request, 'flot.html', context)
    return result

def Update(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'update.html', context)
    return result

def New(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'new.html', context)
    return result

def Search(request):
    iterador = restaurantes.find().limit(10)
    context = {
        "lista": list(iterador)
    } # Aquí van las variables para la plantilla
    if request.session.get('auth'):
        result = render(request, 'index.html', context)
    else:
        result = render(request, 'login.html', context)
    return result

def Doc(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'doc.html', context)
    return result

def User(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'user.html', context)
    return result

def Settings(request):
    result = _default_response(request)
    if request.session.get('auth'):
        context = {
            "user": request.session.get('user'),
            "navigation": menu_items,
        }
        result = render(request, 'settings.html', context)
    return result