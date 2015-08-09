# -*- coding: utf-8 -*-

from app import	db
from app import	restless
from app import	app
import json

class System(db.Model):
	"""
	system
	"""
	id = db.Column(db.Integer, primary_key=True)
	number = db.Column(db.String(255), unique=True, nullable=False)
	name = db.Column(db.String(255), nullable=False)
	desc = db.Column(db.String(512))

def get_single_preprocessor(instance_id=None, **kw):
    """Accepts a single	argument, `instance_id`, the primary key of the
    instance of	the model to get.

    """
    pass

def get_single_postprocessor(result=None, **kw):
    """Accepts a single	argument, `result`, which is the dictionary
    representation of the requested instance of	the model.

    """
    pass
    obj = result.copy()
    result.clear()
    result['system'] = obj


def get_many_preprocessor(search_params=None, **kw):
    """Accepts a single	argument, `search_params`, which is a dictionary
    containing the search parameters for the request.

    """
    pass
    print("start get_many_preprocessor")
    print(search_params)
    print("end get_many_preprocessor")

def get_many_postprocessor(result=None,	search_params=None, **kw):
    """Accepts two arguments, `result`,	which is the dictionary
    representation of the JSON response	which will be returned to the
    client, and	`search_params`, which is a dictionary containing the
    search parameters for the request (that produced the specified
    `result`).

    """
    pass
    print("start get_many_postprocessor")
    objects = result['objects']
    result.clear()
    result['systems'] = objects
    print(result)
    print("end get_many_postprocessor")

def put_single_preprocessor(instance_id=None,	data=None, **kw):
    """Accepts two arguments, `instance_id`, the primary key of	the
    instance of	the model to patch, and	`data`,	the dictionary of fields
    to change on the instance.

    """
    print("start put_single_preprocessor")
    print(data)
    system = data['system']
    data.clear()
    data.update(system)
    print("end put_single_preprocessor")
    pass

def put_single_postprocessor(result=None, **kw):
    """Accepts a single	argument, `result`, which is the dictionary
    representation of the requested instance of	the model.

    """

    print("start put_single_postprocessor")
    print(result)
    print("end put_single_postprocessor")
    pass

def put_many_preprocessor(search_params=None,	data=None, **kw):
    """Accepts two arguments: `search_params`, which is	a dictionary
    containing the search parameters for the request, and `data`, which
    is a dictionary representing the fields to change on the matching
    instances and the values to	which they will	be set.

    """
    pass

def put_many_postprocessor(query=None, data=None, search_params=None,
			     **kw):
    """Accepts three arguments:	`query`, which is the SQLAlchemy query
    which was inferred from the	search parameters in the query string,
    `data`, which is the dictionary representation of the JSON response
    which will be returned to the client, and `search_params`, which is	a
    dictionary containing the search parameters	for the	request.

    """
    pass


def post_preprocessor(data=None, **kw):
    """Accepts a single	argument, `data`, which	is the dictionary of
    fields to set on the new instance of the model.

    """
    print(data)
    print("start post_preprocessor")

    system = data['system']
    data.clear()
    data.update(system)
    print(data)
    print("end post_preprocessor")
    pass

def post_postprocessor(result=None, **kw):
    """Accepts a single	argument, `result`, which is the dictionary
    representation of the created instance of the model.

    """
    print("start post_postprocessor")
    print(result)
    print("end post_postprocessor")
    pass

def delete_single_preprocessor(instance_id=None, **kw):
    """Accepts a single	argument, `instance_id`, which is the primary key
    of the instance which will be deleted.

    """
    print("start delete_single_preprocessor")
    pass
    print(instance_id)
    print("end delete_single_preprocessor")

def delete_single_postprocessor(was_deleted=None, **kw):
    """Accepts a single	argument, `was_deleted`, which represents whether
    the	instance has been deleted.

    """
    print("start delete_single_postprocessor")
    pass
    print(was_deleted)
    print("end delete_single_postprocessor")

def delete_many_preprocessor(search_params=None, **kw):
    """Accepts a single	argument, `search_params`, which is a dictionary
    containing the search parameters for the request.

    """
    pass

def delete_many_postprocessor(result=None, search_params=None, **kw):
    """Accepts two arguments: `result`,	which is the dictionary
    representation of which is the dictionary representation of	the JSON
    response which will	be returned to the client, and `search_params`,
    which is a dictionary containing the search	parameters for the
    request.

    """
    pass


#restless.create_api(System, collection_name='systems',	url_prefix='/api/v1',methods=['GET', 'POST', 'DELETE','PUT'], results_per_page=100)

restless.create_api(
    System,
    methods=['GET', 'POST', 'DELETE', 'PUT'],
    url_prefix='/api/v1',
    collection_name='systems',
    results_per_page=-1,
    preprocessors={
		'GET_MANY': [get_many_preprocessor],
		'POST':	[post_preprocessor],
		'GET_SINGLE':[get_single_preprocessor],
		'PUT_SINGLE':[put_single_preprocessor],
		'PUT_MANY':[put_many_preprocessor],
		'DELETE_SINGLE':[delete_single_preprocessor],
		'DELETE_MANY':[delete_many_preprocessor]
    },
    postprocessors={
		'GET_MANY': [get_many_postprocessor],
		'POST':	[post_postprocessor],
		'GET_SINGLE':[get_single_postprocessor],
		'PUT_SINGLE':[put_single_postprocessor],
		'PUT_MANY':[put_many_postprocessor],
		'DELETE_SINGLE':[delete_single_postprocessor],
		'DELETE_MANY':[delete_many_postprocessor]
    }
)