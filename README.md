# Music REST service

This is a simple REST service that uses Spring Boot.  After using my itunes-analyzer
to create a JSON file representing my music library, I created this REST service
to populate a Postgreql database with the library.  This REST service also provides
an API to change the status of each track.  As I re-rip my CD's, my intranet uses
this REST service to update the tracks' status.

## Installation

This is a Maven project, so assuming you have Maven installed, you can
just clone the project to your machine and run 

mvn install

## Usage

I run this as a standalone jar.  You need to specify the username and password
for your database as properties on the command line.

## License

Licensed under the MIT license.
