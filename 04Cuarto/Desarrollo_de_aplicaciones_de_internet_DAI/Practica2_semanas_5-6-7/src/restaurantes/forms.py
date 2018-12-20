from django import forms

class LoginForm(forms.Form):
    your_name = forms.CharField(label='Your name', max_length=100)
    your_name1 = forms.CharField(label='Your name1', max_length=100)
    your_name2 = forms.CharField(label='Your name2', max_length=100)
