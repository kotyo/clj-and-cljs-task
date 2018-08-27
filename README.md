# clj-and-cljs-task

An application that can decide if two strings can be scrambled.
Consist of 2 parts.
- backend: A webservice that can process http requests.
- frontend: A web UI which can communicate with the backend.

## Usage

You may need 2 terminal in the root of the project
On the first terminal: 
```
cd backend
lein run
```
On the second terminal:
```
cd frontend
lein figwheel
```
A browser should open, and you can test the application.
