from app import db
from app.model import user
from app.model import system

def init():
	#init_system()
	#init_user()
	pass

def init_user():
	try:
		u = user.User(id='0',username='admin',password='chengjianfeng100')
		db.session.add(u)
		db.session.commit()
	except Exception as e:
		print(e)

def init_system():
	try:
		s = system.System(id='0', name='CDMS', desc='公共数据管理系统')
		db.session.add(s)
		db.session.commit()
	except Exception as e:
		print(e)
