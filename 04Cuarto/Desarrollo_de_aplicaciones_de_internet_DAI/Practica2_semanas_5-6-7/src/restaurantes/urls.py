from django.urls import include, path
from . import views

urlpatterns = [
    path('', views.Index, name='index'),
    path('search/', views.Search, name='search'),
]