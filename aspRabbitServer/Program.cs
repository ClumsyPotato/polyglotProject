using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;

using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using Newtonsoft.Json;
using System.Net;
using System.Text;


namespace aspRabbitServer
{
    public class Program
    {
        public static void Main(string[] args)
        {
            
            
         Console.WriteLine("Starting rpc server ");

		List<Item> itemList = new List<Item>();
		itemList.Add(new Item("sword",20));
		itemList.Add(new Item("bow",15));

        Console.WriteLine(itemList[1].name);
        Item tem = new Item("ding",90);
		Console.WriteLine(JsonConvert.SerializeObject(tem));
	     string json = JsonConvert.SerializeObject(itemList, Formatting.Indented);
         Console.WriteLine(json);
 
	    var factory = new ConnectionFactory() { HostName = "messagebroker",UserName="spring" ,Password="spring"};
            using (var connection = factory.CreateConnection())
            using (var channel = connection.CreateModel())
	      {

	         
		var queueName ="item_queue";	
		
		channel.ExchangeDeclare(exchange: "item", type: "direct",durable: true); 
                channel.QueueDeclare(queue: queueName, durable: false,
                                     exclusive: false, autoDelete: false, arguments: null);
                channel.BasicQos(0, 1, false);
		channel.QueueBind(queue: queueName, exchange: "item", routingKey:"item_key");
                var consumer = new EventingBasicConsumer(channel);
                channel.BasicConsume(queue: queueName,
                                      autoAck: true, consumer: consumer);
                Console.WriteLine(" [x] Awaiting RPC requests");
                
    
            consumer.Received += (model, ea) =>
            {
                string response = "here is your response";
		Console.WriteLine("Received a Message");
                var body = ea.Body;
                var props = ea.BasicProperties;
                var replyProps = channel.CreateBasicProperties();
                replyProps.CorrelationId = props.CorrelationId;
	 //	replyProps.CorrelationId = "yodeldido";
		Console.WriteLine("Received:"+Encoding.UTF8.GetString(body));
		Console.WriteLine("*****Printing Stuff*****");
		Console.WriteLine("replyto: "+ props.ReplyTo);
		Console.WriteLine("Correlation ID:" + replyProps.CorrelationId );
	//	Console.WriteLine("replyprops: "+ replyProps.toString());
		Console.WriteLine("ea to string "+ea.ToString());		
		Console.WriteLine("props: "+ ea.BasicProperties.ToString());
		var responseBytes = Encoding.UTF8.GetBytes(response);
        var responseList = Encoding.UTF8.GetBytes(json);
                channel.BasicPublish(exchange: "", routingKey: props.ReplyTo,
                      basicProperties: replyProps, body: responseList);
               // channel.BasicAck(deliveryTag: ea.DeliveryTag,
                //      multiple: false);
	   };

            Console.WriteLine(" Press [enter] to exit.");
            Console.ReadLine();

            BuildWebHost(args).Run();
        }
        }

        public static IWebHost BuildWebHost(string[] args) =>
            WebHost.CreateDefaultBuilder(args)
                .UseStartup<Startup>()
                .Build();
    }
}
