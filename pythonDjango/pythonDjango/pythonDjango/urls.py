"""pythonDjango URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url



from django.conf.urls import url
from django.contrib import admin


from views import hello_django, save_item
from views import root_page
from views import  random_number
from views import get_all_items

from views import saveRaid
from views import getRaidByName
from views import getAllRaids
from views import deleteRaid

urlpatterns = [
    url(r'^saveRaid/$', saveRaid),
    url(r'^getRaidByName/(?P<raidName>\w{0,50})',getRaidByName),
    url(r'^getAllRaids/$',getAllRaids),
    url(r'deleteRaid/$',deleteRaid),

    url(r'^admin/', admin.site.urls),
    url(r'^hellodjango/$', hello_django),
    url(r'^$',root_page),
    url(r'^randomnum/(\d+)/$', random_number),
    url(r'^saveItem/$', save_item),
    url(r'^getItem/$', get_all_items)
]


