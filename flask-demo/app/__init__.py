#!flask/Scripts/python.exe

# -*- coding: utf-8 -*-

from flask import Flask, jsonify

from flask_sqlalchemy import SQLAlchemy
from flask_restless import APIManager
import datetime
import os

from app import config

app = Flask(__name__)
# init config
app.config.from_object(config)

# init views
from app import views

#init database
from app.database import db, restless
from app.model import user, system
db.create_all()

from app import db_init
db_init.init()


if __name__ == "__main__":
	app.run(debug=True)
