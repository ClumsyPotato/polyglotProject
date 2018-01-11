from django.http import HttpResponse




import random
from item import Item
from Raid import Raid
from jRaid  import jRaid
from django.views.decorators.csrf import csrf_exempt
import  json
from django.core import serializers


@csrf_exempt
def saveRaid(request):
    body = request.body.decode('utf-8')
    print(body)
    raid = Raid(name=body)
    raid.save()
    return HttpResponse("Raid was saved")

@csrf_exempt
def getRaidByName(request,raidName):
    #request.body.decode('utf-8')
    raid = []
    print(raidName)

    # retrive raids from Database
    qRaid = Raid.objects.filter(name=raidName)
    for o in qRaid:
        print(o.name)
        r=jRaid(o.id,o.name)
        raid.append(vars(r))


    # onject to json
    jsonRaidList =json.dumps(raid)
    return HttpResponse(jsonRaidList)


    #wrong json
    #jRaid = serializers.serialize("json", raid)
    return HttpResponse(jRaid)


def getAllRaids(request):
    raid =[]

    # get all raids from database
    raidList = Raid.objects.all()
    for o in raidList:
        r=jRaid(o.id,o.name)
        raid.append(vars(r))

    #objects to json
    jsonRaidList = json.dumps(raid)
    return HttpResponse(jsonRaidList)


#####################################################

def deleteRaid(request):
    return HttpResponse("TODO")




def hello_django(request):
    return HttpResponse("Hello django")

def root_page(request):
    return HttpResponse("Root Homepage")

def random_number(request,max_rand=100):
    random_num = random.randrange(0,int(max_rand))

    msg = "Random Number Between 0 and %s : %d" % (max_rand, random_num)

    return HttpResponse(msg)

def save_item(request):
    item2 = Item(name="sword", dmg=10)
    item2.save()
    return HttpResponse("Item was saved")

def get_all_items(request):
    print(Item.objects.all())
    items = Item.objects.all()



    for item in items:
        print(item.__str__())
        print(item.id)
    return HttpResponse(Item.objectpythos.all())

#def getItemName(request):
 #   return HttpResponse(Item.object.get_name())

