# -*- coding: utf-8 -*-

from app import app
from flask import jsonify
from flask import render_template

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
@app.route('/index')
def index():
	return app.send_static_file("index.html")
	#return "Hello, world!"