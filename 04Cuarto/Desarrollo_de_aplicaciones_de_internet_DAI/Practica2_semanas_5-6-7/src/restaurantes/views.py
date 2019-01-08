# Información útil
# http://notesbyanerd.com/2015/12/19/joint-login-and-signup-django-allauth-view/
from django.shortcuts import render, redirect
from django.http import HttpResponse
from .models import menu_items, Restaurants
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

def Delete(request, oid):
    if request.user.is_authenticated:
        # Devuelve una lista [0] es la cantidad
        # y el segundo [1] es un un diccionario
        is_deleted = Restaurants.objects.get(id=oid).delete()[1]
        list_restaurants = Restaurants.objects.all()[:100]
        context = {
            "navigation": menu_items,
            "search_result": list_restaurants,
            "is_deleted": is_deleted.get('restaurantes.restaurants'),
        }
        result = render(request, 'search.html', context)
    else:
        result = redirect('/login/')
    return result

def Edit(request, oid):
    if request.user.is_authenticated:
        if request.method == 'POST':
            item_created = Restaurants.objects.create()
            if item_created:
                item_select = Restaurants.objects.get(id=item_created.id)
            print(item_created)
            context = {
                "navigation": menu_items,
                "item_select": item_select,
            }
        elif request.method == 'GET':
            item_select = Restaurants.objects.get(id=oid)
            context = {
                "navigation": menu_items,
                "item_select": item_select,
            }
        result = render(request, 'editing.html', context)
    else:
        result = redirect('/login/')
    return result

def New(request):
    if request.user.is_authenticated:
        if request.method == 'POST':
            context = {
                "navigation": menu_items,
            }
        elif request.method == 'GET':
            context = {
                "newform": NewRestaurant(),
                "navigation": menu_items,
            }
        result = render(request, 'new.html', context)
    else:
        result = redirect('/login/')
    return result

def Search(request):
    if request.user.is_authenticated:
        if request.method == 'POST':
            list_restaurants = Restaurants.objects.filter(name__icontains=request.POST.get('string'))
            context = {
                "navigation": menu_items,
                "search_result": list_restaurants,
            }
        elif request.method == 'GET':
            list_restaurants = Restaurants.objects.all()[:100]
            context = {
                "navigation": menu_items,
                "search_result": list_restaurants,
            }
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
