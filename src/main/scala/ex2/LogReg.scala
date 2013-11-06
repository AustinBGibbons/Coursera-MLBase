package ex2

import mli.interface.MLContext
import mli.ml.classification.LogisticRegressionAlgorithm
import org.apache.spark.SparkContext

object LogReg {
  def main(args: Array[String]) {
    println("Ahoy hoy")
    val sc = new SparkContext("local[1]", "spark-local")
    val mc = new MLContext(sc)

    // FIXME can't take in type Float ?
    val data = mc.load(readDatafile("./assignments/ex2-003/mlclass-ex2/ex2data1.txt"))
    val model = LogisticRegressionAlgorithm.train(data)

    sc.stop()

  }

  def readDatafile(filename: String): Array[Array[Double]] = {
    scala.io.Source.fromFile(filename).getLines().map(_.split(',').map(_.toDouble)).map(line => {
      assert(line.size == 3)
      line.takeRight(1) ++ line.dropRight(1)
    }).toArray
  }

}
