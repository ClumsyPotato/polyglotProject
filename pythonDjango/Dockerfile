FROM python:2
ADD ./pythonDjango /djangoApp
#ADD requirements.txt /djangoApp
WORKDIR /djangoApp
RUN pip install django==1.11.6 && pip install psycopg2
CMD python manage.py migrate &&  python manage.py runserver 0.0.0.0:8080
