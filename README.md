# 11.4-recordstore
TIY Week 11, Day 4: Record store app

#### Description  
Create a web application to allow you to add bands to a band repository and to add albums to those bands. You should be able to browse through all of the bands and search for bands by a text input. You should also be able to browse through all albums in the store but also be able to search for an album by a text input.

#### Objectives  
Implement the ability to perform the following actions on your models in the application.
Create
Read / List / Detail
Edit
Delete
Implement a "search" functionality for bands and albums. These can be separate searches.
Understand how to integrate JPA Annotations onto models into your Spring MVC application.
Normal Mode  
You are tasked to create a web application where a user can search for and input bands and their albums. Your model structure should be flexible enough to allow a band to have as many albums as they want. It is up to you to design your model structure. Keep in mind how you will store things like "genre" and "track lists" and "release date". Those are up to you to be as detailed or general as possible.

The search functionality is going to be a challenge. How will you handle the form submission for a search action? What will you do with the results? Where will you land after you search?

#### Requirements  
A minimum of 2 models to handle the Album / Band relationship. You will likely have to have more models, but this is the absolute minimum
Search for a band / album
Allow a user to add a band
Allow a user to add an album
