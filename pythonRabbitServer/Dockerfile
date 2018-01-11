FROM python:3
ADD ./rabbitServer.py /
RUN pip install pika
EXPOSE 5672
EXPOSE 4671
EXPOSE 4369
CMD ["python","rabbitServer.py"]
