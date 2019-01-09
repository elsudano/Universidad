# Info from this
# https://stackoverflow.com/questions/12303478/how-to-customize-user-profile-when-using-django-allauth/12308807#12308807
# http://notesbyanerd.com/2015/12/19/joint-login-and-signup-django-allauth-view/
from django import forms
from allauth.account.forms import LoginForm, SignupForm

class MyLoginForm(LoginForm):

    def __init__(self, *args, **kwargs):
        super(MyLoginForm, self).__init__(*args, **kwargs)
        self.fields['login'].widget = forms.TextInput(attrs={'class':'form-control'})
        self.fields['password'].widget = forms.PasswordInput(attrs={'class':'form-control'})

class MySignupForm(SignupForm):

    def __init__(self, *args, **kwargs):
        super(MySignupForm, self).__init__(*args, **kwargs)
        self.fields['username'].widget = forms.TextInput(attrs={'class':'form-control'})
        self.fields['email'].widget = forms.EmailInput(attrs={'class':'form-control'})
        self.fields['password1'].widget = forms.PasswordInput(attrs={'class':'form-control'})
        self.fields['password2'].widget = forms.PasswordInput(attrs={'class':'form-control'})
        

class Restaurant(forms.Form):
    name = forms.CharField(max_length=100, label='Restaurant Name')
    long = forms.FloatField(label='Longitud position')
    lati = forms.FloatField(label='Latitud position')

    def __init__(self, *args, **kwargs):
        super(Restaurant, self).__init__(*args, **kwargs)
        self.fields['name'].widget = forms.TextInput(attrs={'class':'form-control'})
        self.fields['long'].widget = forms.NumberInput(attrs={'class':'form-control', 'min':'-360', 'max':'360', 'step':'0.000001'})
        self.fields['lati'].widget = forms.NumberInput(attrs={'class':'form-control', 'min':'-360', 'max':'360', 'step':'0.000001'})

class EditRestaurant(Restaurant):
    oid = forms.UUIDField()

    def __init__(self, *args, **kwargs):
        super(EditRestaurant, self).__init__(*args, **kwargs)
        self.fields['oid'].widget = forms.HiddenInput()