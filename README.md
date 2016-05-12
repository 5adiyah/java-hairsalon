# Hair Salon Ap
By: Sadio Al

## Technologies Used

jUnit
Fluentleniu
Veloctiy
Java
HTML
CSS

## Setting Up Project

Clone this repository:

```
$ git clone https://github.com/5adiyah/java-hairsalon.git
$ cd java-hairsalon
```

Open terminal and run Postgres, then in a new tab run psql
```
$ postgres
$ psql
```

Create `hair_salon` by following the steps below:
```
$ CREATE DATABASE hair_salon;
$ \c hair_salon;
$ CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, phone varchar);
$ CREATE TABLE clients (id serial PRIMARY KEY, name varchar, phone varchar, stylist_id int);
$ CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
```

Navigate back to the directory where this repository has been cloned, then run gradle:
```
$ gradle run
```

## Legal

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
