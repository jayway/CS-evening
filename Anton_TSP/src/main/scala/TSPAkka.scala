import akka.actor.SupervisorStrategy.Restart
import akka.actor._

case class BatchWork(nodeBatch: List[Seq[Int]], record: Long)

case class LoadMatrix(matrix: List[List[Int]])

case class Result(length: Long, path: String)

case object NewBatch

case object Ready

class Supervisor extends Actor {
  def distanceMatrix(nodes: Seq[(Int, Int)]): Array[Array[Int]] = {
    Array.tabulate(nodes.length, nodes.length) {
      (x, y) => Math.sqrt(Math.pow(nodes(x)._1 - nodes(y)._1, 2) + Math.pow(nodes(x)._2 - nodes(y)._2, 2)).toInt
    }
  }

  val input = "33,35,72,40,87,51,64,23,10,10,55,45,7,60,99,24,81,64,57,97,31,41,85,82,53,84,43,41,98,50,3,69,35,20,77,13,72,48,70,33,61,78,33,59,59,58,36,8,78,15,61,61,65,87,20,65,8,3,76,56,22,20,52,81,57,54,76,14,1,95,69,86,15,28,36,24,2,77,92,18,66,67,68,86,71,10,14,74,89,43,62,62,97,80,26,46,80,54,7,67,4,24,15,71,94,78,67,36,70,61,13,82,36,6,73,98,37,27,79,25,79,33,29,54,13,33,94,8,51,78,31,83,70,1,10,7,26,50,32,9,14,23,39,3,4,10,34,10,11,93,78,95,36,93,73,19,33,11,81,40,74,69,89,20,38,51,12,49,37,29,74,64,32,2,23,44,56,13,23,97,46,30,5,98,79,30,60,94,19,93,82,42,82,77,6,26,28,40,69,67"
  val nodes = input.split(",").map(_.toInt).grouped(2).map(a => a(0) -> a(1)).toSeq
  val matrix = distanceMatrix(nodes).map(_.toList).toList
  var record = Long.MaxValue
  val batchSize = 10000
  val cores = Runtime.getRuntime.availableProcessors()
  var permutations = (0 until nodes.length).permutations.toStream

  (0 until cores) foreach {
    index => context.actorOf(Props[Worker], s"worker-$index") ! LoadMatrix(matrix)
  }

  override def receive: Receive = {
    case Ready => {
      sender ! LoadMatrix(matrix)
    }

    case NewBatch => {
      val batch = permutations.take(batchSize).toList
      if (batch.length > 0) sender ! BatchWork(batch, record)
      else println(s"${self.path.name}: No more batches to distribute to ${sender.path.name}.")
      permutations = permutations.drop(batchSize)
    }

    case Result(length, path) => {
      if (length <= record) {
        record = length
        println(s"${self.path.name}: New record: $length $path")
      }
    }
  }
}

class Worker extends Actor {
  override def preStart(): Unit = {
    println(s"${self.path.name}: Started up.")
    context.parent ! Ready
  }

  def checkLength(path: Seq[Int], distance: List[List[Int]]): Int = {
    path.sliding(2, 1).toArray.map {
      item => distance(item(0))(item(1))
    }.sum + distance(path.head)(path.last)
  }

  def workMode(matrix: List[List[Int]]): Receive = {
    case BatchWork(nodeBatch, record) => {
      var internalRecord = record

      nodeBatch.foreach { path =>
        val length = checkLength(path, matrix)

        if (length <= internalRecord) {
          internalRecord = length
          context.parent ! Result(length, path.mkString("[", ",", "]"))
        }
      }

      context.parent ! NewBatch
    }
  }

  override def receive: Receive = {
    case LoadMatrix(m) => {
      context.become(workMode(m))
      sender ! NewBatch
    }
  }
}

object TSP extends App {
  val system = ActorSystem("TSP")
  val supervisor = system.actorOf(Props[Supervisor], "supervisor")
}