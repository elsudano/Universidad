from django.shortcuts import render
from django.http import HttpResponse

def index(request):
    return HttpResponse("Hello, world. You're at the polls index.")

def mytest(request):
    context = {} # Aquí van las variables para la plantilla
    return render(request,'login.html', context)
