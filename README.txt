-First of all this is my very first real Android app. 
  Despite all the problems I am very proud of what I could achieve in a very short period of time.
  I hope you consider It when assesssing the project.

-After some review I realized that the API method, to get the upcoming movies, has some strange results, bringing movies with a past release date. This put me in doubt if I would keep calling the current method or to make a custom request. Since it`s a MVP product I decided to make it simple and keep calling a native method.

 But if need I could`ve made a request like this;

	https://api.themoviedb.org/3/discover/movie?api_key=<<API_key>>&language=en-US&primary_release_date.gte=<<now date convert to String YYYY-MM-DD>> 

-Tested with a Xiaomi Mi A1 using a custom Android 8.1 ROM

-Third party libraries used:
  -okhttp: to make API requests and then retrieve a JSON
  -GSon: to easily parse a JSON to a list of movies objects
  -Picasso: to easily load and cache the movies images shown

