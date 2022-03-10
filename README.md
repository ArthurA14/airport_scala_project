#SCALA PROJECT
___
Abstract
------

This project is about parsing, querying and reporting on large data .csv files with Scala language.

This is a command line program that will ask the user for two options - Query or Reports. 

1) Query Option will ask the user for the country name or code and display the airports & runways at each airport. The input can be country code or country name. 


2) Choosing Reports will display the following (possibly through a menu): 
* 10 countries with the highest number of airports (with count) and countries with the lowest number of airports. 
* Type of runways (as indicated in "surface" column in runways.csv) per country 
* The top 10 most common runway latitude (indicated in "le_ident" column in runways.csv) 



The folder contains:

* a *build.sbt* file
* a main folder containing
     * *.csv* files : airports.csv, countries.csv, runaways.csv
     * scala sources code files : main and packages (models, parseCsv, caseClassCsv)
* a test folder for testsuit project

This project is using a library for tests named *ScalaTest*. According to their words, ScalaTest is the most flexible and most popular testing tool in the Scala ecosystem.

___
Execution
---------

Firstly, launch the **sbt console**  (or from the terminal ) at the root of the project:

    > sbt  

To run all of the test source files (test units), enter *test* from the sbt console with:

    > test  (if terminal : > sbt test)

For the execution of the main program, just *run* at the root:

    > run   ( if terminal : > sbt run)

You will be asked for two options, -query or -report. Note that you can run the program by these following ways:

    > run -query
    > run -report




___
User Guide
----------

You can ask for all airports and its associated runways to a given country code or country name.

    > run -query
    > Bahamas | > BS       (63 airports)

 You can also give a fuzzy name (Bonus Task). For example:

    > bahaMaS | > Bs

You can give for example a shorter name (Bonus Task). For example:

    > baha | > bahama | > baham

And combine the two example behind:

    > BaHam | > baHAMA | > BAh

You can ask for a report about the top 10 countries with hightest number of airports.

    > run -report
    > 1

You can ask for a report about the top 10 countries with lowest number of airports.

    > run -report
    > 2

You can ask for a report about type of runways per country.

    > run -report
    > 3

You can ask for a report about the top 10 most common runway latitude.
    
    > run -report
    > 4
      
At any time of the program, you can finish your execution by entering *quit*.

    > quit | > Quit

___
Testing 
-------

There are some tests available on this project that we used along the developpment. You can launch our tests by entering the commande test at the root of the project:

    > test ( if terminal : > sbt test)

___
PAIR WORK :
-------

Partners: **RASOLOARIVONY Elysé** & **ALLIE Arthur**

EFREI School -  M1 Big Data & IA Project 2021/2022 - for Scala course. 



____
CONCLUSION 
----------
This project allowed us to understand with a little more depth the programming language: SCALA, especially the particularity and the use of functional programming.

During this project, the online materials and the courses were very beneficial. It allowed us to understand how to put small details into practice like case class, val, recursion, singleton ( and companion object), packages, test unit, structure of a project,... .

Thanks to the teacher and all kind of supports ( www.scala-lang.org, github, youtube, ...)