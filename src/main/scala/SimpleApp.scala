import org.apache.spark.streaming.{ Seconds, StreamingContext }
import StreamingContext._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.twitter._

import scala.io.Source

object SimpleApp {
  val SPARK_HOME = "/opt/spark/"
  val intervalWindow = 30

  def configureTwitterCredentials() = {
    for (line <- Source.fromFile("resource/twitter.txt").getLines()) {
      val key :: value :: _ = line.replace(" ", "").split("=").toList
      val fullKey = "twitter4j.oauth." + key;
      System.setProperty(fullKey, value)
      System.out.println("\tProperty " + fullKey + " set as " + value)
    }
  }

  def main(args: Array[String]) {

    configureTwitterCredentials

    val serverLocation = if (args.length > 0) args(0)
    else "local[4]"

    val ssc = new StreamingContext(
      serverLocation,
      "TwitterPopularTags",
      Seconds(2),
      SPARK_HOME)

    val stream = TwitterUtils.createStream(ssc, None, Nil)

    val statuses = stream.map(status => status.getText())
    statuses.print()

    val hashTags = stream.flatMap(status => status.getText.split(" ").filter(_.startsWith("http")))

    val topCounts = hashTags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(intervalWindow))
      .map { case (topic, count) => (count, topic) }
      .transform(_.sortByKey(false))

    // Print popular hashtags
    topCounts.foreach(rdd => {
      val topList = rdd.take(5)
      println("\nPopular topics in last %d seconds (%s total):".format(intervalWindow, rdd.count()))
      topList.foreach { case (count, tag) => println("%s (%s tweets)".format(tag, count)) }
    })

    ssc.start()
  }
}
