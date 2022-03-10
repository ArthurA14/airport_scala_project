import collection.mutable.Stack
import org.scalatest._

class CSVClassTest extends FlatSpec with Matchers {
    //"6523","00A","heliport","Total Rf Heliport","40.07080078125","-74.93360137939453","11","NA","US","US-PA","Bensalem","no","00A","","00A","",""," "
    "An airport " should "be created" in {

        val line1 = "39730,CA-0084,closed,Casey Airport,47.93333435058594,-74.0999984741211,,NA,CA,CA-QC,,no,,,,,,"
        val airportObjet1 = caseClassCsv.Airport("39730","CA-0084","closed","Casey Airport","47.93333435058594","-74.0999984741211","","NA","CA","CA-QC","","no","","","","",""," ")
        val anyObject1 = caseClassCsv.CaseClassCsv.sortColumns("airport", line1);
        anyObject1 should be (airportObjet1)

        val line2 = "6527,00AZ,small_airport,Cordes Airport,34.305599212646484,-112.16500091552734,3810,NA,US,US-AZ,Cordes,no,00AZ,,00AZ,,,"
        val airportObjet2 = caseClassCsv.Airport("6527","00AZ","small_airport","Cordes Airport","34.305599212646484","-112.16500091552734","3810","NA","US","US-AZ","Cordes","no","00AZ","","00AZ","",""," ")
        val anyObject2 = caseClassCsv.CaseClassCsv.sortColumns("airport", line2);
        anyObject2 should be (airportObjet2)
    }

    "A country " should "be created" in {

        val line1 = "302711,PT,Portugal,EU,http://en.wikipedia.org/wiki/Portugal,"
        val countryObjet1 = caseClassCsv.Country("302711","PT","Portugal","EU","http://en.wikipedia.org/wiki/Portugal", " ")
        val anyObject1 = caseClassCsv.CaseClassCsv.sortColumns("country", line1);
        anyObject1 should be (countryObjet1)

        val line2 = "302717,SK,Slovakia,EU,http://en.wikipedia.org/wiki/Slovakia,"
        val countryObjet2 = caseClassCsv.Country("302717","SK","Slovakia","EU","http://en.wikipedia.org/wiki/Slovakia"," ")
        val anyObject2 = caseClassCsv.CaseClassCsv.sortColumns("country", line2);
        anyObject2 should be (countryObjet2)
    }

    "A runway " should "be created" in {

        val line1 = "243556,20043,KHEI,1890,100,GRS,0,0,17,,,,179.4,,35,,,,359.4,"
        val runwayObjet1 = caseClassCsv.Runway("243556","20043","KHEI","1890","100","GRS","0","0","17","","","","179.4","","35","","","","359.4"," ")
        val anyObject1 = caseClassCsv.CaseClassCsv.sortColumns("runway", line1);
        anyObject1 should be (runwayObjet1)

        val line2 = "246601,3576,KHFD,2309,150,TURF,0,0,NE,,,,,,SW,,,,,"
        val runwayObjet2 = caseClassCsv.Runway("246601","3576","KHFD","2309","150","TURF","0","0","NE","","","","","","SW","","","",""," ")
        val anyObject2 = caseClassCsv.CaseClassCsv.sortColumns("runway", line2);
        anyObject2 should be (runwayObjet2)
    }
}
