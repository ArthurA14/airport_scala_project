package main

import caseClassCsv.Airport
import caseClassCsv.Country
import caseClassCsv.Runway

object Main {
  //Creating immutable lists for each of the three data csv files
  val airportNameList = parseCsv.ParseCSV.fromCsvToList("airport").asInstanceOf[List[Airport]];
  val countryNameList = parseCsv.ParseCSV.fromCsvToList("country").asInstanceOf[List[Country]];
  val runwayNameList = parseCsv.ParseCSV.fromCsvToList("runway").asInstanceOf[List[Runway]];
  
  def main(args: Array[String]): Unit = {
    //The application is running
    println("...In progress...\n");
    println("The application has correctly launched.\n");

    //Query option function
    def queryOptionFunc(): Unit = {
      println("You have selected the query option.\n");
      println("Type a country code or a country name :\n");

      val countryNameOrCode = models.Models.countryNameShift(scala.io.StdIn.readLine(Console.BLUE + "ScalaAirport> " + Console.RESET));
      println("");

      (countryNameOrCode, countryNameOrCode.length) match {
        case ("quit"|"Quit", 4) => println("You have correctly left the query option command.\n");

        case (_, 2) =>  caseClassCsv.CaseClassCsv.countryAirports(countryNameOrCode, airportNameList, runwayNameList);
                        queryOptionFunc;

        case (_, _) =>  val countryCode = models.Models.countryCodeFunction(countryNameOrCode, countryNameList, 10); //Example: 'AQ' is 'Antarctica'
                        caseClassCsv.CaseClassCsv.countryAirports(countryCode, airportNameList, runwayNameList);
                        queryOptionFunc;
      }
    }

    //Report option function
    def reportOptionFunc(): Unit = {
      println("You have selected the report option.\n");
      println("Select a report to display among these options :\n");
      println("Choose" + Console.GREEN + "1" + Console.RESET + ": the top 10 countries with hightest number of airports.");
      println("Choose" + Console.GREEN + "2" + Console.RESET + ": the top 10 countries with lowest number of airports.");
      println("Choose" + Console.GREEN + "3" + Console.RESET + ": type of runways per country.");
      println("Choose" + Console.GREEN + "4" + Console.RESET + ": the top 10 most common runway latitudes.\n");
      val reportValue = scala.io.StdIn.readLine(Console.BLUE+ "ScalaAirport> "+ Console.RESET);
      println("");

      reportValue match {
        case ("1"|"2") => val airportIsoCountryVal = caseClassCsv.CaseClassCsv.airportIsoCountryFunc(airportNameList)
                            .groupBy(identity)
                              .mapValues(_.size)
                                .toList.map{case (k, v) => (v, k)}
                                  .sorted;
                          reportValue match {
                            case ("1") => println("Top 10 countries with highest number of airports are:\n");
                                                models.Models.countryAirportsNumber(airportIsoCountryVal.reverse.splitAt(10)._1);
                            case ("2") => println("Top 10 countries with lowest number of airports are:\n");
                                                models.Models.countryAirportsNumber(airportIsoCountryVal.splitAt(10)._1);
                          };
                          reportOptionFunc;

        case ("3") => val list1 = caseClassCsv.CaseClassCsv.runwaySurfaceFunc(runwayNameList);
                      val list2 = caseClassCsv.CaseClassCsv.airportIdAndIsocountryFunc(airportNameList);
                      val list3 = list1.flatMap(x => list2.map(y => (x,y)))
                        .filter{case ((_,y),(i,_)) => y == i}
                          .map {case ((x, _),(_,j)) => (x,j)};
                      reportOptionFunc;

        case ("4") => println("Top 10 most commom runway latitudes are:\n");
                      models.Models.commomRunwayLatitudes;
                      reportOptionFunc;

        case ("quit"|"Quit") => println("You have correctly left the report option command.\n");

        case (_) => reportOptionFunc;
      }
    }

    //choice option parser function
    def choiceOptionParser(): Unit = {
      if (args.length != 1)
        println("Type query or report option.\n");
      else {
        val choiceOption = args(0);
        choiceOption match {
          case ("-query")  => queryOptionFunc;
          case ("-report") => reportOptionFunc;
          case (_) => println("Type -query or -report option.\n");
        }
      }
    }

    //Launching choice option parser function
    choiceOptionParser;

    //Closing the buffer
    parseCsv.ParseCSV.buffersClosing();
  }
}
