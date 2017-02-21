import scala.collection.mutable.ListBuffer
import scala.util.Random
def voteOut(options: Iterable[String]) : String = {
        val choices = options.mkString(", ").split(", ")
        if(choices.size == 1){
                return choices(0).split(':')(0)
        }else{
                var finalVal = choices(0).split(':')(1).toInt
                var finalName = choices(0).split(':')(0)
                var i = 0
                var flag = false
                var lowestIndex = 0
                var list = new ListBuffer[Int]()
                for(lowestVal <- choices){
                        if(i!=0){
                        val tempName = lowestVal.split(':')(0)
                        val tempVal = lowestVal.split(':')(1).toInt
                        if(finalVal == tempVal){
                                flag = true
                                list+=lowestIndex
                                list+=i
                        }
                        else if(finalVal > tempVal){
                                flag = false
                                lowestIndex = i
                                finalVal = tempVal
                                finalName = tempName
                        }
                }
                i = i + 1
                }
                if(flag){
                        var random = new Random().nextInt(Math.abs(list.size))
                        return choices(list(random)).split(':')(0)
                }else{
                return finalName
                }
        }
}

def findTotal(options: Iterable[String], contestant: String) : Int = {
        val choices = options.mkString
        if (choices.contains(contestant)){
                return 1
        }else{
                return 0
        }
}

def hateTotal(options: String, contestant: String) : Int = {
        if (options.contains(contestant)){
                return 1
        }else{
                return 0
        }
}


val tweets = sc.textFile("/user/pxp142030/project/tweets_20160416.txt")

val hillary = tweets.map(_.split('\t')).filter(line=>line(2).toLowerCase.matches(".*hillary.*|.*clinton.*")).map(c =>(c(1),("Hillary"+":"+c(7))))
val modifiedHillary = hillary.map(l=>(l._1,(l._2.replace("1","-1"))))
val modifiedHillary1 = modifiedHillary.map(l=>(l._1,(l._2.replace("0","-1"))))
val modifiedHillary = modifiedHillary1.map(l=>(l._1,(l._2.replace("2","0"))))
val modifiedHillary1 = modifiedHillary.map(l=>(l._1,(l._2.replace("3","1"))))
val modifiedHillary = modifiedHillary1.map(l=>(l._1,(l._2.replace("4","1"))))
val modifiedHillary1 = modifiedHillary.map(l=>(l._1,(l._2.replace("Hillary:",""))))
val modifiedHillary = modifiedHillary1.map(l=>(l._1,(l._2.toInt)))
val commonHillary = modifiedHillary.reduceByKey(_+_)
val finalHillary = commonHillary.map(line=>(line._1,("Hillary"+":"+line._2)))

val donald = tweets.map(_.split('\t')).filter(line=>line(2).toLowerCase.matches(".*donald.*|.*trump.*")).map(c =>(c(1),("Donald"+":"+c(7))))
val modifiedDonald = donald.map(l=>(l._1,(l._2.replace("1","-1"))))
val modifiedDonald1 = modifiedDonald.map(l=>(l._1,(l._2.replace("0","-1"))))
val modifiedDonald = modifiedDonald1.map(l=>(l._1,(l._2.replace("2","0"))))
val modifiedDonald1 = modifiedDonald.map(l=>(l._1,(l._2.replace("3","1"))))
val modifiedDonald = modifiedDonald1.map(l=>(l._1,(l._2.replace("4","1"))))
val modifiedDonald1 = modifiedDonald.map(l=>(l._1,(l._2.replace("Donald:",""))))
val modifiedDonald = modifiedDonald1.map(l=>(l._1,(l._2.toInt)))
val commonDonald = modifiedDonald.reduceByKey(_+_)
val finalDonald = commonDonald.map(line=>(line._1,("Donald"+":"+line._2)))

val bernie = tweets.map(_.split('\t')).filter(line=>line(2).toLowerCase.matches(".*bernie.*|.*sanders.*")).map(c =>(c(1),("Bernie"+":"+c(7))))
val modifiedBernie = bernie.map(l=>(l._1,(l._2.replace("1","-1"))))
val modifiedBernie1 = modifiedBernie.map(l=>(l._1,(l._2.replace("0","-1"))))
val modifiedBernie = modifiedBernie1.map(l=>(l._1,(l._2.replace("2","0"))))
val modifiedBernie1 = modifiedBernie.map(l=>(l._1,(l._2.replace("3","1"))))
val modifiedBernie = modifiedBernie1.map(l=>(l._1,(l._2.replace("4","1"))))
val modifiedBernie1 = modifiedBernie.map(l=>(l._1,(l._2.replace("Bernie:",""))))
val modifiedBernie = modifiedBernie1.map(l=>(l._1,(l._2.toInt)))
val commonBernie = modifiedBernie.reduceByKey(_+_)
val finalBernie = commonBernie.map(line=>(line._1,("Bernie"+":"+line._2)))

val ted = tweets.map(_.split('\t')).filter(line=>line(2).toLowerCase.matches(".*ted.*|.*cruz.*")).map(c =>(c(1),("Ted"+":"+c(7))))
val modifiedTed = ted.map(l=>(l._1,(l._2.replace("1","-1"))))
val modifiedTed1 = modifiedTed.map(l=>(l._1,(l._2.replace("0","-1"))))
val modifiedTed = modifiedTed1.map(l=>(l._1,(l._2.replace("2","0"))))
val modifiedTed1 = modifiedTed.map(l=>(l._1,(l._2.replace("3","1"))))
val modifiedTed = modifiedTed1.map(l=>(l._1,(l._2.replace("4","1"))))
val modifiedTed1 = modifiedTed.map(l=>(l._1,(l._2.replace("Ted:",""))))
val modifiedTed = modifiedTed1.map(l=>(l._1,(l._2.toInt)))
val commonTed = modifiedTed.reduceByKey(_+_)
val finalTed = commonTed.map(line=>(line._1,("Ted"+":"+line._2)))

val kasich = tweets.map(_.split('\t')).filter(line=>line(2).toLowerCase.matches(".*john.*|.*kasich.*")).map(c =>(c(1),("Kasich"+":"+c(7))))
val modifiedKasich = kasich.map(l=>(l._1,(l._2.replace("1","-1"))))
val modifiedKasich1 = modifiedKasich.map(l=>(l._1,(l._2.replace("0","-1"))))
val modifiedKasich = modifiedKasich1.map(l=>(l._1,(l._2.replace("2","0"))))
val modifiedKasich1 = modifiedKasich.map(l=>(l._1,(l._2.replace("3","1"))))
val modifiedKasich = modifiedKasich1.map(l=>(l._1,(l._2.replace("4","1"))))
val modifiedKasich1 = modifiedKasich.map(l=>(l._1,(l._2.replace("Kasich:",""))))
val modifiedKasich = modifiedKasich1.map(l=>(l._1,(l._2.toInt)))
val commonKasich = modifiedKasich.reduceByKey(_+_)
val finalKasich = commonKasich.map(line=>(line._1,("Kasich"+":"+line._2)))

val union = finalHillary.union(finalDonald).union(finalBernie).union(finalKasich).union(finalTed)

val tempResult = union.groupByKey().cache()
val findTotalHillary = tempResult.map(l=>("Hillary",findTotal(l._2,"Hillary"))).reduceByKey(_+_)
val findTotalDonald = tempResult.map(l=>("Donald",findTotal(l._2,"Donald"))).reduceByKey(_+_)
val findTotalBernie = tempResult.map(l=>("Bernie",findTotal(l._2,"Bernie"))).reduceByKey(_+_)
val findTotalTed = tempResult.map(l=>("Ted",findTotal(l._2,"Ted"))).reduceByKey(_+_)
val findTotalKasich = tempResult.map(l=>("Kasich",findTotal(l._2,"Kasich"))).reduceByKey(_+_)
val findTotalAll = findTotalHillary.union(findTotalDonald).union(findTotalBernie).union(findTotalTed).union(findTotalKasich)

val finalResult = tempResult.map(l=>(l._1,voteOut(l._2)))
val hateHillary = finalResult.map(l=>("Hillary",hateTotal(l._2,"Hillary"))).reduceByKey(_+_)
val hateDonald = finalResult.map(l=>("Donald",hateTotal(l._2,"Donald"))).reduceByKey(_+_)
val hateBernie = finalResult.map(l=>("Bernie",hateTotal(l._2,"Bernie"))).reduceByKey(_+_)
val hateTed = finalResult.map(l=>("Ted",hateTotal(l._2,"Ted"))).reduceByKey(_+_)
val hateKasich = finalResult.map(l=>("Kasich",hateTotal(l._2,"Kasich"))).reduceByKey(_+_)
val hateAll = hateHillary.union(hateDonald).union(hateBernie).union(hateTed).union(hateKasich)

val merge = hateAll.union(findTotalAll).groupByKey()
merge.saveAsTextFile("merge")
