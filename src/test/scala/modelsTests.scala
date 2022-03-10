import collection.mutable.Stack
import org.scalatest._

class ModelsClassTest extends FlatSpec with Matchers
{
    "A country name" should "be shaped" in {
        val name1 = models.Models.countryNameShift("BaHAmaS")
        name1 should be ("Bahamas")
        val name2 = models.Models.countryNameShift("ausTRIA")
        name2 should be ("Austria")
        val name3 = models.Models.countryNameShift("fInLaNd")
        name3 should be ("Finland")
        val name4 = models.Models.countryNameShift("")
        name4 should be ("")
    }

    "A country code" should "be shaped" in {
        val name1 = models.Models.countryNameShift("fr")
        name1 should be ("FR")
        val name2 = models.Models.countryNameShift("gB")
        name2 should be ("GB")
        val name3 = models.Models.countryNameShift("Nl")
        name3 should be ("NL")
        val name4 = models.Models.countryNameShift("")
        name4 should be ("")
    }

    "A truncated country name" should "be recognized" in {
        val name1 = models.Models.countryCodeFunction("Nicarag", main.Main.countryNameList)
        name1 should be ("NI")
        val name2 = models.Models.countryCodeFunction("Nica", main.Main.countryNameList)
        name2 should be ("NI")
        val name3 = models.Models.countryCodeFunction("Nic", main.Main.countryNameList)
        name3 should be ("NI")
        val name4 = models.Models.countryCodeFunction("", main.Main.countryNameList)
        name4 should be ("ZZ")
    }

    it should "should be recognized and correctly shaped" in {
        val name1 = models.Models.countryCodeFunction(models.Models.countryNameShift("FRanc"), main.Main.countryNameList)
        name1 should be ("FR")
        val name2 = models.Models.countryCodeFunction(models.Models.countryNameShift("fRaN"), main.Main.countryNameList)
        name2 should be ("FR")
        val name3 = models.Models.countryCodeFunction(models.Models.countryNameShift("FRA"), main.Main.countryNameList)
        name3 should be ("FR")
        val name4 = models.Models.countryCodeFunction("", main.Main.countryNameList)
        name4 should be ("ZZ")
    }

    "An input country name" should "return its country code" in {
        val name1 = models.Models.countryCodeFunction("France", main.Main.countryNameList)
        name1 should be ("FR")
        val name2 = models.Models.countryCodeFunction("Italy", main.Main.countryNameList)
        name2 should be ("IT")
        val name3 = models.Models.countryCodeFunction("Japan", main.Main.countryNameList)
        name3 should be ("JP")
        val name4 = models.Models.countryCodeFunction("lol", main.Main.countryNameList)
        name4 should be ("ZZ")
        val name5 = models.Models.countryCodeFunction("", main.Main.countryNameList)
        name5 should be ("ZZ")
    }    
}