#!flask/Scripts/python.exe

# -*- coding: utf-8 -*-

import requests
import json

BASE_URL = 'http://127.0.0.1:5000'

def test_get_tasks_list():
	
	try:
		url = BASE_URL+'/todo/api/v1.0/tasks'
		print("request " + url)
		rsp = requests.get(url, headers={'Accept':'application/json'})
		print(rsp.json())
	except Exception as e:
		raise e

def test_get_user_list():
	url = BASE_URL+'/api/user'
	print("request " + url)
	rsp = requests.get(url, headers={'Accept':'application/json','Content-Type':'application/json'})
	print(rsp.json())

def test_get_user():
	url = BASE_URL+'/api/user/2'
	print("request " + url)
	rsp = requests.get(url, headers={'Accept':'application/json','Content-Type':'application/json'})
	print(rsp.json())

def test_post_user_list():
	url = BASE_URL+'/api/user'
	print("request " + url)
	data = { 'id' : 2, 'username':'chen','password':'abcdefg'}
	rsp = requests.post(url, json.dumps(data), headers={'Accept':'application/json','Content-Type':'application/json'})
	print(rsp.json())

def test_get_user_list_filer_username():
	url = BASE_URL+'/api/user'
	filters = [dict(name='username', op='like', val='%chen%')]
	params = dict(q=json.dumps(dict(filters=filters)))
	print("request " + url)
	rsp = requests.get(url, params=params, headers={'Accept':'application/json','Content-Type':'application/json'})
	print(rsp.json())

if __name__ == '__main__':
	test_get_tasks_list()
	test_get_user_list()
	#test_get_user()
	#test_post_user_list()
	#test_get_user_list_filer_username()