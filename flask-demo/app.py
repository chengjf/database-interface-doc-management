#!flask/Scripts/python.exe

# -*- coding: utf-8 -*-

from flask import Flask, jsonify

from flask_sqlalchemy import SQLAlchemy
from flask_restless import APIManager
import datetime
import os

app = Flask(__name__)

basedir = os.path.abspath(os.path.dirname(__file__))

SQLALCHEMY_DATABASE_URI = 'sqlite:///' + os.path.join(basedir, 'app.db')
SQLALCHEMY_MIGRATE_REPO = os.path.join(basedir, 'db_repository')

app.config['SQLALCHEMY_DATABASE_URI'] = SQLALCHEMY_DATABASE_URI

db = SQLAlchemy(app)
restless = APIManager(app, flask_sqlalchemy_db=db)

class User(db.Model):
	"""
	User
	"""
	id = db.Column(db.Integer, primary_key=True)
	username = db.Column(db.String(255), unique=True, nullable=False)
	password = db.Column(db.String(255), nullable=False)

restless.create_api(User, methods=['GET', 'POST', 'DELETE', 'PATCH', 'PUT'], results_per_page=100)

db.create_all()


tasks = [
	{
		'id':1,
		'title':u'吃饭',
		'description':u'eat',
		'done':False
	},
	{
		'id':2,
		'title':u'睡觉',
		'description':u'sleep',
		'done':False
	}
]


@app.route('/todo/api/v1.0/tasks', methods=['GET'])
def get_tasks():
	print('get tasks');
	return jsonify({'tasks':tasks})

@app.route('/')
def index():
	return "Hello, world!"

if __name__ == "__main__":
	app.run(debug=True)
