class Developer(var ime:String, var prezime:String, var drzava:String, var listaApp:Array<Data>){}
fun racunanjeProsjeka(nizapp:Array<Data>):Double{
    var zbirOcj:Double=1.0
    var brojOcj:Int=0
    for(app in nizapp){
        zbirOcj += app.prosOcj
        ++brojOcj
       }
    val proOcj:Double = zbirOcj/brojOcj
    return proOcj
}

fun developerLeaderboard(niz:Array<Developer>, k:Int){
    val sortiraniNiz = niz.sortedWith { a, b -> 
    		var abroj:Int = 0
			for (app in a.listaApp) abroj += app.brojPreuz
			var bbroj:Int = 0
			for (app in b.listaApp) bbroj += app.brojPreuz
            if (abroj == bbroj) { 
            	racunanjeProsjeka(a.listaApp).compareTo(racunanjeProsjeka(b.listaApp))
            }
    		else bbroj.compareTo(abroj)
		}
		var i=0
        for(dev in sortiraniNiz){
            var brojP=0
            var zbirOcj:Double=0.0
            var brojOcj:Int=0
            for(app in dev.listaApp){
                brojP += app.brojPreuz
                zbirOcj += app.prosOcj
                ++brojOcj
            }
            val proOcj:Double=zbirOcj/brojOcj
            i++
            println("${dev.ime} ${dev.prezime} (${dev.drzava}) - $brojP preuz. - prosj. ocjena: $proOcj")
            if(i==k) {
                break
            }
        }   	
}

class Data(var naziv:String, var kategorija:String, var brojPreuz:Int, var prosOcj:Double, var velicina:Double){    
    init {
        require(brojPreuz > 0) { "Broj preuzimanja mora biti pozitivan broj." }
        require(prosOcj > 0) { "Prosjeca ocjena mora biti pozitivan broj." }
        require(velicina > 0) { "Velicina mora biti pozitivan broj." }
        println("Incijalizacija App: $naziv | Category: $kategorija | Rating: $prosOcj | Downloads: $brojPreuz | Size: $velicina")
    }
    fun print(){
        println("App: $naziv | Category: $kategorija | Rating: $prosOcj | Downloads: $brojPreuz | Size: $velicina")
    }
}
	
    fun findByName(niz:Array<Data>, naziv1: String){
        for(app in niz){
            if(app.naziv==naziv1){
                app.print()
            }
        }
    }
    fun findByNameFunk(niz:Array<Data>, ime:String){
        val result= niz.find { it.naziv==ime }
        if (result!=null) result.print()
    }
    
 
    fun filterByGrade(niz:Array<Data>, poredbenaOcjena:Double){
        val filtriraniNiz = niz.filter{ it.prosOcj >= poredbenaOcjena }
        for(app in filtriraniNiz){
            app.print()
        }
    }
    fun sortiranjeOpadajuceFunk (niz:Array<Data>){
        val sortiraniNiz : List<Data> = niz.sortedByDescending() { it.brojPreuz }
        for(app in sortiraniNiz){
            app.print()
        }
    }
    fun sortiranjeOpadajuce (niz:Array<Data>){
		val sortiraniNiz = niz.sortedWith { a, b -> 
    		if (a.brojPreuz == b.brojPreuz) a.naziv.compareTo(b.naziv)
    		else b.brojPreuz.compareTo(a.brojPreuz)
		}

        for(app in sortiraniNiz){
            app.print()
        }
    }
    fun groupByCatFunk(niz:Array<Data>){
        var mapa = niz.groupingBy { it.kategorija }.eachCount()
        println("Broj aplikacija po kategorijama: $mapa")
    }
    fun groupByCat(niz:Array<Data>){
        val mapa: MutableMap<String, Int> = mutableMapOf()
        for(app in niz){
            val imeKat=app.kategorija
			val trenutna = mapa[imeKat] ?: 0 
    		mapa[imeKat] = trenutna + 1
        }
        println("Broj aplikacija po kategorijama: $mapa")
    }




fun main() {
    var s1: Array<Data> = arrayOf(
    Data("Clash Royale", "Igrica", 90000, 5.0, 80.6),
    Data("Clash of Clans", "Igrica", 1302000, 5.0, 80.6),
    Data("Brawl Stars", "Igrica", 1530000, 5.0, 80.6),
    Data("Candy Crush Saga", "Igrica", 4500000, 4.5, 75.2),
    Data("Subway Surfers", "Igrica", 2100000, 4.3, 70.1),
    Data("Angry Birds 2", "Igrica", 850000, 4.0, 65.5),
    Data("Spotify", "Muzika", 6000000, 4.8, 92.1),
    Data("Netflix", "Video", 7200000, 4.9, 95.0),
    Data("Instagram", "Društvena mreža", 12000000, 4.6, 88.9),
    Data("WhatsApp", "Komunikacija", 15000000, 4.7, 98.5),
    Data("Google Maps", "Navigacija", 10000000, 4.4, 90.3),
    Data("TikTok", "Video", 9500000, 4.5, 85.7),
    Data("Microsoft Word", "Produktivnost", 3500000, 4.2, 78.4)
)
    val SkupinaDevelopera: Array<Developer> = arrayOf(
    	Developer("Ana", "Kovac", "HR", arrayOf(
    		Data("Clash Royale", "Igrica", 90000, 5.0, 80.6),
    		Data("Clash of Clans", "Igrica", 1302000, 5.0, 80.6),
    		Data("Brawl Stars", "Igrica", 1530000, 5.0, 80.6)
    	)),
        Developer("Marko", "Juric", "BA", arrayOf(
    		Data("Candy Crush Saga", "Igrica", 4500000, 4.5, 75.2),
    		Data("Subway Surfers", "Igrica", 2100000, 4.3, 70.1),
    		Data("Angry Birds 2", "Igrica", 850000, 4.0, 65.5)
    	)),
        Developer("Luca", "Bianchi", "IT", arrayOf(
    		Data("Spotify", "Muzika", 6000000, 4.8, 92.1),
    		Data("Netflix", "Video", 7200000, 4.9, 95.0),
    		Data("Instagram", "Društvena mreža", 12000000, 4.6, 88.9)
    	)))
        
    println()
    findByName(s1,"Brawl Stars")
    findByNameFunk(s1,"Brawl Stars")
    println()
    filterByGrade(s1,4.5)
    println()
    sortiranjeOpadajuceFunk(s1)
    println()
    sortiranjeOpadajuce(s1)
    println()
    groupByCat(s1)
    
    developerLeaderboard(SkupinaDevelopera, 2)
}
