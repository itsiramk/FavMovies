# MoviesApp

This project is a MoviesApp to display list of top rated movies using the concept of Pagination in Android.

Pagination is one of the key concepts in Android API communication and is widely used to display data for multiple pages within an api.

In this app, there are 2 screens

Screen 1:

It is a tab view with two tabs.

Tab1:

Displays top-rated movies with pagination loading infinite movies in a recyclerview with a like button. When you click on the like button, the data of the respective record is saved in the database.

Tab2: Displays items marked as favourite in a gridview.

Screen 2: When one clickes on an item in Tab1, it details gets displayed on Screen2.

Details:

Architecture : MVVM

Api Communication : http://api.themoviedb.org

Networking Libarary : Retrofit

Database : Room Db

Pagination : Android Pagination Libarary

Image Rendering : Picasso

Material Design : Recyclerview

Animation : Shimmer Animation used
