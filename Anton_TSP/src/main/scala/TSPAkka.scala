import akka.actor._
import scala.util.Random

case class BatchWork(nodeBatch: Seq[Seq[Int]], record: Double)

case class LoadMatrix(matrix: Seq[Seq[Double]])

case class Result(length: Double, path: String)

case object NewBruteForceBatch

case object NewRandomBatch

case object Ready

class Supervisor extends Actor {
  def distanceMatrix(nodes: Seq[(Int, Int)]): Array[Array[Double]] = {
    Array.tabulate(nodes.length, nodes.length) {
      (x, y) => Math.sqrt(Math.pow(nodes(x)._1 - nodes(y)._1, 2) + Math.pow(nodes(x)._2 - nodes(y)._2, 2))
    }
  }

  val input = "79,205,715,166,56,197,80,140,309,503,578,90,978,240,628,161,484,680,680,665,108,881,876,772,264,400,7,340,670,372,670,736,918,869,226,780,200,615,718,297,562,959,972,784,766,20,649,372,227,693,377,356,350,480,955,82,191,654,295,207,41,596,595,916,611,656,219,643,319,424,563,871,484,18,28,502,27,805,503,217,708,490,258,885,718,227,880,37,597,997,430,265,850,320,696,390,854,142,448,571,547,109,98,924,127,599,685,514,895,414,394,467,200,221,47,534,892,550,827,34,603,845,544,795,243,660,257,776,386,221,317,345,540,458,535,986,431,337,652,639,495,459,631,157,701,103,20,239,458,974,208,646,499,921,61,75,613,499,908,463,724,108,881,561,437,160,806,285,619,396,970,467,533,652,912,778,708,669,896,383,155,334,158,583,605,651,699,460,894,750,741,995,52,449,296,220,519,796,396,25,242,280,742,909,40,574,765,563,139,667,866,778,463,450,952,278,725,96,703,332,333,882,488,635,592,806,360,683,106,955,589,611,964,176,983,71,442,426,902,387,738,994,532,162,488,944,484,104,121,234,0,274,674,935,165,644,987,719,808,648,291,466,262,479,578,667,247,494,669,252,986,72,802,654,305,792,323,286,419,357,138,346,977,440,274,751,897,575,922,963,312,234,239,856,497,658,864,227,823,790,813,453,907,412,810,124,678,765,701,569,613,60,765,278,352,697,747,972,284,615,425,801,55,693,635,190,704,778,943,244,994,418,926,805,782,768,789,593,46,493,949,759,396,294,76,498,224,68,550,905,811,823,432,926,741,95,766,338,632,96,969,931,951,670,216,245,752,841,573,375,658,243,258,122,539,901,159,186,855,208,88,663,392,881,772,652,573,214,850,181,427,426,279,6,229,984,987,982,274,633,246,43,350,426,530,203,472,905,568,471,383,12,422,515,203,542,264,584,921,69,748,785,103,173,874,338,285,270,59,55,11,621,47,0,76,6,694,319,211,468,324,993,28,626,241,610,126,678,603,372,293,193,950,172,460,204,312,868,380,249,685,684,437,680,96,167,143,291,32,842,486,158,20,983,555,238,378,514,820,262,456,991,974,613,625,723,275,458,442,186,493,250,804,227,349,951,137,309,530,282,863,565,46,595,229,205,327,10,41,122,57,871,859,451,494,90,105,611,254,786,365,946,774,857,777,798,864,621,771,772,928,407,147,860,882,357,693,576,973,974,954,547,506,924,28,797,97,947,837,869,699,956,411,809,694,949,18,966,550,964,746,973,778,880,771,910,106,173,460,207,516,864,934,20,421,58,516,631,621,951,311,521,465,737,863,46,375,257,387,92,194,492,176,181,672,49,310,599,157,451,612,889,865,516,564,596,208,687,637,983,388,806,276,827,67,82,380,503,615,57,295,578,929,609,613,476,998,198,46,301,81,99,177,604,779,219,460,489,328,240,729,715,294,510,198,728,926,954,696,282,773,595,717,767,640,564,570,736,952,391,350,709,648,628,197,950,427,178,423,228,57,444,369,588,975,433,103,808,622,180,684,837,908,335,706,800,320,501,454,389,499,42,888,648,438,513,354,770,300,57,379,613,441,330,323,610,559,915,598,485,114,216,55,249,469,422,510,563,981,932,913,284,301,841,205,185,487,441,559,860,402,779,452,475,966,432,374,709,623,352,303,426,67,986,885,65,805,115,234,23,639,31,987,900,225,552,823,80,799,574,207,842,338,809,78,242,749,651,192,542,599,697,947,541,679,303,297,675,987,258,439,417,16,926,739,147,544,86,811,849,646,562,904,800,807,837,685,632,755,42,605,476,172,879,997,873,353,967,227,340,140,795,236,688,255,732,314,832,282,512,205,380,391,593,655,275,199,785,620,871,605,244,931,838,924,234,540,208,777,8,535,385,458,904,334,509,550,664,16,270,188,305,604,675,425,173,281,402,273,748,107,110,668,182,543,696,350,932,268,526,668,520,232,734,801,344,379,273,610,501,185,438,844,750,966,236,814,387,101,146,854,360,151,129,339,145,925,557,317,405,772,105,17,311,466,281,7,684,404,644,670,102,177,199,575,857,666,493,882,889,391,924,510,812,265,753,782,295,895,923,758,156,662,794,708,646,817,353,563,76,498,89,501,113,592,379,504,441,997,566,596,8,425,986,38,150,20,926,905,508,428,98,787,257,378,794,43,529,217,229,774,542,239,430,124,187,533,183,154,984,352,689,584,42,384,503,375,119,81,838,102,686,156,236,792,644,90,929,713,525,710,387,225,672,439,484,943,549,381,401,79,918,682,283,440,871,474,525,891,460,993,734,57,658,81,148,588,501,295,196,11,77,723,908,509,822,158,349,97,979,70,14,783,873,909,958,318,976,983,696,168,639,185,567,727,886,611,999,837,154,466,68,664,735,83,194,431,505,46,456,686,139,179,239,795,43,791,607,481,787,413,750,403,63,481,628,403,529,589,971,522,243,752,107,210,905,700,893,364,954,345,609,272,169,258,97,650,6,153,855,375,48,802,600,349,866,410,369,342,351,438,918,259,993,479,203,779,174,143,683,654,108,359,623,165,732,262,585,504,29,8,543,220,38,546,887,267,421,725,39,602,923,647,59,386,943,335,270,631,21,820,788,856,837,116,514,257,928,432,118,701,499,386,313,216,177,255,531,240,969,336,583,671,614,876,45,185,920,722,87,778,89,379,655,233,807,222,201,448,387,817,50,160,300,788,640,965,534,598,535,215,200,742,406,722,829,925,401,673,333,570,157,802,366,757,580,990,105,277,393,885,686,606,579,894,273,280,642,902,805,104,541,118,205,908,449,589,561,514,487,862,164,715,3,437,367,772,175,105,753,870,374,658,453,257,160,403,849,990,952,800,166,600,178,791,213,109,566,690,183,580,201,436,848,541,292,823,114,367,916,510,832,556,976,721,178,464,786,574,229,544,169,410,30,585,595,789,578,370,674,132,940,597,291,55,365,819,417,78,627,285,990,682,252,148,555,935,742,308,804,783,745,125,934,923,159,816,215,566,296,41,154,392,760,212,118,311,544,706,970,98,118,553,462,688,12,513,531,17,757,818,511,121,592,424,364,934,453,391,809,359,602,959,92,333,607,651,528,202,927,550,46,596,697,494,51,186,264,819,357,97,356,340,850,345,51,211,939,751,363,598,659,505,462,309,271,474,721,6,137,678,369,897,343,253,196,180,302,355,766,432,975,544,172,879,508,399,922,754,102,828,678,680,622,797,103,674,18,204,254,627,58,486,202,820,907,530,128,328,387,134,926,33,806,776,492,5,846,885,683,281,144,341,705,495,557,331,11,245,406,224,229,385,834,338,348,849,533,395,886,989,762,846,357,573,754,911,380,192,927,575,362,380,634,750,411,735,846,807,844,223,453,218,662,290,290,998,637,457,657,874,320,763,641,579,131,666,524,255,768,993,804,218,422,359,753,460,587,83,960,547,443,544,123,940,172,259,945,715,421,611,991,83,773,661,339,514,335,728,843,726,439,905,345,441,390,86,557,981,836,16,761,108,571,898,488,724,471,626,566,894,778,166,325,421,941,476,45,312,354,296,380,337,71,715,895,595,420,380,977,674,76,109,938,983,130,956,81,333,65,289,411,217,970,545,881,585,819,537,531,861,291,25,911,389,10,180,326,790,49,328,802,238,850,740,714,408,969,339,514,165,986,56,232,288,835,60,639,1,323,438,605,481,259,177,480,377,133,913,395,285,361,723,958,620,779,363,651,191,219,834,973,404,781,286,90,413,701,873,757,31,879,71,565,955,406,345,412,553,558,549,797,617,683,127,276,526,732,235,123,188,419,188,68,749,108,57,908,50,766,463,630,803,620,40,496,772,493,532,698,691,508,927,855,768,407,27,467,642,389,449,54,920,788,788,874,841,697,100,232,90,384,751,997,48,374,378,647,168,377,520,332,466,698,616,400,87,697,445,228,990,575,89,521,193,23,711,424,278,456,720,546,55,373,373,204,460,624,351,930,854,395,99,363,966,502,250,997,867,176,209,381,440,0,31,399,305,728,246,359,556,77,786,248,870,302,349,36,659,978,753,520,33,463,532,689,413,414,753,921,448,303,489,692,437,609,116,82,608,640,350,147,48,221,368,984,158,581,779,668,105,673,904,345,671,34,58,282,842,510,718,979,382,394,309,994,158,77,481,110,116,935,193,362,579,212,755,474,12,70,756,92,340,963,704,259,903,29,246,654,572,221,191,13,431,664,568,479,726,133,841,570,374,978,276,689,873,501,538,835,282,26,438,541,44,266,445,392,123,698,415,622,508,367,130,832,213,207,156,785,352,378,768,686,306,80,526,476,148,307,110,899,240,295,713,348,850,847,168,835,237,848,915,5,860,97,573,94,677,691,956,440,777,894,786,282,329,580,600,981,176,9,401,419,981,451,665,629,216,300,65,959,976,178,646,598,698,275,718,422,925,630,444,567,897,388,942,531,635,475,220,419,646,15,686,291,542,612,325,749,504,703,485,842,553,904,901,470,319,594"
//  val input = "33,35,72,40,87,51,64,23,10,10,55,45,7,60,99,24,81,64,57,97,31,41,85,82,53,84,43,41,98,50,3,69,35,20,77,13,72,48,70,33,61,78,33,59,59,58,36,8,78,15,61,61,65,87,20,65,8,3,76,56,22,20,52,81,57,54,76,14,1,95,69,86,15,28,36,24,2,77,92,18,66,67,68,86,71,10,14,74,89,43,62,62,97,80,26,46,80,54,7,67,4,24,15,71,94,78,67,36,70,61,13,82,36,6,73,98,37,27,79,25,79,33,29,54,13,33,94,8,51,78,31,83,70,1,10,7,26,50,32,9,14,23,39,3,4,10,34,10,11,93,78,95,36,93,73,19,33,11,81,40,74,69,89,20,38,51,12,49,37,29,74,64,32,2,23,44,56,13,23,97,46,30,5,98,79,30,60,94,19,93,82,42,82,77,6,26,28,40,69,67"
  val nodes = input.split(",").map(_.toInt).grouped(2).map(a => a(0) -> a(1)).toSeq
  val length = nodes.length
  val matrix = distanceMatrix(nodes).map(_.toList).toList
  var record = Double.MaxValue
  val batchSize = 100
  val cores = Runtime.getRuntime.availableProcessors()

  (0 until cores) foreach {
    index => context.actorOf(Props[Worker], s"worker-$index") ! LoadMatrix(matrix)
  }

  override def receive: Receive = {
    case Ready => {
      sender ! LoadMatrix(matrix)
    }

    case NewRandomBatch => {
      val nodeList = (0 until length).toList
      val batch = List.fill(batchSize)(Random.shuffle(nodeList))
      sender ! BatchWork(batch, record)
    }

    case Result(pathLength, path) => {
      if (pathLength < record) {
        record = pathLength
        println(s"${sender().path.name}: New record: $pathLength $path")
      }
    }
  }
}

class Worker extends Actor {
  val random = new Random()
  override def preStart(): Unit = {
    println(s"${self.path.name}: Started up.")
    context.parent ! Ready
  }

  def checkLength(path: Seq[Int], distance: Seq[Seq[Double]]): Double = {
    path.sliding(2, 1).toArray.map {
      item => distance(item(0))(item(1))
    }.sum + distance(path.head)(path.last)
  }

  def workMode(matrix: Seq[Seq[Double]]): Receive = {
    case BatchWork(nodeBatch, record) => {
      val pathLength = nodeBatch.head.length
      val indexList = (0 until pathLength).toList
      var recordCache = record
      var workPath: Seq[Int] = null
      var bestPath: Seq[Int] = null
      var length = 0d
      var lw = 0d
      var p1List = List(0)
      var p2List = List(0)
      var p1 = 0
      var p2 = 0

      nodeBatch.foreach { path =>
        bestPath = path
        length = checkLength(bestPath, matrix)

        p1List = Random.shuffle(indexList)
        p1 = p1List.headOption.getOrElse(-1)
        p1List = p1List.tail

        p2List = Random.shuffle((p1 + 1 until pathLength).toList)
        p2 = p2List.headOption.getOrElse(-1)
        p2List = p2List.tail

        while (p1 != -1) {
          while (p2 != -1) {
            workPath = bestPath.take(p1) ++ bestPath.slice(p1, p2).reverse ++ bestPath.drop(p2)
            lw = checkLength(workPath, matrix)

            length =
              if (length - lw < 0.01d) length
              else {
                bestPath = workPath
                p1List = Random.shuffle(indexList)
                p1 = p1List.headOption.getOrElse(-1)
                p1List = p1List.tail

                p2List = Random.shuffle((p1 + 1 until pathLength).toList)
                lw
              }

            if (length - recordCache < 0.01d) {
              recordCache = length
              context.parent ! Result(length, bestPath.mkString("[", ",", "]"))
            }

            p2 = p2List.headOption.getOrElse(-1)
            if (p2 != -1) p2List = p2List.tail
          }

          p1 = p1List.headOption.getOrElse(-1)
          if (p1 != -1) p1List = p1List.tail

          p2List = Random.shuffle((p1 + 1 until pathLength).toList)
          p2 = p2List.headOption.getOrElse(-1)
          if (p2 != -1) p2List = p2List.tail
        }
      }

      context.parent ! NewRandomBatch
    }
  }

  override def receive: Receive = {
    case LoadMatrix(m) => {
      context.become(workMode(m))
      sender ! NewRandomBatch
    }
  }
}

object TSP extends App {
  val system = ActorSystem("TSP")
  val supervisor = system.actorOf(Props[Supervisor], "supervisor")
}