from django.db import models


class Item(models.Model):

#    id = models.IntegerField()
    name = models.CharField(max_length=50)
    dmg = models.IntegerField()

    def __str__(self):
        istring ="Name:" +self.name +" Dmg:" +str(self.dmg) +" Id:" + self.id *" "
        return istring


    def get_name(self):
        return self.name




