package parseCsv

object ParseCSV {
    println("id,ident,type,name,latitude_deg,longitude_deg,elevation_ft,continent,iso_country,iso_region,municipality,scheduled_service,gps_code,iata_code,local_code,home_link,wikipedia_link,keywords")
    
     // CSV file path
    val csvFilePath = "./src/main/csvfiles/"

    // Open the files
    val airportsBufferedSource = io.Source.fromFile(csvFilePath + "airports.csv")
    val countriesBufferedSource = io.Source.fromFile(csvFilePath + "countries.csv")
    val runwaysBufferedSource = io.Source.fromFile(csvFilePath + "runways.csv")

    // Get data on List of String | Constructing case class later
    val airportsLines = airportsBufferedSource.getLines.toList
    val countriesLines = countriesBufferedSource.getLines.toList
    val runwaysLines = runwaysBufferedSource.getLines.toList
    
    // Function that fill list with country/airport/runway
    def fromCsvToList(categories: String): List[Any] = {
      def csvToList(rows: List[String], acc: List[Any] = Nil): List[Any] = rows match {
        case (Nil) => acc
        case (x::tail) => val anyObject = caseClassCsv.CaseClassCsv.sortColumns(categories, x);
                          csvToList(tail, anyObject::acc)
      }
      categories match {
        case ("airport") => csvToList(parseCsv.ParseCSV.airportsLines)
        case ("country") => csvToList(parseCsv.ParseCSV.countriesLines)
        case ("runway")  => csvToList(parseCsv.ParseCSV.runwaysLines)
        case (_)         => Nil
      }
    }

    def buffersClosing(): Unit = {
        // Closing our buffer
        airportsBufferedSource.close
        countriesBufferedSource.close
        runwaysBufferedSource.close
    }
}
