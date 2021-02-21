from db import db
from db import Ingredient, Recipe
import json
from flask import Flask, request

# define db filename
db_filename = "todo.db"
app = Flask(__name__)

# setup config
app.config["SQLALCHEMY_DATABASE_URI"] = f"sqlite:///{db_filename}"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
app.config["SQLALCHEMY_ECHO"] = True

# initialize app
db.init_app(app)
with app.app_context():
    db.create_all()

# generalized response formats
def success_response(data, code=200):
    return json.dumps({"success": True, "data": data}), code


def failure_response(message, code=404):
    return json.dumps({"success": False, "error": message}), code

# your routes here
# home page
# includes search bar to search ingredients
@app.route("/api/search/")
def get_search():
    return success_response("Hello", 201)

# function to search for ingredients
# puts searched ingredient into ingredients tab
# once searched, will take you to new link with all recipes that include searched ingredient
@app.route("/api/search/", methods=["POST"])
def create_course():
    body = json.loads(request.data)
    name = body.get('name')
    amount = body.get('amount')

    if amount is None or name is None:
        return failure_response("Incorrect input!")
    new_ingredient = Ingredient(
        name=name,
        amount=amount
    )
    db.session.add(new_ingredient)
    db.session.commit()

    recipes = [r.simple_serialize() for r in Recipe.query.filter_by(name = name).all()]
    if recipes is None:
        return failure_response("Recipe not found!")

    return success_response(recipes, 201)

#list of saved recipes
@app.route("/api/recipes/")
def get_recipes():
    recipes = [r.simple_serialize() for r in Recipe.query.all()]
    return success_response(recipes)


#function to add a recipe to recipes tab
@app.route("/api/recipes/", methods=["POST"])
def add_recipe():
    body = json.loads(request.data)
    name = body.get('name')

    if name is None:
        return failure_response("Incorrect input!")
    new_recipe = Recipe(
        name=name
    )
    db.session.add(new_recipe)
    db.session.commit()
    return success_response(new_recipe.simple_serialize(), 201)

#tab that displays specific recipe user opens
@app.route("/api/recipes/<int:recipe_id>/")
def show_recipe(recipe_id):
    recipe = Recipe.query.filter_by(id=recipe_id).first()
    if recipe is None:
        return failure_response("Recipe not found!")
    return success_response(recipe.serialize())

#function to delete recipes from recipe tab
@app.route("/api/recipes/<int:recipe_id>/", methods=["DELETE"])
def delete_recipe(recipe_id):
    recipe = Recipe.query.filter_by(id=recipe_id).first()
    if recipe is None:
        return failure_response("Recipe not found!")
    db.session.delete(recipe)
    db.session.commit()
    return success_response(recipe.simple_serialize())

#list of saved ingredients
@app.route("/api/ingredients/")
def get_ingredients():
    ingredients = [i.simple_serialize() for i in Ingredient.query.all()]
    return success_response(ingredients)

#function to add an ingredient to ingredients tab
@app.route("/api/ingredients/", methods=["POST"])
def create_ingredient():
    body = json.loads(request.data)
    name = body.get('name')
    amount = body.get('amount')
    
    if amount is None or name is None:
        return failure_response("Incorrect input!")
    new_ingredient = Ingredient(
        name=name,
        amount=amount
    )
    db.session.add(new_ingredient)
    db.session.commit()
    return success_response(new_ingredient.simple_serialize(), 201)

#function to delete ingredients from ingredients tab
@app.route("/api/ingredients/<int:ingredient_id>/", methods=["DELETE"])
def delete_ingredient(ingredient_id):
    ingredient = Ingredient.query.filter_by(id=ingredient_id).first()
    if ingredient is None:
        return failure_response("Ingredient not found!")
    db.session.delete(ingredient)
    db.session.commit()
    return success_response(ingredient.serialize())

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
