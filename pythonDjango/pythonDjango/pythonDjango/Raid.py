from django.db import models

class Raid(models.Model):
    #id = models.AutoField(primary_key=True) id is automatically created
    name = models.CharField(max_length=10)

#    def __init__(self, name):
#        self.name =name








