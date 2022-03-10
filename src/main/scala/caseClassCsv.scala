package caseClassCsv

//Classes declaration
case class Airport(id: String, 
                  ident: String, 
                  types: String, 
                  name: String, 
                  latitude_deg: String, 
                  longitude_deg: String, 
                  elevation_ft: String, 
                  continent: String, 
                  iso_country: String, 
                  iso_region: String, 
                  municipality: String, 
                  scheduled_service: String, 
                  gps_code: String, 
                  iata_code: String, 
                  local_code: String, 
                  home_link: String, 
                  wikipedia_link: String, 
                  keywords: String);

case class Country(id: String, 
                  code: String, 
                  name: String, 
                  continent: String, 
                  wikipedia_link: String, 
                  keywords: String);

case class Runway(id: String, 
                  airport_ref: String, 
                  airport_ident: String, 
                  length_ft: String, 
                  width_ft: String, 
                  surface: String, 
                  lighted: String, 
                  closed: String, 
                  le_ident: String, 
                  le_latitude_deg: String, 
                  le_longitude_deg: String, 
                  le_elevation_ft: String, 
                  le_heading_degT: String, 
                  le_displaced_threshold_ft: String, 
                  he_ident: String, 
                  he_latitude_deg: String, 
                  he_longitude_deg: String, 
                  he_elevation_ft: String, 
                  he_heading_degT: String, 
                  he_displaced_threshold_ft: String);

object CaseClassCsv {
    //This function returns an airport, a country or a runway row, giving a csv file on input line
    def sortColumns(kindOfObject: String, row: String) = {
      val column = (row + " ").split(",");

      kindOfObject match {
        case ("airport") => Airport(column(0), 
                                    column(1), 
                                    column(2), 
                                    column(3), 
                                    column(4), 
                                    column(5), 
                                    column(6), 
                                    column(7), 
                                    column(8), 
                                    column(9), 
                                    column(10), 
                                    column(11), 
                                    column(12), 
                                    column(13), 
                                    column(14), 
                                    column(15), 
                                    column(16), 
                                    column(17));

        case ("country") => Country(column(0), 
                                    column(1), 
                                    column(2), 
                                    column(3), 
                                    column(4), 
                                    column(5));

        case ("runway") => Runway(column(0), 
                                  column(1), 
                                  column(2), 
                                  column(3), 
                                  column(4), 
                                  column(5), 
                                  column(6), 
                                  column(7), 
                                  column(8), 
                                  column(9), 
                                  column(10), 
                                  column(11), 
                                  column(12), 
                                  column(13), 
                                  column(14), 
                                  column(15), 
                                  column(16), 
                                  column(17), 
                                  column(18), 
                                  column(19));

        case (_) => Nil;
      }
    }

    //This function prints runways belonging to a given airport
    def airportRunways(airport_ident: String, rows: List[Runway], acc: Int = 0): Unit = rows match {
        case (x::tail) if x.airport_ident == airport_ident => println("\t " + x.id + " ; " 
                                                                        + x.airport_ref
                                                                          + " ; " + x.airport_ident
                                                                            + " ; " + x.length_ft
                                                                              + " ; " + x.width_ft
                                                                                + " ; " + x.surface);
                                                              airportRunways(airport_ident, tail, acc + 1);

        case (x::tail) => airportRunways(airport_ident, tail, acc);

        case (Nil) if acc == 0 => println("\t No runway on this airport.\n");

        case (Nil) => println("");
    }

    //This function prints airports belonging to a given country
    def countryAirports(countryName: String, airportNameList: List[Airport], runwayNameList: List[Runway]): Unit = {
      def countryAirportsList(airportlist: List[Airport], numberOfAirports: Int): Unit = airportlist match {
        case (x::tail) if x.iso_country == ("\"" + countryName + "\"") => println(x.name);
                                                                          print(Console.GREEN);
                                                                          airportRunways(x.ident, runwayNameList); //This intruction prints all the runways
                                                                          print(Console.RESET);                                                                   
                                                                          countryAirportsList(tail, numberOfAirports + 1);

        case (x::tail) => countryAirportsList(tail, numberOfAirports);

        case (Nil) => println(numberOfAirports + " airports in this country : " + Console.GREEN + countryName + Console.RESET + ".\n");
      }

      if (countryName == "ZZ")
        println(Console.GREEN + "Problem" + Console.RESET + ": We don't know this country name.\n");
      else
        countryAirportsList(airportNameList, 0);
    }

    //This function returns the list which contains the iso country code associated with each airport
    def airportIsoCountryFunc(airportNameList: List[Airport], acc: List[String] = Nil) : List[String] = airportNameList match {
      case (x::tail) => airportIsoCountryFunc(tail, x.iso_country::acc);
      case (Nil) => acc;
    }

    //This function returns the list which contains the airport id and the iso country associated with each airport
    def airportIdAndIsocountryFunc(airportNameList: List[Airport], acc: List[(String, String)] = Nil) : List[(String, String)] = airportNameList match {
      case (x::tail) => airportIdAndIsocountryFunc(tail, acc:+(x.ident, x.iso_country));
      case (Nil) => acc;
    }
        
    //This function returns the list which contains the surface associated with each runway of an airport
    def runwaySurfaceFunc(runwayNameList: List[Runway], acc: List[(String, String)] = Nil): List[(String, String)] = runwayNameList match {
      case (x::tail) => runwaySurfaceFunc(tail, acc:+(x.airport_ident, x.surface));
      case (Nil) => acc;
    }
}

