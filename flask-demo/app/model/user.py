# -*- coding: utf-8 -*-

from app import db
from app import restless

class User(db.Model):
	"""
	User
	"""
	id = db.Column(db.Integer, primary_key=True)
	username = db.Column(db.String(255), unique=True, nullable=False)
	password = db.Column(db.String(255), nullable=False)

restless.create_api(User, methods=['GET', 'POST', 'DELETE', 'PATCH', 'PUT'], results_per_page=100)