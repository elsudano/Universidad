from django.urls import include, path
from . import views

urlpatterns = [
    path('', views.Index, name='index'),
    path('', include('allauth.urls')),
    path('blank/', views.Blank, name='blank'),
    path('grid/', views.Grid, name='grid'),
    path('icons/', views.Icons, name='icons'),
    path('typography/', views.Typography, name='typography'),
    path('notifications/', views.Notifications, name='notifications'),
    path('buttons/', views.Buttons, name='buttons'),
    path('panels-wells/', views.Panels_Wells, name='panels-Wells'),
    path('forms/', views.Forms, name='forms'),
    path('tables/', views.Tables, name='tables'),
    path('morris/', views.Morris, name='morris'),
    path('restaurant/del/<str:oid>/', views.DeleteRestaurant, name='deleterestaurant'),
    path('restaurant/edit/', views.EditRestaurantPost, name='editrestaurant'),
    path('restaurant/edit/<str:oid>/', views.EditRestaurantGet, name='editrestaurant'),
    path('restaurant/new/', views.NewRestaurant, name='newrestaurant'),
    path('plate/del/<str:oid>/', views.DeletePlate, name='deleteplate'),
    path('plate/edit/', views.EditPlatePost, name='editplate'),
    path('plate/edit/<str:oid>/', views.EditPlateGet, name='editplate'),
    path('plate/new/', views.NewPlate, name='newplate'),
    path('search/', views.Search, name='search'),
    path('doc/', views.Doc, name='doc'),
    path('user/', views.User, name='user'),
    path('settings/', views.Settings, name='settings'),
]