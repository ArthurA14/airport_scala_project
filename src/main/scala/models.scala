package models

import caseClassCsv.Airport
import caseClassCsv.Country
import caseClassCsv.Runway

object Models {
    //This function changes the break of a country name or a country code
    def countryNameShift(countryName: String): String = countryName.length match {
      case (2) => countryName.toUpperCase; //Example: 'the' becomes 'THE'
      case (_) => countryName.toLowerCase.capitalize; //Example: 'dEUtschland' becomes 'Deutschland'
    }

    //This function returns the associated country code given a country name
    def countryCodeFunction(countryName: String, countriesList: List[Country], numberOfSplit: Int = 15): String = {
        def countryCodePulled(countriesList: List[Country], numberOfSplit: Int): String = countriesList match {
            case (x::tail) if x.name.splitAt(numberOfSplit)._1 == countryName => x.code;
            case (x::tail) => countryCodePulled(tail, numberOfSplit);
            case (Nil) => "ZZ";
        }
        
        val countryCode = countryCodePulled(countriesList, numberOfSplit);

        (countryCode, numberOfSplit == 2) match {
            case ("ZZ", false) => countryCodeFunction(countryName, countriesList, numberOfSplit - 1);
            case (_, _) => countryCode;
        }
    }

    //This function prints the number of airports in a given country
    def countryAirportsNumber(list: List[(Int, String)]): Unit = list match {
      case (x::tail) => println("Number of airports in " + Console.GREEN + x._2 + Console.RESET + " = " + Console.GREEN + x._1 + Console.RESET);
                        countryAirportsNumber(tail);
      case (Nil) => println("");
    }

    //This function retrives all runways latitude in order to know its occurences
    def runwayLatitudeList(runwaysList: List[Runway], acc: List[String] = Nil) : List[String] = runwaysList match {
      case (x::tail) => runwayLatitudeList(tail, x.le_ident::acc);
      case (Nil) => acc;
    }

    //This function calculates the top 10 most commom runway latitudes
    def commomRunwayLatitudes(): Unit = {
      val occurencesList = runwayLatitudeList(main.Main.runwayNameList).groupBy(identity)
                                                                          .mapValues(_.size)
                                                                            .toList.map{ case (k, v) => (v, k) }
                                                                              .sorted.reverse
                                                                                .splitAt(10)._1;

      def commomRunwayLatitudesPulled(occurencesList: List[(Int, String)]): Unit = occurencesList match {
        case (x::tail) => println(Console.GREEN + x._2 + Console.RESET + " has " + Console.GREEN + x._1 + Console.RESET + " hits.");
                          commomRunwayLatitudesPulled(tail);
        case (Nil) => println("");
      }
      commomRunwayLatitudesPulled(occurencesList);
    }
}