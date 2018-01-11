#!/usr/bin/python	

import json

class Item(object):
 
 def __init__(self,name,dmg):
  self.name=name
  self.dmg=dmg
  




item=[]

item.append(vars(Item("bow",20)))
item.append(vars(Item("dagger",20)))

for i in range(len(item)):
    print((item[i]))

#print vars(item)

#print json.dumps(vars(item))

import pika

credentials = pika.PlainCredentials('spring', 'spring')
con = pika.BlockingConnection(pika.ConnectionParameters(host='messagebroker',credentials=credentials))

channel = con.channel()

channel.queue_declare(queue='rpc_queue')

channel.exchange_declare(exchange='item', exchange_type='direct',durable=True)
channel.queue_declare(queue='item_queue')
channel.queue_bind(exchange='item',queue='item_queue',routing_key="item_key")




def on_request(ch, method, props, body):
    print("on request was called")
    print(body)
    response="Here is your response string"
    n = 5
    ch.basic_publish(exchange='',routing_key=props.reply_to,
                     properties=pika.BasicProperties(correlation_id = props.correlation_id),
                     body=json.dumps(vars(item)))
    ch.basic_ack(delivery_tag=method.delivery_tag)


#returns all items     
def item_list(ch,method,props,body):
   print("item list was called")
   print(body)
#  list1=["milk","bread","cookies",6]
#   print("Corelation id: " +props.correlation_id)
#   print( props.reply_to) 
   ch.basic_publish(exchange='', routing_key=props.reply_to,
		     properties= pika.BasicProperties(correlation_id = props.correlation_id),
		     body=json.dumps(item))
   ch.basic_ack(delivery_tag=method.delivery_tag)


          


channel.basic_consume(item_list,queue='item_queue')
#channel.basic_consume(on_request,queue='rpc_queue')

print("***Waiting for requests***")

channel.start_consuming()
