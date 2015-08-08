# -*- coding: utf-8 -*-

from app import db
from app import restless
from app import app

class System(db.Model):
	"""
	system
	"""
	id = db.Column(db.Integer, primary_key=True)
	name = db.Column(db.String(255), unique=True, nullable=False)
	desc = db.Column(db.String(512))

restless.create_api(System, collection_name='systems', url_prefix='/api/v1',methods=['GET', 'POST', 'DELETE','PUT'], results_per_page=100)

