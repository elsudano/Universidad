# Información útil
# http://notesbyanerd.com/2015/12/19/joint-login-and-signup-django-allauth-view/
from django.core.paginator import Paginator
from django.shortcuts import render, redirect
from django.http import HttpResponse
from .models import menu_items, Restaurants
from .forms import MyLoginForm, MySignupForm, Restaurant, EditRestaurant, Plate

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

def Flot(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'flot.html', context)
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

def Highcharts(request):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'highcharts.html', context)
    else:
        result = redirect('/login/')
    return result

def Maps(request):
    if request.user.is_authenticated:
        page = request.GET.get('page')
        # si queremos reducir la cantidad de restaurantes totales.
        all_restaurants = Restaurants.objects.all()#[:9]
        paginator = Paginator(all_restaurants, 8)
        list_part_of_restaurants = paginator.get_page(page)
        context = {
            "navigation": menu_items,
            "restaurants": list_part_of_restaurants,
        }
        result = render(request, 'maps.html', context)
    else:
        result = redirect('/login/')
    return result

def DeleteRestaurant(request, oid):
    if request.user.is_authenticated:
        # Devuelve una lista [0] es la cantidad
        # y el segundo [1] es un un diccionario
        is_deleted = Restaurants.objects.get(_id=oid).delete()[1]
        list_restaurants = Restaurants.objects.all()[:100]
        context = {
            "navigation": menu_items,
            "search_result": list_restaurants,
            "is_deleted": is_deleted.get('restaurantes.Restaurants'),
        }
        result = render(request, 'search.html', context)
    else:
        result = redirect('/login/')
    return result

def EditRestaurantPost(request):
    if request.user.is_authenticated:
        oid = request.POST.get('oid')
        name = request.POST.get('name')
        long = request.POST.get('long')
        lati = request.POST.get('lati')
        result = render(request, 'index.html', {"navigation": menu_items})
        if request.method == 'POST':
            item_select = Restaurants.objects.get(_id=oid)
            item_select.name = name
            item_select.location.coordinates[0] = long
            item_select.location.coordinates[1] = lati
            item_select.save()
            # https://stackoverflow.com/questions/813418/django-set-field-value-after-a-form-is-initialized
            edit_form = EditRestaurant(initial={ \
                'oid':item_select._id, \
                'name':item_select.name, \
                'long':item_select.location.coordinates[0], \
                'lati':item_select.location.coordinates[1]})
            context = {
                "navigation": menu_items,
                "edit_form": edit_form,
                "item_select": item_select,
            }
            result = render(request, 'editing.html', context)
    else:
        result = redirect('/login/')
    return result

def EditRestaurantGet(request, oid):
    if request.user.is_authenticated:
        result = render(request, 'index.html', {"navigation": menu_items})
        if request.method == 'GET':
            item_select = Restaurants.objects.get(_id=oid)
            if item_select:
                edit_form = EditRestaurant(initial={ \
                    'oid':item_select._id, \
                    'name':item_select.name, \
                    'long':item_select.location.coordinates[0], \
                    'lati':item_select.location.coordinates[1]})
            else:
                edit_form = EditRestaurant()
            context = {
                "navigation": menu_items,
                "edit_form": edit_form,
                "item_select": item_select,
            }
            result = render(request, 'editing.html', context)
    else:
        result = redirect('/login/')
    return result

def NewRestaurant(request):
    if request.user.is_authenticated:
        if request.method == 'POST':
            name = request.POST.get('name')
            long = request.POST.get('long')
            lati = request.POST.get('lati')
            item_created = Restaurants.objects.create_restaurant(name=name,long=long,lati=lati)
            if item_created:
                search_result = Restaurants.objects.filter(_id=item_created._id)
            context = {
                "navigation": menu_items,
                "save": item_created,
                "search_result": search_result,
            }
            result = render(request, 'search.html', context)
        elif request.method == 'GET':
            context = {
                "tipo": "Restaurante",
                "newform": Restaurant(),
                "navigation": menu_items,
            }
            result = render(request, 'new.html', context)
    else:
        result = redirect('/login/')
    return result

def DeletePlate(request, oid):
    if request.user.is_authenticated:
        context = {
            "navigation": menu_items,
        }
        result = render(request, 'search.html', context)
    else:
        result = redirect('/login/')
    return result

def EditPlatePost(request):
    if request.user.is_authenticated:
        result = render(request, 'index.html', {"navigation": menu_items})
        if request.method == 'POST':
            context = {
                "navigation": menu_items,
            }
            result = render(request, 'editing.html', context)
    else:
        result = redirect('/login/')
    return result

def EditPlateGet(request, oid):
    if request.user.is_authenticated:
        result = render(request, 'index.html', {"navigation": menu_items})
        if request.method == 'GET':
            context = {
                "navigation": menu_items,
            }
            result = render(request, 'editing.html', context)
    else:
        result = redirect('/login/')
    return result

def NewPlate(request):
    if request.user.is_authenticated:
        if request.method == 'POST':
            context = {
                "navigation": menu_items,
            }
            result = render(request, 'search.html', context)
        elif request.method == 'GET':
            context = {
                "tipo": "Plato",
                "newform": Plate(),
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