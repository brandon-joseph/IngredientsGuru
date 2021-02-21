from flask_sqlalchemy import SQLAlchemy
db = SQLAlchemy()

# student_asso = db.Table("student_asso", db.Model.metadata,
#     db.Column("course_id", db.Integer, db.ForeignKey("course.id")),
#     db.Column("user_id", db.Integer, db.ForeignKey("user.id")),
# )

# instructor_asso = db.Table("instructor_asso", db.Model.metadata,
#     db.Column("course_id", db.Integer, db.ForeignKey("course.id")),
#     db.Column("user_id", db.Integer, db.ForeignKey("user.id")),
# )

association_table = db.Table("association", db.Model.metadata,
    db.Column("ingredient_id", db.Integer, db.ForeignKey("ingredient.id")),
    db.Column("recipe_id", db.Integer, db.ForeignKey("recipe.id")),
)

# your classes here

class Ingredient(db.Model):
    __tablename__ = "ingredient"
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String, nullable=False)
    amount = db.Column(db.Integer, nullable=False)
    recipes = db.relationship("Recipe", secondary=association_table, back_populates="ingredients")

    def __init__ (self, **kwargs):
        self.name = kwargs.get("name", "")
        self.amount = kwargs.get("amount", "")

    def serialize(self):
        return {
            "id": self.id,
            "name": self.name,
            "amount": self.amount,
            "recipes": [r.simple_serialize() for r in self.recipes]
        }

    def simple_serialize(self):
        return {
            "id": self.id,
            "name": self.name,
            "amount": self.amount
        }

class Recipe(db.Model):
    __tablename__ = "recipe"
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String, nullable=False)
    #defining the reverse side of the many-to-many relationship, connecting to the same join table
    ingredients = db.relationship("Ingredient", secondary=association_table, back_populates="recipes")

    def __init__ (self, **kwargs):
        self.name=kwargs.get("name", "")
     
    def serialize(self):
        return {
            "id": self.id,
            "name": self.name,
            "ingredients": [i.simple_serialize() for i in self.ingredients]
        }

    def simple_serialize(self):
        return {
            "id": self.id,
            "name": self.name
        }

# class Course(db.Model):
#     __tablename__ = "course"
#     id = db.Column(db.Integer, primary_key=True)
#     code = db.Column(db.String, nullable=False)
#     name = db.Column(db.String, nullable=False)
#     # defining the reverse side of the relationship
#     assignments = db.relationship("Assignment", cascade="delete")
#     # defining the reverse side of the many-to-many relationship, connecting to the same join table
#     instructors = db.relationship("User", secondary=instructor_asso, back_populates="instructor_courses")
#     students = db.relationship("User", secondary=student_asso, back_populates="student_courses")


#     def __init__ (self, **kwargs):
#         self.code = kwargs.get("code", "")
#         self.name = kwargs.get("name", "")

#     def serialize(self):
#         return {
#             "id": self.id,
#             "code": self.code,
#             "name": self.name,
#             # serialize each assignment related to this course
#             "assignments": [a.simple_serialize() for a in self.assignments],
#             # serialize each category related to this task
#              "instructors": [i.simple_serialize() for i in self.instructors],
#              "students": [s.simple_serialize() for s in self.students]
#         }

#     def simple_serialize(self):
#         return {
#             "id": self.id,
#             "code": self.code,
#             "name": self.name
#         }

# class User(db.Model):
#     __tablename__ = "user"
#     id = db.Column(db.Integer, primary_key=True)
#     name = db.Column(db.String, nullable=False)
#     netid = db.Column(db.String, nullable=False)
#     typ = db.Column(db.String, nullable=False)
#     # define many-to-many user by connecting to association table
#     instructor_courses = db.relationship("Course", secondary=instructor_asso, back_populates='instructors')
#     student_courses = db.relationship("Course", secondary=student_asso, back_populates='students')

#     def __init__(self, **kwargs):
#         self.name = kwargs.get("name", "")
#         self.netid = kwargs.get("netid", "")
#         self.typ = kwargs.get("typ", "")

#     def serialize(self):
#         return {
#             "id": self.id,
#             "name": self.name,
#             "netid": self.netid,
#             "courses": [i.serialize() for i in self.instructor_courses] + [s.serialize() for s in self.student_courses]
#         }

#     def simple_serialize(self):
#         return {
#             "id": self.id,
#             "name": self.name,
#             "netid": self.netid
#         }


# class Assignment(db.Model):
#     __tablename__ = "assignment"
#     id = db.Column(db.Integer, primary_key=True)
#     title = db.Column(db.String, nullable=False)
#     due_date = db.Column(db.Integer, nullable=False)
#     course_id = db.Column(db.Integer, db.ForeignKey("course.id"), nullable=False)
#     course = db.Column

#     def __init__(self, **kwargs):
#         self.title = kwargs.get("title", "")
#         self.due_date = kwargs.get("due_date", "")
#         self.course_id = kwargs.get("course_id")
#         self.course = kwargs.get("course")

#     def serialize(self):
#         return {
#             "id": self.id,
#             "title": self.title,
#             "due_date": self.due_date,
#             "course": {
#                 "id": self.course.id,
#                 "code": self.course.code,
#                 "name": self.course.name
#             }
#         }

#     def simple_serialize(self):
#         return {
#             "id": self.id,
#             "title": self.title,
#             "due_date": self.due_date
#         }


