package matasano

import math.pow

/**
  * Define metric between two maps
  */
class Metric[T] {
  // http://docs.opencv.org/doc/tutorials/imgproc/histograms/histogram_comparison/histogram_comparison.html
  def distance(a: Map[T, Double], b: Map[T, Double]): Double = 0
}

/**
  * ChiSquare metric
  */
class ChiSquare[T] extends Metric[T] {

  override def distance(a: Map[T, Double], b: Map[T, Double]): Double =
    a.foldLeft(0.0){ case (acc, (ch, v)) => acc + (pow(v - b.getOrElse(ch, 99.0), 2) / v) } // todo: find mathematically reasonable value
}

